package com.mockitopractice.spy;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

//   Mock vs Spy.
//
//   In a mock all methods are stubbed and return "smart return types". This means that calling any method on
//   a mocked class will do nothing unless you specify behaviour.
//
//   In a spy the original functionality of the class is still there but you can validate method invocations
//   in a spy and also override method behaviour.
//
//   A quick example:

public class SpyExample {

    static class TestClass {

        public String getThing() {
            return "Thing";
        }

        public String getOtherThing() {
            return getThing();
        }
    }

    public static void main(String[] args) {
        final TestClass testClass = spy(new TestClass());
        when(testClass.getThing()).thenReturn("Some Other thing");
        System.out.println(testClass.getOtherThing());
    }

//    Output is:
//    Some Other thing

}
