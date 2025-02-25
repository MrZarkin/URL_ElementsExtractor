import java.io.IOException;

public class Main {
    
    public static void main(String[] args) throws IOException
    {
        HTTPLoader.getList("https://hwpublic.blob.core.windows.net/dataengineering/measurements.txt");
    }
}