import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /**
         * 정렬 sort
         */
        List<Person> list = new ArrayList<>();
        list.add(new Person("홍성훈",33));
        list.add(new Person("홍성충",31));
        list.add(new Person("헤헤헤",43));
        list.add(new Person("가나다",53));
        list.add(new Person("홍가나",23));


        System.out.println(list);
        list.sort((p1,p2)->p1.getName().compareTo(p2.getName()));
        System.out.println(list);
        list.sort((p1,p2)->Integer.compare(p1.getAge(),p2.getAge()));
        System.out.println(list);


        /**
         * 파일 입출력
         */
        try{
            //파일 쓰기
            File file = new File("test.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("test22");
            bufferedWriter.newLine();
            bufferedWriter.write("test");

            bufferedWriter.close();
            fileWriter.close();


            //파일 읽기
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
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        /**
         * 외부 프로그램 실행 메서드 실행부
         */
        try{
            String ret = ProcessH.run("Project1.exe","test","4");
            System.out.println("받은 값 : "+ret);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        /**
         * 소켓
         */
    }
}