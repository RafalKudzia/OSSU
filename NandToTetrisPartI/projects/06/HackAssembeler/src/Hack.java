import java.io.*;
import java.util.ArrayList;

public class Hack {

    private static String FILENAME="";
    private static final String strPattern = "^[a-zA-Z0-9._ -]+\\.(asm)$";
    private ArrayList<String> commands =new ArrayList<>();
    String data="";
    String[] subStr;
    File file;// = new File(FILENAME);
    BufferedReader br;
    File fileToWrite ;
    BufferedWriter bw;


    public Hack() throws IOException {
    }

    public static void main(String args[]) throws IOException {

                 if (args[0].matches(strPattern)) //sprawdzenie czy ładowany plik ma odpowiednią nazwę pliku tzn. z rozszerzeniem .asm
                 {                                //Jeżeli tak nazwa pliku zostaj przypisana do zmiennej FILENAME
                     FILENAME = args[0];          //tworzony jest obiekt klasy Hack i uruchomiona zostaje metoda run()
                     Hack hack = new Hack();      //zawierająca całą logikę aplikacji
                     hack.run();

                 }
                 else
                     System.out.println("File name not allowed");
    }
/*W Hack asemblerze są dwa podstawowe rodzaje instrukcji A-instruction ora C-instruction. Na potrzeby tworzenia
 aplikacji zastała utworzona dodatkowo L-instruction. Rozpoznaje ona etykiety oraz zmienne*/

            void run() throws IOException {
                Cinstruction CIns= new Cinstruction(); //utworzenie obiektów dla każdej instrukcji
                Ainstruction AIns =new Ainstruction();
                Linstruction LIns =new Linstruction();

                file = new File(FILENAME);                          // utworzenie readera do odczytu pliku .asm
                br =new BufferedReader(new FileReader(file));
                fileToWrite = new File(FILENAME.substring(0,FILENAME.indexOf('.'))+".hack"); //na podstawie nazwy pliku jest tworzony plik z rozszerzeniem .hack
                bw = new BufferedWriter(new FileWriter(fileToWrite));



                while((data= br.readLine())!=null)     //przepisanie każdej komendy do ArrayListy każda komenda w osobnej komórce
                {                                      //wraz z usunięciem pustuch linii oraz komentarzy
                    data = data.trim();
                    if (!(data.startsWith("//") || data.equals("")))
                    {
                        if (data.contains("//")) {
                            subStr = data.split("//");
                            data = subStr[0].trim();
                        }

                        commands.add(data);

                    }
                }

                LIns.initTable(commands);                // inicjacja tablicy symboli

                /*Główna logika programu. Tutaj następuje identyfikacja każdej komendy(A,C lub L)
                * , zamiana ich do postaci binarnej oraz zapisanie do pliku "nazwa".hack*/
                for (int i = 0;i<commands.size();i++)
                {
                    if (commands.get(i).charAt(0) == '@' )                                         //Jeżeli jest to L-instruction na podstawie tabeli symboli
                        if (LIns.contaiSymbol(commands.get(i).substring(1)))                       // następuje zamiana symbolu na jej wartość liiczbową
                            commands.set(i,"@"+LIns.getSymbolValue(commands.get(i).substring(1))); //Dalej jest traktowana jak A-instruction

                    if(AIns.isAinstruction(commands.get(i))) //Jeżeli instrukcja A to zamiana do postaci binarnej
                    {                                        //oraz zapisanie do pliku
                        bw.write(AIns.binaryAinstruction(commands.get(i)));
                        bw.newLine();
                        bw.flush();
                        continue;
                    }

                    if(CIns.isCinstruction(commands.get(i))) //Jeżeli instrukcja C to zamiana do postaci binarnej
                    {                                        //oraz zapisanie do pliku
                        bw.write(CIns.binaryCinstruction(commands.get(i)));
                        bw.newLine();
                        bw.flush();

                    }
                }

    }
}

