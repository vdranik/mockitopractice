package com.mockitopractice.junit;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ArraysCompareTest {

    @Test
    public void testArraySort_RandomArray(){
        int[] numbers = {12, 3, 4, 1};
        int[] expected = {1, 3, 4, 12};

        Arrays.sort(numbers);
//        assertEquals(expected, numbers); //equals objects - red
        assertArrayEquals(expected, numbers); //green
    }

    @Test(expected = NullPointerException.class)
    public void testArraySort_NullArray(){
        int[] numbers = null;

        Arrays.sort(numbers);
    }

    @Test(timeout = 1000) //10 will be red
    public void testArraySort_Performance(){
        int array[] = {12, 23, 4};
        for(int i=1; i<=1000000; i++){
            array[0] = i;
            Arrays.sort(array);
        }
    }
}
