package com.jefff.gr.homework.mapper;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static org.junit.Assert.*;

public class SplitterTest {

    private static final List<String> EXPECTED = Arrays.asList("a", "b", "c");
    @Test
    public void splitOnNullReturnsEmptyList() {
        Splitter sut = new Splitter();
        List<String> parts = sut.split(null);
        Assert.assertEquals(0, parts.size());
    }

    @Test
    public void splitOnEmptyStringReturnsEmptyList() {
        Splitter sut = new Splitter();
        List<String> parts = sut.split("");
        Assert.assertEquals(0, parts.size());
    }

    @Test
    public void splitOnDelimitersOnlyReturnsEmptyList() {
        Splitter sut = new Splitter();
        List<String> parts = sut.split(" |,");
        Assert.assertEquals(0, parts.size());
    }
    @Test
    public void spaceDelimiter() {
        runTest("a b c");
        runTest("a   b                   c");
    }

    @Test
    public void commaDelimiter() {
        runTest("a,b,c");
        runTest("a,,,b,,,,,,,,,,,,,,,,,, c");

    }
    @Test
    public void pipeDelimiter() {
        runTest("a|b|c");
        runTest("a|||b|||||||||||||||||| c");
    }

    @Test
    public void mixedDelimiter() {
        runTest("a , b , c");
        runTest("a | b | c");
        runTest("a , b | c");

    }


    public void runTest(final String input)
    {
        Splitter sut = new Splitter();
        List<String> parts = sut.split(input);
        Assert.assertEquals(EXPECTED, parts);

    }
}