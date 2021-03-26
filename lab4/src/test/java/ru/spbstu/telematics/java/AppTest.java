package ru.spbstu.telematics.java;

import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;


public class AppTest 
    extends TestSuite
{
    @Test
    public void femTest(){
        FEM fem = new FEM(10, 3, 0, 1, 1, 0);
        try {
            BigDecimal[] res = fem.solve();
            for (int i=0; i<11; i++)
                System.out.println(res[i]);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Assert.assertTrue(true);
    }
}
