package fr.zarkin.project;

import java.io.IOException;

public class Main {
    
    public static void main(String[] args) throws IOException
    {
        HTTPLoader.getText("https://hwpublic.blob.core.windows.net/dataengineering/measurements.txt");
    }
}