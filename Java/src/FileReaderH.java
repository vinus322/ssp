import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderH {
	/**
	 * 파일 이름을 받고 그 파일에 쓰여진 값들을 읽어서 List<String>으로 반환함
	 * @param path
	 * @return
	 * @throws IOException
	 */
    public static List<String> readAll(String path) throws IOException {
        File fileout = new File(path);
        FileReader fileReader = new FileReader(fileout);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        List<String> list = new ArrayList<>();
        while((line=bufferedReader.readLine())!=null)
            list.add(line);
        
        bufferedReader.close();
        fileReader.close();
        
        return list;
    }

    /**
     * 파일 이름과 마지막에 오는 String을 받고 해당 String이 나오기 전까지 읽어주고 그 값들을 List<String>형으로 반환
     * 
     * @param path
     * @param endStr
     * @return
     * @throws IOException
     */
    public static List<String> readAll(String path,String endStr) throws IOException{
        File fileout = new File(path);
        FileReader fileReader = new FileReader(fileout);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        List<String> list = new ArrayList<>();
        while(!(line=bufferedReader.readLine()).equals(endStr))
            list.add(line);
        
        bufferedReader.close();
        fileReader.close();
        
        return list;
    }
    
    /**
     * 파일 이름과 List<String>을 받아서 
     * 해당 파일에 String들을 써주는 함수
     * @param path : 해당하는 file에 씀(없으면 생성)
     * @param strList : 파일에 쓸 String의 List
     * @throws IOException
     */
    public static void writeAll(String path,List<String> strList) throws IOException{
    	File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int size = strList.size();
        for(int i=0;i<size;i++) {
        	bufferedWriter.write(strList.get(i));
        	if(!(i==size-1))
        		bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }
}