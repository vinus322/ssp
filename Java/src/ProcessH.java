import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessH {
	/**
	 * 
	 * @param processName 프로세스 이름 (경로 포함 가능)
	 * @param param 가변 매개변수
	 * @return 외부 프로그램에서 출력되는 값 String형
	 * @throws Exception
	 */
    public static String run(String processName,String...param) throws Exception{
        String ret = "";
        List<String> command = new ArrayList<>();
        command.add(processName);
        for(String str : param)
            command.add(str);
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(command);


        Process proc = pb.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line = "";
        while((line=br.readLine())!=null){
            ret+=line+"\n";
        }
        proc.waitFor();
        
        //실행파일에서 던져주는 return값
        int result = proc.exitValue();
        return ret;
    }
    
    /**
     * 
     * @param processName 프로세스 이름 (경로 포함 가능)
     * @param param 가변 매개변수
     * @return 외부 프로그램에서 던져준 return값
     * @throws Exception
     */
    public static int getReturnValue(String processName,String...param) throws Exception{
        List<String> command = new ArrayList<>();
        command.add(processName);
        for(String str : param)
            command.add(str);
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(command);


        Process proc = pb.start();

        //프로세스 끝날때 까지 기다려줌
        proc.waitFor();
        
        //실행파일에서 던져주는 return값
        int result = proc.exitValue();
        return result;
    }
}