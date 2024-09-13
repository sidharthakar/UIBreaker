package UtilityManager;


import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AssertUtility {

    public static <T> void assertEquals(T actual, T expected) {
        Assertions.assertThat(actual).isEqualTo(expected);
        Allure.step((String) expected, (Status) actual);
    }
    public static <T> void assertNotNull(T actual) {
        Assertions.assertThat(actual).isNotNull();
        Allure.step((String) actual + " -:Value is notnull");
    }
    public static <T> void assertStringContains(String actual, String substring) {
        Assertions.assertThat(actual).contains(substring);
        Allure.step(actual+"_Substring Value is "+substring +" _String contains the substring" );
    }
    public static <T> void assertCollectionContains(Iterable<T> collection, T element) {
        Assertions.assertThat(collection).contains(element);
        Allure.step(collection + "_contains Value is " + element);
    }
    public static void assertGreaterThan(int actual, int expected) {
        Assertions.assertThat(actual).isGreaterThan(expected);
        Allure.step(actual + "_contains Value is " + expected);
    }
    public static void assertConditionIsTrue(boolean condition) {
        Assertions.assertThat(condition).isTrue();
        Allure.step("Contains value is " + true);
    }
    public static void assertConditionIsFalse(boolean condition) {
        Assertions.assertThat(condition).isFalse();
        Allure.step("Contains value is " + false);
    }
    public class softAssert{
        ThreadLocal<SoftAssert> softAssertThreadLocal = ThreadLocal.withInitial(SoftAssert::new);

        public void assertEquals(Objects actual, Objects expected) {
            softAssertThreadLocal.get().assertEquals(actual, expected);
        }

        public void assertNotNull(Objects actual) {
            softAssertThreadLocal.get().assertNotNull(actual);
        }

        public void assertConditionIsTrue(boolean condition) {
            softAssertThreadLocal.get().assertTrue(condition);
        }
        public void assertNotSame(Objects actual, Objects expected) {
            softAssertThreadLocal.get().assertNotSame(actual, expected);
        }
        @AfterMethod
        public void assertAll() {
            softAssertThreadLocal.get().assertAll();
            softAssertThreadLocal.remove();
        }

    }

    }
