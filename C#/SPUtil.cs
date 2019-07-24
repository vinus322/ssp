using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace SPUtil
{
    public class SPUtil
    {
        static ConcurrentDictionary<string, List<string>> inputData = null;

        static SPUtil()
        {
            inputData = new ConcurrentDictionary<string, List<string>>();
        }

        /// <summary>
        /// 입력 파일 읽기
        /// </summary>
        /// <param name="fileName"></param>
        public static void ReadFile(string fileName)
        {
            inputData.TryAdd("A", new List<string>());
            inputData.TryAdd("B", new List<string>());
            inputData.TryAdd("C", new List<string>());

            String line;
            using (StreamReader sr = new StreamReader(fileName))
            {
                while (sr.Peek() > -1)
                {
                    line = sr.ReadLine();

                    if (line.Equals("PRINT"))
                        break;

                    DoFileLineJob(line);
                }
            }
        }

        /// <summary>
        /// 파일 출력
        /// </summary>
        /// <param name="fileName"></param>
        public static void WriteFile(string fileName, string text, bool append)
        {
            // Append text to an existing file named "WriteLines.txt".
            using (StreamWriter outputFile = new StreamWriter(fileName, append))
            {
                outputFile.Write(text);
            }
        }

        /// <summary>
        /// 외부 프로그램 실행
        /// </summary>
        /// <param name="messageCode"></param>
        /// <returns></returns>
        public static string executeExternalProcess(string fileName, string args)
        {
            ProcessStartInfo start = new ProcessStartInfo();
            start.FileName = fileName;
            start.UseShellExecute = false;
            start.RedirectStandardOutput = true;
            start.WindowStyle = ProcessWindowStyle.Hidden;
            start.CreateNoWindow = true;
            start.Arguments = args;

            using (Process process = Process.Start(start))
            {
                using (StreamReader reader = process.StandardOutput)
                {
                    return reader.ReadToEnd();
                }
            }
        }

        /// <summary>
        /// 서버 소켓 시작
        /// </summary>
        public void startServerSocket(int portNumber)
        {
            IPEndPoint ep = new IPEndPoint(IPAddress.Any, portNumber);
            TcpListener server = new TcpListener(ep);
            server.Start();

            Console.WriteLine("The server socket is started at {0}", ep.ToString());

            while (true)
            {
                TcpClient client = server.AcceptTcpClient();
                Console.WriteLine("A new client is connected from {0}", ep.ToString());

                ParameterizedThreadStart sTh = new ParameterizedThreadStart(ThreadFunc);
                Thread th = new Thread(sTh);
                th.Start(client);
            }
        }


        /// <summary>
        /// 접속된 클라이언트별 전용 쓰레드
        /// </summary>
        /// <param name="client"></param>
        static void ThreadFunc(object client)
        {
            TcpClient c = (TcpClient)client;
            NetworkStream ns = c.GetStream();

            byte[] buffer;

            // 1회 전송 데이터의 최대 길이
            int bufferSize = 1024;

            // 1회 데이터를 읽은 길이
            int nRead = 0;

            string line;
            string clientName = "";

            while (c.Connected)
            {
                buffer = new byte[bufferSize];

                try
                {
                    nRead = ns.Read(buffer, 0, bufferSize);
                    line = Encoding.Default.GetString(buffer, 0, nRead);

                    Console.WriteLine("Received Data : {0} from {1}", c.Client.RemoteEndPoint);

                    /* 출력용 클라이언트인가 프린트를 전송 */
                    if (clientName.Equals("PRINT_CLIENT") &&
                        line.Equals("PRINT"))
                    {
                        string outputData = MakeSocketPrintData();

                        SendData(ns, outputData);
                        break;
                    }
                    else if (line.Equals("CLIENT_NAME"))
                    {
                        clientName = line;
                        inputData[clientName] = new List<string>();
                    }
                    else
                    {
                        DoSocketLineJob(clientName, line);
                    }
                }
                catch (Exception ex)
                {

                }
            }
        }

        /// <summary>
        /// 출력 클라이언트에 보내줄 데이터 생성
        /// </summary>
        /// <returns></returns>
        static string MakeSocketPrintData()
        {
            string ret = string.Empty;

            return ret;
        }

        /// <summary>
        /// 파일인 경우 라인 단위 작업
        /// </summary>
        /// <param name="line"></param>
        static List<string> DoFileLineJob(string line)
        {
            string[] items = line.Split((",").ToCharArray());

            inputData["A"].Add(items[0]);
            //inputData["A"].Sort();

            inputData["B"].Add(items[1]);
            // inputData["B"].Sort();

            inputData["C"].Add(items[2]);
            // inputData["C"].Sort();


            return items.ToList<string>();
        }

        /// <summary>
        /// 소켓인 경우 라인 단위 작업
        /// </summary>
        /// <param name="line"></param>
        static void DoSocketLineJob(string clientName, string line)
        {
            string[] items = line.Split((",").ToCharArray());

            // 라인을 분석한 후 클라이언트 이름의 자료에 저장

            inputData[clientName].Add(line);

        }

        /// <summary>
        /// 텍스트 전송
        /// </summary>
        /// <param name="ns"></param>
        /// <param name="data"></param>
        static void SendData(NetworkStream ns, string data)
        {
            byte[] bufs = Encoding.Default.GetBytes(data);
            ns.Write(bufs, 0, bufs.Length);
        }
    }
}
