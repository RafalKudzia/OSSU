import java.io.*;

public class CodeWriter
{
    BufferedWriter bw;
    public CodeWriter(File fw)
    {
        try{
            bw=new BufferedWriter(new FileWriter(fw));
        }
        catch (Exception e)
        {
            System.out.println();
        }
    }

    void writeArithmetic(String command)
    {
        //TODO: Write a method to write arithmetic command in assembly language
    }

    void writePushPop(String command)
    {
        //TODO: Write a method to write push/pop command in assembly language
        try
        {
            bw.write("//"+command);

            if (command.split(" ")[0].equals("push"))
            {

                if(command.split(" ")[1].equals("constant"))
                {
                    bw.write("@"+command.split(" ")[2]);
                    bw.write("D=A");
                  //----  bw.write("@"+);
                }

            }
        }
        catch (Exception e)
        {

        }


    }
}
