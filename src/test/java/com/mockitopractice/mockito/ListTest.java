package com.mockitopractice.mockito;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ListTest {

    private List list;

    @Before
    public void init(){
        list = mock(List.class);
    }

    @Test
    public void letsMockListSize() {
        //Given - setup
        //List list = mock(List.class);
        when(list.size()).thenReturn(10);
        //When - actual method call

        //Then - asserts
        assertEquals(10, list.size());
    }

    @Test
    public void letsMockListSizeWithMultipleReturnValues() {

        when(list.size()).thenReturn(10).thenReturn(20);
        assertEquals(10, list.size()); // First Call
        assertEquals(20, list.size()); // Second Call
    }

    @Test
    public void letsMockListGet() {

        when(list.get(0)).thenReturn("SOME_VALUE");
        assertEquals("SOME_VALUE", list.get(0));
        assertNull(list.get(1));
    }

    @Test
    public void letsMockListGetWithAny() {

        when(list.get(anyInt())).thenReturn("SOME_VALUE");
        // If you are using argument matchers, all arguments
        // have to be provided by matchers.
        assertEquals("SOME_VALUE", list.get(0));
        assertEquals("SOME_VALUE", list.get(1));
    }

    @Test(expected = RuntimeException.class)
    public void letsMockList_throwException() {

        when(list.get(anyInt())).thenThrow(new RuntimeException("Something wrong"));
        list.get(anyInt());
    }

    @Test(expected = RuntimeException.class)
    public void letsMockList_mixingUp() {

        when(list.subList(anyInt(), 5)).thenThrow(new RuntimeException("Something wrong"));
        list.get(anyInt());
    }

    @Test
    public void bddAliases_UsingGivenWillReturn() {
        List<String> list = mock(List.class);

        //given
        given(list.get(anyInt())).willReturn("SOME_VALUE");

        //then
        assertThat("SOME_VALUE", is(list.get(0)));
        assertThat("SOME_VALUE", is(list.get(0)));
    }

    @After
    public void teardown(){
        list = null;
    }
}


