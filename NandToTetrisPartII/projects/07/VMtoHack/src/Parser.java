import java.io.*;

public class Parser {

    private BufferedReader br;
    private String command;
    String typeOfCommand;


    public Parser(File fr) {

        try{
        this.br = new BufferedReader(new FileReader(fr));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    boolean hasMoreCommand()
    {
        try
        {
            while ((command= br.readLine()) !=null)
            {

                if (command.isEmpty())
                    continue;
                command = command.trim();

                if (command.startsWith("//"))
                    continue;

                if (command.startsWith("/*")) {
                    while ((command = br.readLine()) != null)
                    {
                        command = command.trim();
                        if (command.endsWith("*/"))
                            break;
                    }
                    continue;
                }

                return true;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
     }

    String advance()
    {
        return command;
    }

    String commandType(String command)
    {
        if (command.contains("push"))
            typeOfCommand = "C_PUSH";
        else if (command.contains("pop"))
            typeOfCommand = "C_POP";
        else
            typeOfCommand="C_ARITHMETIC";


        return typeOfCommand;
    }

    String arg1()
    {
        if (typeOfCommand.equals("C_ARITHMETIC"))
            return command;
        else
            return command.split(" ")[1];
    }

    int arg2()
    {
        return Integer.parseInt(command.split(" ")[2]);
    }




}
