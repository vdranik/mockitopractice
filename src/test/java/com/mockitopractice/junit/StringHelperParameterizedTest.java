package com.mockitopractice.junit;

import com.mockitopractice.junit.StringHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StringHelperParameterizedTest {

    private StringHelper stringHelper;

    @Before
    public void init(){
        stringHelper = new StringHelper();
    }

//    @Test
//    public void testTruncateAInFirst2Positions_AInFirst2Positions() {
//        //AACD => CD
//        assertEquals("CD", stringHelper.truncateAInFirst2Positions("AACD"));
//    }
//
//    @Test
//    public void testTruncateAInFirst2Positions_AInFirstPositions() {
//        //ACD => CD
//        assertEquals("CD", stringHelper.truncateAInFirst2Positions("ACD"));
//    }
//
//    ......

    private String input;
    private String expectedOutput;

    public StringHelperParameterizedTest(String input, String expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Parameters
    public static Collection<String[]> testConditions(){
        String[][] expectedOutputs= {{"AACD", "CD"}, {"ACD", "CD"}, { "CDEF", "CDEF"}, {"CDAA", "CDAA"}};

        return Arrays.asList(expectedOutputs);
    }

    @Test
    public void testTruncateAInFirst2Positions() {
        assertEquals(expectedOutput, stringHelper.truncateAInFirst2Positions(input));
    }

    @After
    public void teardown(){
        stringHelper = null;
    }
}
