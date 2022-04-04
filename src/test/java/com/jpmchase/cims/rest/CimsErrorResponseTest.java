package com.jpmchase.cims.rest;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

public class CimsErrorResponseTest {

    @Test
    public void itShouldPassPojoTest() {
        Assertions.assertPojoMethodsFor(CimsErrorResponse.class).quickly().testing(Method.CONSTRUCTOR, Method.SETTER, Method.GETTER, Method.EQUALS).areWellImplemented();
    }

}
