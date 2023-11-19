import java.io.*;

public class VMtranslator {

    public Parser parser;
    public CodeWriter codeWriter;


    public static void main(String[] args) {
        File filePath;

        if (args[0].endsWith(".vm"));
        {
            filePath = new File(args[0]);
            VMtranslator vmt = new VMtranslator();
            vmt.parseCommand(filePath);
        }
    }

    private void parseCommand(File file)
    {
        parser = new  Parser(file);
        codeWriter = new CodeWriter(new File(file.getName().split("\\.")[0]+".asm"));

        String currentCommand;
        String commandType;

        while (parser.hasMoreCommand())
        {
            currentCommand =parser.advance();
            //System.out.println(currentCommand);
            commandType = parser.commandType(currentCommand);

            if(commandType.equals("C_PUSH") || commandType.equals("C_POP"))
            {
                codeWriter.writePushPop(commandType, parser.arg1(), parser.arg2());

            }
            if (commandType.equals("C_ARITHMETIC"))
            {
                codeWriter.writeArithmetic(parser.arg1());
            }

        }
    }

    /*private static String FILENAME="";
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

        file = new File(FILENAME);                          // utworzenie readera do odczytu pliku .vm
        FileReader fr =new FileReader(file);
        br =new BufferedReader(fr);
        fileToWrite = new File(FILENAME.substring(0,FILENAME.indexOf('.'))+".asm"); //na podstawie nazwy pliku jest tworzony plik z rozszerzeniem .asm
        FileWriter fw =new FileWriter(fileToWrite);
        //bw = new BufferedWriter();

        Parser parser = new Parser(fr);
        CodeWriter codeWriter = new CodeWriter(fw);

        String data = "";
        while((data = br.readLine())!=null)
        {
            data =data.trim();
            if (!(data.startsWith("//") || data.equals(""))) {
                bw.write("//" + data);
                bw.newLine();


                bw.flush();
            }
        }
    }*/
}
