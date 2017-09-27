package com.mockitopractice.powermock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)         //need to for PowerMock using
@PrepareForTest({ UtilityClass.class}) //initialize class with static method
public class PowerMockitoMockingStaticMethodTest {

    @Mock
    private Dependency dependencyMock;

    @InjectMocks
    private SystemUnderTest systemUnderTest;


//    Classic mockito with @RunWith(mockitoJUnitRunner.class)
//
//    @Test
//    public void testRetrieveTodosRelatedToSpring_usingAMock(){
//        List<Integer> stats = Arrays.asList(1,2,3);
//        when(dependencyMock.retrieveAllStats()).thenReturn(stats);
//        //BUT!
//        when(UtilityClass.staticMethod(6)).thenReturn(150); It will still return exception
//        systemUnderTest.methodCallingAStaticMethod();
//    }


    @Test
    public void powerMockito_MockingAStaticMethodCall() {

        when(dependencyMock.retrieveAllStats()).thenReturn(
                Arrays.asList(1, 2, 3));

        PowerMockito.mockStatic(UtilityClass.class);
        when(UtilityClass.staticMethod(anyLong())).thenReturn(150);

        assertEquals(150, systemUnderTest.methodCallingAStaticMethod());

        //To verify a specific method call
        //First : Call PowerMockito.verifyStatic()
        //Second : Call the method to be verified
        PowerMockito.verifyStatic();
        UtilityClass.staticMethod(1 + 2 + 3);

        // verify exact number of calls
        //PowerMockito.verifyStatic(Mockito.times(1));

    }
}

