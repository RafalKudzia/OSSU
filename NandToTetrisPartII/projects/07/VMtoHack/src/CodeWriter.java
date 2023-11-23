import java.io.*;

public class CodeWriter
{
    BufferedWriter bw;
    private static  int iForEndLabel = 0;
    private static  int iForIfLabel = 0;
    private static String staticVariableName;

    public CodeWriter(File fw)
    {
        try{
            bw=new BufferedWriter(new FileWriter(fw));
            staticVariableName = fw.getName().split("\\.")[0];
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
            if (!(command.equals("neg") || command.equals("not")))
            {
                decrementStackPointer();
                getFromStackToD();
                decrementStackPointer();
                getFromStackToA();

                if (command.equals("eq") || command.equals("lt") || command.equals("gt"))
                {
                    jumpToIfLabel(command);
                    pushFALSEtoTheStack();
                    jumpToEndLabel();
                    insertIfLabel();
                    pushTRUEtoTheStack();
                    insertEndLabel();
                }
                else
                {
                    compute(command);
                }
            }
            else
            {
                decrementStackPointer();
                getFromStackToA();
                compute(command);
            }
            incrementStackPointer();
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
            writeCommandTypeComment(type.toLowerCase().substring(2)+" "+segment+" "+number);

            if (type.equals("C_PUSH"))
            {

                if (segment.equals("static"))
                {
                    bw.write("@"+staticVariableName+"."+number);
                    bw.newLine();
                    bw.write("D=M");
                    bw.newLine();
                   // pushDtoTheStack();
                    //incrementStackPointer();

                }
                else if (segment.equals("pointer"))
                {
                    if (number == 0)
                    {
                        bw.write("@THIS");
                        bw.newLine();
                        bw.write("D=M");
                        bw.newLine();
                    }
                    if (number == 1)
                    {
                        bw.write("@THAT");
                        bw.newLine();
                        bw.write("D=M");
                        bw.newLine();

                    }
                    pushDtoTheStack();
                    incrementStackPointer();
                }
                else if (!segment.equals("constant")) {
                        getFromMemoryToD(segment, number);
                    } else {
                        bw.write("@" + number);
                        bw.newLine();
                        bw.write("D=A");
                        bw.newLine();
                    }
                    pushDtoTheStack();
                    incrementStackPointer();

            }
            if (type.equals("C_POP")) {
                if (segment.equals("static")) {
                    decrementStackPointer();
                    getFromStackToD();
                    bw.write("@"+staticVariableName+"."+number);
                    bw.newLine();
                    bw.write("M=D");
                    bw.newLine();
                }
                else if (segment.equals("pointer")) {
                        decrementStackPointer();
                        getFromStackToD();
                        if (number == 0) {
                            bw.write("@THIS");
                            bw.newLine();
                            bw.write("M=D");
                            bw.newLine();

                        }
                        if (number == 1) {
                            bw.write("@THAT");
                            bw.newLine();
                            bw.write("M=D");
                            bw.newLine();
                        }
                    } else {
                        setMemoryAddress(segment, number);
                        decrementStackPointer();
                        pushFromStackToMemory();
                    }
                }

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

    private void pushFromStackToMemory()
    {
        try {
            getFromStackToD();
            bw.write("@R13");
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

    private void setMemoryAddress(String seg,int index)
    {
        try {
            if (seg.equals("local")) {
                bw.write("@LCL");
                bw.newLine();
            }
            if (seg.equals("argument")) {
                bw.write("@ARG");
                bw.newLine();
            }
            if (seg.equals("this")) {

                bw.write("@THIS");
                bw.newLine();
            }
            if (seg.equals("that")) {

                bw.write("@THAT");
                bw.newLine();

            }

            if (seg.equals("temp")) {

                bw.write("@5");
                bw.newLine();
                bw.write("D=A");
                bw.newLine();
            }
            else {

                bw.write("D=M");
                bw.newLine();
            }
            bw.write("@" + index);
            bw.newLine();
            bw.write("D=A+D");
            bw.newLine();
            bw.write("@R13");
            bw.newLine();
            bw.write("M=D");
            bw.newLine();

        }
        catch (Exception e)
        {

        }
    }
    private void getFromMemoryToD(String seg,int index)
    {
        try {
            if (seg.equals("local")) {

                bw.write("@LCL");
                bw.newLine();
            }
            if (seg.equals("argument")) {
                bw.write("@ARG");
                bw.newLine();
            }
            if (seg.equals("this")) {
                bw.write("@THIS");
                bw.newLine();
            }
            if (seg.equals("that")) {
                bw.write("@THAT");
                bw.newLine();
            }
            if (seg.equals("temp")) {
                bw.write("@5");
                bw.newLine();
                bw.write("D=A");
                bw.newLine();
            }
            else {
                bw.write("D=M");
                bw.newLine();
            }
            bw.write("@" + index);
            bw.newLine();
            bw.write("A=A+D");
            bw.newLine();
            bw.write("D=M");
            bw.newLine();
        }
        catch (Exception e){

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

    private void incrementStackPointer()
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

    private void decrementStackPointer()
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
            bw.write("@SP");
            bw.newLine();
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
    private void getFromStackToA()
    {
        try {
            bw.write("@SP");
            bw.newLine();
            bw.write("A=M");
            bw.newLine();
        }
        catch (Exception e)
        {

        }
    }

    private void insertEndLabel()
    {

        try {
            bw.write("(END"+iForEndLabel+")");
            bw.newLine();
            iForEndLabel++;
        }
        catch (Exception e)
        {

        }
    }

    private void insertIfLabel()
    {
        try {
            bw.write("(IF"+iForIfLabel+")");
            bw.newLine();
            iForIfLabel++;
        }
        catch (Exception e)
        {

        }
    }

    private void pushTRUEtoTheStack()
    {
        try {
            bw.write("@SP");
            bw.newLine();
            bw.write("A=M");
            bw.newLine();
            bw.write("M=-1");
            bw.newLine();

        }
        catch (Exception e)
        {

        }
    }

    private void pushFALSEtoTheStack()
    {
        try {
            bw.write("@SP");
            bw.newLine();
            bw.write("A=M");
            bw.newLine();
            bw.write("M=0");
            bw.newLine();
        }
        catch (Exception e)
        {

        }
    }

    private void insertIfLabelAddress()
    {
        try {
            bw.write("@IF"+iForIfLabel);
            bw.newLine();
        }
        catch (Exception e)
        {

        }
    }

    private void jumpToIfLabel(String cmd)
    {
        try {
            bw.write("D=M-D");
            bw.newLine();
            insertIfLabelAddress();
            if (cmd.equals("eq"))
            {
                bw.write("D;JEQ");
                bw.newLine();
            }
            if (cmd.equals("gt")) {

                bw.write("D;JGT");
                bw.newLine();
            }
            if (cmd.equals("lt")) {
                bw.write("D;JLT");
                bw.newLine();
            }
        }
        catch (Exception e)
        {

        }
    }

    private void jumpToEndLabel()
    {
        try {
            bw.write("@END"+iForEndLabel);
            bw.newLine();
            bw.write("0;JMP");
            bw.newLine();
        }
        catch (Exception e)
        {

        }
    }

    private void compute(String cmd)
    {
        try {
            if (cmd.equals("add")) {
                bw.write("D=D+M");
                bw.newLine();
            }
            if (cmd.equals("sub"))
            {
                bw.write("D=M-D");
                bw.newLine();
            }
            if (cmd.equals("neg"))
            {
                bw.write("D=-M");
                bw.newLine();
            }
            if (cmd.equals("not"))
            {
                bw.write("D=!M");
                bw.newLine();
            }
            if (cmd.equals("or"))
            {
                bw.write("D=D|M");
                bw.newLine();
            }
            if (cmd.equals("and"))
            {
                bw.write("D=D&M");
                bw.newLine();
            }
            pushDtoTheStack();
        }
        catch (Exception e)
        {

        }
    }
}
