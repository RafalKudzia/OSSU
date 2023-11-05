import java.io.*;

public class Parser {

    private BufferedReader br;
    private String command;


    public Parser(File fr) {

        try{
        this.br = new BufferedReader(new FileReader(fr));
        }
        catch (Exception e)
        {

        }

    }

    boolean hasMoreCommand()
    {

        return true;
    }

    void advance()
    {

    }

    String commandType(String command)
    {

    }

    String arg1()
    {

    }

    int arg2()
    {

    }




}
