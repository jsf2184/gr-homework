package com.jefff.gr.homework.console.output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterFactory
{
    public IPrintWriter createPrintWriter(String fileName) throws IOException
    {
        final FileWriter fileWriter = new FileWriter(fileName);
        IPrintWriter result = new RealPrintWriter(fileName, new PrintWriter(fileWriter));
        return result;
    }
}
