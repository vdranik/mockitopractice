package com.mockitopractice.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SpyTest {

    @Test
    public void creatingASpyOnArrayList() {

        //MOCK
        List<String> arrayListMock = mock(ArrayList.class); // - mocks return default value
        //mock doesn't get any business logic from
        stub(arrayListMock.size()).toReturn(5);
        assertEquals(5, arrayListMock.size());
        //but
        arrayListMock.add("new 6th value"); // we are inserting into a mock!
        assertEquals(5, arrayListMock.size()); // it will be 5, add() doesn't work


        //==================================================================================
        //MOCKITO NOT ALLOW STUBBING FINAL, STATIC AND PRIVATE METHODS!
//        Cannot mock static methods
//        Cannot mock constructors
//        Cannot mock equals(), hashCode(). Firstly, you should not mock those methods. Secondly, Mockito defines
//        and depends upon a specific implementation of these methods. Redefining them might break Mockito.
//        Mocking is only possible on VMs that are supported by Objenesis. Don't worry, most VMs should work just fine.
//        Spying on real methods where real implementation references outer Class via OuterClass.this
//        is impossible. Don't worry, this is extremely rare case.

        //MOCKITO promotes good design

        // words: stub() when() given() have the same sence
        // differences only in syntactic stylistics (for example given() for BDD stylistics)
        //==================================================================================


        //SPY (also call Partial Mock)
        //A Spy gets all logic from the class
        //Spy is a proxy object
        List<String> listSpy = spy(ArrayList.class);
        listSpy.add("Ranga");
        listSpy.add("Carl");

        verify(listSpy).add("Ranga");
        verify(listSpy).add("Carl");

        assertEquals(2, listSpy.size());
        assertEquals("Ranga", listSpy.get(0));
    }

    @Test
    public void creatingASpyOnArrayList_overridingSpecificMethods() {
        List<String> listSpy = spy(ArrayList.class);
        assertEquals(0, listSpy.size());

        listSpy.add("Ranga");
        listSpy.add("Carl");
        assertEquals(2, listSpy.size()); //it was added 2 elements
        listSpy.remove("Carl");
        assertEquals(1, listSpy.size());

        //override class logic in size() method
        stub(listSpy.size()).toReturn(-1);

        assertEquals(-1, listSpy.size()); //size() was overrided (stubed)
        //but
        assertEquals("Ranga", listSpy.get(0)); //was called a real class method

        // @Spy Annotation
    }

}
