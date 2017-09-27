package com.mockitopractice.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ArraysCompareTest.class, StringHelperTest.class, StringHelperParameterizedTest.class})
public class AllTestsSuite {
}
