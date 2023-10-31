
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Linstruction {
    private HashMap<String,Integer> symbolsTable; //Deklaracja tablicy symboli

    /*Konstruktor klasy inicjuje tabele symboli z wbudowanymi symbolami języka hack assemmbler*/
    public Linstruction()
    {
        symbolsTable = new HashMap<>();
        symbolsTable.put("R0",0);
        symbolsTable.put("R1",1);
        symbolsTable.put("R2",2);
        symbolsTable.put("R3",3);
        symbolsTable.put("R4",4);
        symbolsTable.put("R5",5);
        symbolsTable.put("R6",6);
        symbolsTable.put("R7",7);
        symbolsTable.put("R8",8);
        symbolsTable.put("R9",9);
        symbolsTable.put("R10",10);
        symbolsTable.put("R11",11);
        symbolsTable.put("R12",12);
        symbolsTable.put("R13",13);
        symbolsTable.put("R14",14);
        symbolsTable.put("R15",15);
        symbolsTable.put("SP",0);
        symbolsTable.put("LCL",1);
        symbolsTable.put("ARG",2);
        symbolsTable.put("THIS",3);
        symbolsTable.put("THAT",4);
        symbolsTable.put("SCREEN",16384);
        symbolsTable.put("KBD",24576);

    }

    public void initTable(ArrayList<String> inst) throws IOException {

        String line ="";
        int instructionCounter = 0;
        int varAddress = 16;                    //zmenne w hack asemblerze są przypisywane od odresu 16
        String regex = "^\\([\\w\\W.]+\\)?$";   //regular expresion dla etykiet

        /*Pętla rozpoznająca etykiety w programie(etykieety dla skoków)*/
        for (int i =0;i< inst.size();i++)
        {
            line = inst.get(i); //pobranie instrukcji

            if (line.matches(regex) && !symbolsTable.containsKey(line.substring(1, line.length() - 1)))
                 //Jeżeli instrukcja jest etykietą (***) i nie występuje jeszcze w tabeli symboli to zostaje w niej
                // zapisana bez nawiasów() z wartością licznika komend.Jednak bez zwiększenia PC gdyż etykiety nie są
                // częścią programu
            {
                symbolsTable.put(line.substring(1, line.length() - 1), instructionCounter);
                continue;
            }
            instructionCounter++; //jeżeli nie jest to etykieta zwiększamy PC
        }

        /*Pętla rozpoznająca zmienne */
        for (int i =0;i< inst.size();i++)
        {
            line = inst.get(i);
            if (line.matches("^@[\\w.]+"))                                     //jeżeli instrukcja zaczyna się od @
            if (!(symbolsTable.containsKey(line.substring(1))) && !isNumber(line))  //a to co po @ nie jest liczbą i nie występuje w
            {                                                                       //tablicy symboli zostaje w niej zapisane
                symbolsTable.put(line.substring(1), varAddress);                    //od odresu 16 w górę i za każdym razem
                varAddress++;                                                       //zwiększamy PC

            }
        }
    }

    public boolean contaiSymbol(String key)
    {
        return symbolsTable.containsKey(key);
    }

    public String getSymbolValue(String key)
    {
        return  symbolsTable.get(key).toString();
    }

    private boolean isNumber(String s)
    {
        char[] charArray = s.substring(1).toCharArray();
        boolean isNumber = true;

        for (int i=0;i<charArray.length;i++)
        {
            if (!Character.isDigit(charArray[i]))
                return false;
        }
        return isNumber;
    }
}
