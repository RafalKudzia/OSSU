import java.io.*;

public class VMtranslator {

    private static String FILENAME="";
    private static final String strPattern = "^[a-zA-Z0-9._ -]+\\.(vm)$";
    File file;
    BufferedReader br;
    File fileToWrite ;
    BufferedWriter bw;

    public static void main(String[] args) throws IOException {

        if (args[0].matches(strPattern)) //sprawdzenie czy ładowany plik ma odpowiednią nazwę pliku tzn. z rozszerzeniem .vm
        {                                //Jeżeli tak nazwa pliku zostaj przypisana do zmiennej FILENAME
            FILENAME = args[0];          //tworzony jest obiekt klasy Hack i uruchomiona zostaje metoda run()
            VMtranslator vmt = new VMtranslator();//zawierająca całą logikę aplikacji
            vmt.run();

        }
        else
            System.out.println("File name not allowed");

    }

    void run() throws IOException {

        Parser parser = new Parser();
        CodeWriter codeWriter = new CodeWriter();

        file = new File(FILENAME);                          // utworzenie readera do odczytu pliku .vm
        br =new BufferedReader(new FileReader(file));
        fileToWrite = new File(FILENAME.substring(0,FILENAME.indexOf('.'))+".asm"); //na podstawie nazwy pliku jest tworzony plik z rozszerzeniem .asm
        bw = new BufferedWriter(new FileWriter(fileToWrite));

        String data = "";
        while((data = br.readLine())!=null)
        {


        }


    }
}
