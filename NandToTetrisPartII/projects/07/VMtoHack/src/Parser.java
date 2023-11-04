import java.io.BufferedReader;
import java.io.FileReader;

public class Parser {

    private BufferedReader br;
    private FileReader fr;

    public Parser(FileReader fr)
    {
        this.br = new BufferedReader(fr);
    }


}
