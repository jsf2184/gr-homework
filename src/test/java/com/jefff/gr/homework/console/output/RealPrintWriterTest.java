package com.jefff.gr.homework.console.output;

import org.junit.Test;

import java.io.PrintWriter;

import static org.mockito.Mockito.*;

public class RealPrintWriterTest
{

    PrintWriter printWriter;

    @Test
    public void println()
    {
        RealPrintWriter realPrintWriter = create();
        realPrintWriter.println("abc");
        verify(printWriter, times(1)).println("abc");
    }

    @Test
    public void print()
    {
        RealPrintWriter realPrintWriter = create();
        realPrintWriter.print("abc");
        verify(printWriter, times(1)).print("abc");

    }

    @Test
    public void close()
    {
        RealPrintWriter realPrintWriter = create();
        realPrintWriter.close();
        verify(printWriter, times(1)).close();

    }

    RealPrintWriter create()
    {
        printWriter = mock(PrintWriter.class);
        RealPrintWriter realPrintWriter = new RealPrintWriter("filename", printWriter);
        return realPrintWriter;
    }
}