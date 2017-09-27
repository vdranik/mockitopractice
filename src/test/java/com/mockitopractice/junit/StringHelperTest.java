package com.mockitopractice.junit;

import com.mockitopractice.junit.StringHelper;
import org.junit.*;

import static org.junit.Assert.*;

public class StringHelperTest {

    private StringHelper stringHelper;

    @BeforeClass
    public static void initTestClass(){
        System.out.println("Init test class");
    }

    @Before
    public void init(){
        stringHelper = new StringHelper();
    }

    @Test
    public void testTruncateAInFirst2Positions_AInFirst2Positions(){
        //AACD => CD
        assertEquals("CD", stringHelper.truncateAInFirst2Positions("AACD"));
    }

    @Test
    public void testTruncateAInFirst2Positions_AInFirstPositions(){
        //ACD => CD
        assertEquals("CD", stringHelper.truncateAInFirst2Positions("ACD"));
    }

    @Test
    public void testTruncateAInFirst2Positions_WithoutA(){
        //CDEF => CDEF
        assertEquals("CDEF", stringHelper.truncateAInFirst2Positions("CDEF"));
    }

    @Test
    public void testTruncateAInFirst2Positions_AInLastPositions() {
        //CDAA => CDAA
        assertEquals("CDAA", stringHelper.truncateAInFirst2Positions("CDAA"));
    }

    @Test
    public void testAreFirstAndLastTwoCharactersTheSame_BasicNegativeScenatio() {
        //ABCD => false
        assertFalse(stringHelper.areFirstAndLastTwoCharactersTheSame("ABCD"));
    }

    @Test
    public void testAreFirstAndLastTwoCharactersTheSame_BasicPositiveScenatio() {
        //ABAB => true
        assertTrue(stringHelper.areFirstAndLastTwoCharactersTheSame("ABAB"));
    }

    @Test
    public void testAreFirstAndLastTwoCharactersTheSame_TwoCharacters() {
        //AB => true
        assertTrue(stringHelper.areFirstAndLastTwoCharactersTheSame("AB"));
    }

    @Test
    public void testAreFirstAndLastTwoCharactersTheSame_OneCharacter() {
        //A => false
        assertFalse(stringHelper.areFirstAndLastTwoCharactersTheSame("A"));
    }

    @After
    public void teardown(){
        stringHelper = null;
    }

    @AfterClass
    public static void finalizeTestClass(){
        System.out.println("Finalize test class");
    }
}