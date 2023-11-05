import java.io.BufferedWriter;
import java.io.FileWriter;

public class CodeWriter
{
    BufferedWriter bw;
    public CodeWriter(FileWriter fw)
    {
            bw=new BufferedWriter(fw);
    }
}
