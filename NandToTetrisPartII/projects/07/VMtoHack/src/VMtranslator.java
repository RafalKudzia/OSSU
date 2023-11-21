import java.io.*;

public class VMtranslator {

    public Parser parser;
    public CodeWriter codeWriter;


    public static void main(String[] args) {
        File filePath;

        if (args[0].endsWith(".vm"));
        {
            filePath = new File(args[0]);
            VMtranslator vmt = new VMtranslator();
            vmt.parseCommand(filePath);
        }
    }

    private void parseCommand(File file)
    {
        parser = new  Parser(file);
        codeWriter = new CodeWriter(new File(file.getName().split("\\.")[0]+".asm"));

        String currentCommand;
        String commandType;

        while (parser.hasMoreCommand())
        {
            currentCommand =parser.advance();
            commandType = parser.commandType(currentCommand);

            if(commandType.equals("C_PUSH") || commandType.equals("C_POP"))
            {
                codeWriter.writePushPop(commandType, parser.arg1(), parser.arg2());

            }
            if (commandType.equals("C_ARITHMETIC"))
            {
                codeWriter.writeArithmetic(parser.arg1());
            }
        }
        codeWriter.close();

    }

}
