import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderH {
    public static List<String> readAll(String path) throws IOException {
        File fileout = new File(path);
        FileReader fileReader = new FileReader(fileout);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        List<String> list = new ArrayList<>();
        while((line=bufferedReader.readLine())!=null)
            list.add(line);
        return list;
    }

    public static List<String> readAll(String path,String endStr) throws IOException{
        File fileout = new File(path);
        FileReader fileReader = new FileReader(fileout);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        List<String> list = new ArrayList<>();
        while(!(line=bufferedReader.readLine()).equals(endStr))
            list.add(line);
        return list;
    }

}