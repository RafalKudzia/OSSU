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
            writeCommandTypeComment(command);
            if (command.equals("add")) {
                decraseStackPointer();
                getFromStackToD();
                decraseStackPointer();
                getFromMemoryToA();
                bw.write("D=D+M");
                bw.newLine();
                pushDtoTheStack();
            }
            if (command.equals("sub"))
            {
                decraseStackPointer();
                getFromStackToD();
                decraseStackPointer();
                getFromMemoryToA();
                bw.write("D=M-D");
                bw.newLine();
                pushDtoTheStack();
            }
            if (command.equals("neg"))
            {
                decraseStackPointer();
                getFromMemoryToA();
                bw.write("D=-M");
                bw.newLine();
                pushDtoTheStack();
            }
            if (command.equals("eq"))
            {
                decraseStackPointer();
                getFromStackToD();
                decraseStackPointer();
                getFromMemoryToA();
                bw.write("D=M-D");
                bw.newLine();
                bw.write("@IF");
                bw.newLine();
                bw.write("D;JEQ");
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("A=M");
                bw.newLine();
                bw.write("M=0");
                bw.newLine();
                bw.write("@END");
                bw.newLine();
                bw.write("0;JMP");
                bw.newLine();
                bw.write("(IF)");
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("A=M");
                bw.newLine();
                bw.write("M=-1");
                bw.newLine();
                bw.write("(END)");
                bw.newLine();
            }
            if (command.equals("lt"))
            {
                decraseStackPointer();
                getFromStackToD();
                decraseStackPointer();
                getFromMemoryToA();
                bw.write("D=M-D");
                bw.newLine();
                bw.write("@IF");
                bw.newLine();
                bw.write("D;JEQ");
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("A=M");
                bw.newLine();
                bw.write("M=0");
                bw.newLine();
                bw.write("@END");
                bw.newLine();
                bw.write("0;JMP");
                bw.newLine();
                bw.write("(IF)");
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("A=M");
                bw.newLine();
                bw.write("M=-1");
                bw.newLine();
                bw.write("(END)");
                bw.newLine();
            }
            if (command.equals("gt"))
            {
                decraseStackPointer();
                getFromStackToD();
                decraseStackPointer();
                getFromMemoryToA();
                bw.write("D=M-D");
                bw.newLine();
                bw.write("@IF");
                bw.newLine();
                bw.write("D;JGT");
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("A=M");
                bw.newLine();
                bw.write("M=0");
                bw.newLine();
                bw.write("@END");
                bw.newLine();
                bw.write("0;JMP");
                bw.newLine();
                bw.write("(IF)");
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("A=M");
                bw.newLine();
                bw.write("M=-1");
                bw.newLine();
                bw.write("(END)");
                bw.newLine();
            }
            if (command.equals("not"))
            {
                decraseStackPointer();
                getFromMemoryToA();
                bw.write("D=!M");
                bw.newLine();
                pushDtoTheStack();
            }
            if (command.equals("or"))
            {
                decraseStackPointer();
                getFromStackToD();
                decraseStackPointer();
                getFromMemoryToA();
                bw.write("D=D|M");
                bw.newLine();
                pushDtoTheStack();
            }
            if (command.equals("and"))
            {
                decraseStackPointer();
                getFromStackToD();
                decraseStackPointer();
                getFromMemoryToA();
                bw.write("D=D&M");
                bw.newLine();
                pushDtoTheStack();
            }
            incraseStackPointer();
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
            writeCommandTypeComment(type+" "+segment+" "+number);
          //  bw.write("//**********"+type+" "+ segment+" "+number);
          //  bw.newLine();

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

    void close()
    {
        try
        {
            bw.close();
        }
        catch (Exception e)
        {

        }
    }

    private void writeCommandTypeComment(String comStr)
    {
        try
        {
            bw.write("//**********" + comStr);
            bw.newLine();
        }
        catch (Exception e)
        {

        }

    }

    private void incraseStackPointer()
    {
        try
        {
            bw.write("@SP");
            bw.newLine();
            bw.write("M=M+1");
            bw.newLine();
        }
        catch (Exception e)
        {

        }
    }

    private void decraseStackPointer()
    {
        try {
            bw.write("@SP");
            bw.newLine();
            bw.write("M=M-1");
            bw.newLine();
        }
        catch (Exception e)
        {

        }
    }

    private void getFromStackToD()
    {
        try {
            bw.write("A=M");
            bw.newLine();
            bw.write("D=M");
            bw.newLine();
        }
        catch (Exception e)
        {

        }
    }

    private  void pushDtoTheStack()
    {
        try {
            bw.write("@SP");
            bw.newLine();
            bw.write("A=M");
            bw.newLine();
            bw.write("M=D");
            bw.newLine();
        }
        catch (Exception e)
        {

        }
    }
    private void getFromMemoryToA()
    {
        try {
            bw.write("A=M");
            bw.newLine();
        }
        catch (Exception e)
        {

        }
    }
}
