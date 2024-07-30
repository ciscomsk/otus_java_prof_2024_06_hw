package homework;

import homework.runner.TestRunner;
import homework.runner.annotations.After;
import homework.runner.annotations.Before;
import homework.runner.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

import static homework.runner.TestRunner.assertEquals;

public class TestClass {
    private static final Logger logger = LoggerFactory.getLogger(TestClass.class);

    @Before
    public void before1() {
        logger.info("before_1");
    }

    @Before
    public void before2() {
        logger.info("before_2");
    }

    @After
    public void after1() {
        logger.info("after_1");
    }

    @After
    public void after2() {
        logger.info("after_2");
    }

    @Test
    public void successCase() {
        logger.info("successCase");
        assertEquals(1, 1);
    }

    @Test
    public void failedCase() {
        logger.info("failedCase");
        assertEquals(1, 2);
    }

    @Test
    public void exceptionCase() {
        logger.info("exceptionCase");
        throw new RuntimeException("runtime exception");
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestRunner testRunner = new TestRunner("homework.TestClass");
        testRunner.runTests();
    }
}
