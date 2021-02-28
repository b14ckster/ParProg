package ru.spbstu.telematics.java;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;


public class AppTest 
    extends TestSuite
{
    @Test
    public void Test1() throws IOException {

        File f1 = new File("textFile.txt");
        f1.createNewFile();
        App.mv(new String[] {"textFile.txt", "renamedTextFile.txt"});

        File f2 = new File("renamedTextFile.txt");

        Assert.assertTrue("Не удалось переименовать файл ", f2.exists());
        f2.delete();
    }
}
