package com.jefff.gr.homework.console.output;

public class StrPrintWriter implements IPrintWriter
{
    StringBuilder sb = new StringBuilder();
    String closedStr = null;

    @Override
    public void println(String s)
    {
        sb.append(s);
        sb.append("\n");
    }

    @Override
    public void print(String s)
    {
        sb.append(s);
    }

    @Override
    public void close()
    {
        closedStr = sb.toString();
    }

    public String getOutput()
    {
        return closedStr;
    }
}
