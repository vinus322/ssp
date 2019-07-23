import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessH {
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
//            System.out.println(line);
        }
        proc.waitFor();
        int result = proc.exitValue();
//        System.out.println("result Val : "+result);
        return ret;
    }
}