package com.tw.arena;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsolePrinterTest {

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp(){
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void shouldPrintSomethingToOutputStream(){
        ConsolePrinter printer = new ConsolePrinter();

        printer.print("this just is a test");

        assertThat(outputStream.toString(), is("this just is a test\r\n"));
    }

}
