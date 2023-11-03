import java.util.HashMap;

class Cinstruction {
                                                //regex rozpoznający czy dana linia jest instrukcją C
    private static final String cInstrRegex = "^((A?M?D?=)?([-!]?[(-1)01ADM]{1}[-+|&]?[1ADM]?)?(;J[GELMN][TQEP])?)$";
    private  StringBuffer binary = new StringBuffer(); //służy do tworzenia stringa w postaci binarnej
    private HashMap<String,String> destBin;     //deklaracja map, któe zwierają wszystkie możliwe operacje
    private HashMap<String,String> jumpBin;     // do wykonania przez CPU Hack komputera
    private HashMap<String,String> compBin;



     public Cinstruction() //konstrukto klasy Cinstruction, w którym inicjujemy mapy dla CPU
     {
         destBin = new HashMap<String,String>();
         destBin.put("","000");
         destBin.put("M","001");
         destBin.put("D","010");
         destBin.put("MD","011");
         destBin.put("A","100");
         destBin.put("AM","101");
         destBin.put("AD","110");
         destBin.put("AMD","111");

         jumpBin = new HashMap<String,String>();
         jumpBin.put("","000");
         jumpBin.put("JGT","001");
         jumpBin.put("JEQ","010");
         jumpBin.put("JGE","011");
         jumpBin.put("JLT","100");
         jumpBin.put("JNE","101");
         jumpBin.put("JLE","110");
         jumpBin.put("JMP","111");

         compBin = new HashMap<String,String>();
         compBin.put("0","0101010");
         compBin.put("1","0111111");
         compBin.put("-1","0111010");
         compBin.put("D","0001100");
         compBin.put("A","0110000");
         compBin.put("M","1110000");
         compBin.put("!D","0001101");
         compBin.put("!A","0110001");
         compBin.put("!M","1110001");
         compBin.put("-D","0001111");
         compBin.put("-A","0110011");
         compBin.put("-M","1110011");
         compBin.put("D+1","0011111");
         compBin.put("A+1","0110111");
         compBin.put("M+1","1110111");
         compBin.put("D-1","0001110");
         compBin.put("A-1","0110010");
         compBin.put("M-1","1110010");
         compBin.put("D+A","0000010");
         compBin.put("D+M","1000010");
         compBin.put("D-A","0010011");
         compBin.put("D-M","1010011");
         compBin.put("A-D","0000111");
         compBin.put("M-D","1000111");
         compBin.put("D&A","0000000");
         compBin.put("D&M","1000000");
         compBin.put("D|A","0010101");
         compBin.put("D|M","1010101");

     }


    public boolean isCinstruction(String arg) //metoda sprawdzająca czy to instrucja C
    {
        if (arg.matches(cInstrRegex))
            return true;
        else
            return false;

    }

    public  String binaryCinstruction(String arg) //metoda zwracająca postać binarną instrukcji
    {

        binary.delete(0,binary.length());               //zerujemy strin bufera
        String[] help;                                  //tablice pomocnicze
        String[] help2;
        binary.append("111");                           //instrukcja C zaczyna sie od 111

        /*Tutaj następuje sprawdzenie z czego składa się intrukcja C
        * Obowiązkowo musi być cześć z operacją(compute) compBin, skok oraz miejsce docelowe
        * są opcjonalne*/
        if (arg.contains("=")) // czy jest miejsce docelowe
        {
            help = arg.split("="); // jeśli tak dzieli stringa na destination i resztę
            if (help[1].contains(";"))  //czy zawiera skok
            {
                help2=help[1].split(";"); // jeśli tak oddziela skok od operacji CPU
                binary.append(compBin.get(help2[0]));
                binary.append(destBin.get(help[0]));
                binary.append(jumpBin.get(help2[1]));
            }
            else
            {
                binary.append(compBin.get(help[1]));
                binary.append(destBin.get(help[0]));
                binary.append(jumpBin.get(""));

            }
        }
        else if (arg.contains(";"))             //może być tak że jest tylko compute i jump
        {
            help= arg.split(";");
            binary.append(compBin.get(help[0]));
            binary.append(destBin.get(""));
            binary.append(jumpBin.get(help[1]));

        }
        return binary.toString();
    }
}
