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
        try {
            if (command.equals("add")) {
                bw.write("//" + command);
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("M=M-1");
                bw.newLine();
                bw.write("A=M");
                bw.newLine();
                bw.write("D=M");
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("M=M-1");
                bw.newLine();
                bw.write("A=M");
                bw.newLine();
                bw.write("D=D+M");
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("A=M");
                bw.newLine();
                bw.write("M=D");
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("M=M+1");
                bw.flush();
            }
            if (command.equals("sub"))
            {
                bw.write("//" + command);
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("M=M-1");
                bw.newLine();
                bw.write("A=M");
                bw.newLine();
                bw.write("D=M");
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("M=M-1");
                bw.newLine();
                bw.write("A=M");
                bw.newLine();
                bw.write("D=M-D");
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("A=M");
                bw.newLine();
                bw.write("M=D");
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("M=M+1");
                bw.flush();
            }
        }
        catch (Exception e)
        {

        }
    }

    void writePushPop(String type,String segment,int number)
    {
        //TODO: Write a method to write push/pop command in assembly language
        try
        {
            bw.write("//"+type+" "+ segment+" "+number);
            bw.newLine();
          /*  bw.write("@SP");
            bw.newLine();
            bw.write("M=M-1");
            bw.newLine();
            bw.write("D=M");
            bw.newLine();
            bw.write("M=M-1");
            bw.newLine();
            bw.write("D=D+M");
            bw.newLine();
            bw.write("M=D");
            bw.newLine();
            bw.write("@SP");
            bw.newLine();
            bw.write("M=M+1");*/


            if (type.equals("C_PUSH"))
            {

                if(segment.equals("constant"))
                {
                    bw.write("@"+number);
                    bw.newLine();
                    bw.write("D=A");
                    bw.newLine();
                    bw.write("@SP");
                    bw.newLine();
                    bw.write("A=M");
                    bw.newLine();
                    bw.write("M=D");
                    bw.newLine();
                    bw.write("@SP");
                    bw.newLine();
                    bw.write("M=M+1");
                    bw.newLine();
                    bw.flush();
                }

            }

        bw.flush();
        }
        catch (Exception e)
        {

        }

    }
}
