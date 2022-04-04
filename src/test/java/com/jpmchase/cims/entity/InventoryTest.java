package com.jpmchase.cims.entity;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

public class InventoryTest {

    @Test
    public void itShouldPassPojoTest() {
        Assertions.assertPojoMethodsFor(Inventory.class).quickly().testing(Method.CONSTRUCTOR, Method.SETTER, Method.GETTER, Method.EQUALS, Method.TO_STRING).areWellImplemented();
    }

}
