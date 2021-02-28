package ru.spbstu.telematics.java;

import java.io.File;
import java.io.IOError;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void mv(String[] args)
    {
        if(args.length != 0)
        {
            File dFile = new File(args[args.length - 1]);
            if (!dFile.isDirectory() && args.length > 2)
            {
                System.out.println("Неверные аргументы! Последний аргумент должен быть директорией!");
                return;
            }

            try {
                if (dFile.isDirectory()) {
                    for (int i = 0; i < args.length - 1; i++) {
                        File curFile = new File(args[i]);
                        curFile.renameTo(new File(dFile, curFile.getName()));
                    }
                } else {
                    {
                        File curFile = new File(args[0]);
                        curFile.renameTo(dFile);
                    }
                }
            } catch (IOError a)
            {
                System.out.println("Введите корректные названия файлов!");
            }
        }else System.out.println("Введите названия файлов!");

    }
    public static void main( String[] args )
    {
      //mv(new String[] {"textFile.txt", "renamedTextFile.txt"});
        mv(new String[] { "renamedTextFile.txt", "textFile.txt"});
    }
}