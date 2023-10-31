public class Ainstruction {
                        //regex sprawdzający czy wartość mieści się w przedziale 0-32676[adresowalna piamięć RAM]
    String aInstRegex = "^@(([0-2]?[0-9]{1,4})|(3[0-1][0-9]{3})|(32[0-6][0-9]{2})|(327[0-5][0-9])|(3276[0-7]))$";

    public boolean isAinstruction(String arg) //metoda sprawdzająca czy to instrukcja A
    {
        return arg.matches(aInstRegex);

    }

    public String binaryAinstruction(String arg) //metoda zwracająca postać binarną instrukcji
    {

       arg = String.format("%16s", Integer.toBinaryString(Integer.parseInt(arg.substring(1))));
       arg = arg.replace(' ','0');

       return arg;

    }
}
