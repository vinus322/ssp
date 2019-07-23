import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    	/**
         * 정렬 sort
         */
        List<Person> list = new ArrayList<>();
        list.add(new Person("ȫ����",33));
        list.add(new Person("ȫ����",31));
        list.add(new Person("������",43));
        list.add(new Person("������",53));
        list.add(new Person("ȫ����",23));


        System.out.println(list);
        list.sort((p1,p2)->p1.getName().compareTo(p2.getName()));
        System.out.println(list);
        list.sort((p1,p2)->Integer.compare(p1.getAge(),p2.getAge()));
        System.out.println(list);


        /**
         * 파일 입출력
         */
        try{
            //���� ����
            File file = new File("test.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("test22");
            bufferedWriter.newLine();
            bufferedWriter.write("test");

            bufferedWriter.close();
            fileWriter.close();


            //���� �б�
            File fileout = new File("test.txt");
            FileReader fileReader = new FileReader(fileout);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            while((line=bufferedReader.readLine())!=null)
                System.out.println(line);

        }catch(IOException e){
            System.out.println("file not");
        }


        /**
         * 파일 읽는 메서드 실행부
         */
        try {
            List<String> strList = FileReaderH.readAll("text.txt", "PRINT");
            System.out.println(strList);
            
            FileReaderH.writeAll("out.txt", strList);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        /**
         * 외부 프로그램 실행 메서드 실행부
         */
        try{
            String ret = ProcessH.run("Project1.exe","test","4");
            System.out.println("���� �� : "+ret);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        /**
         * 소켓
         */
    }
}