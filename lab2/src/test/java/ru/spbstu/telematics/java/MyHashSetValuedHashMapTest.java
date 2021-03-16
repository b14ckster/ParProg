package ru.spbstu.telematics.java;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class MyHashSetValuedHashMapTest
    extends TestSuite
{
    private static MyHashSetValuedHashMap<Integer, String> myHashSetValuedHashMap =
            new MyHashSetValuedHashMap<>();
    private static HashSetValuedHashMap hashSetValuedHashMap =
            new HashSetValuedHashMap<Integer, String>();

   @Before
   public void Init()
   {
        hashSetValuedHashMap.clear();
        hashSetValuedHashMap.put(1, "first element");
        hashSetValuedHashMap.put(2, "second element");
        hashSetValuedHashMap.put(3, "third element");
        hashSetValuedHashMap.put(1, "first element");

        myHashSetValuedHashMap.clear();
        myHashSetValuedHashMap.put(1, "first element");
        myHashSetValuedHashMap.put(2, "second element");
        myHashSetValuedHashMap.put(3, "third element");
        myHashSetValuedHashMap.put(1, "first element");
   }

    @Test
    public void getTest(){
        Assert.assertEquals(hashSetValuedHashMap.get(3), myHashSetValuedHashMap.get(3));
        Assert.assertEquals(hashSetValuedHashMap.get(1), myHashSetValuedHashMap.get(1));
    }

    @Test
    public void removeMappingTest(){
        myHashSetValuedHashMap.removeMapping(2, "second element");
        hashSetValuedHashMap.removeMapping(2, "second element");
        Assert.assertEquals(hashSetValuedHashMap.containsMapping(2, "second element"), myHashSetValuedHashMap.containsMapping(2, "second element"));
    }

   @Test
    public void sizeTest()
    {
        hashSetValuedHashMap.put(3, "third element");
        myHashSetValuedHashMap.put(3, "third element");
        Assert.assertEquals(hashSetValuedHashMap.size(), myHashSetValuedHashMap.size());
    }

    @Test
    public void containsKeyTest()
    {
        Assert.assertEquals(hashSetValuedHashMap.containsKey(2), myHashSetValuedHashMap.containsKey(2));
    }
    @Test
    public void iteratorTest(){
        MapIterator<Integer, String> myHashSetValuedHashMapIterator =
                myHashSetValuedHashMap.mapIterator();
        MapIterator<Integer, String> hashSetValuedHashMapIterator =
                hashSetValuedHashMap.mapIterator();
        if (hashSetValuedHashMapIterator.hasNext()&&myHashSetValuedHashMapIterator.hasNext()){
            hashSetValuedHashMapIterator.next();
            myHashSetValuedHashMapIterator.next();
        }

        Assert.assertEquals(hashSetValuedHashMapIterator.getValue(), myHashSetValuedHashMapIterator.getValue());
    }



}
