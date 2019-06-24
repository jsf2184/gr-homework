package com.jefff.gr.homework.console.output;

import org.apache.log4j.Logger;

import java.io.PrintWriter;

public class RealPrintWriter implements IPrintWriter
{
    private static final Logger log = Logger.getLogger(RealPrintWriter.class);

    private String fileName;
    PrintWriter printWriter;

    public RealPrintWriter(String fileName, PrintWriter printWriter)
    {
        this.fileName = fileName;
        this.printWriter = printWriter;
    }

    @Override
    public void println(String s)
    {
        printWriter.println(s);
    }

    @Override
    public void print(String s)
    {
        printWriter.print(s);
    }

    @Override
    public void close()
    {
        printWriter.close();
        log.info(String.format("Successfully wrote output file: %s", fileName));
    }
}
