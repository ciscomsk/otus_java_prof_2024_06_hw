package homework.runner;

import homework.runner.annotations.After;
import homework.runner.annotations.Before;
import homework.runner.annotations.Test;
import homework.runner.exceptions.AssertionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestRunner {
    private static final Logger logger = LoggerFactory.getLogger(TestRunner.class);

    private final String testClass;
    private int total;
    private int passed;
    private int failed;
    private final List<Method> beforeMethods;
    private final List<Method> testMethods;
    private final List<Method> afterMethods;

    public TestRunner(String testClass) {
        this.testClass = testClass;
        total = 0;
        passed = 0;
        failed = 0;
        beforeMethods = new ArrayList<>();
        testMethods = new ArrayList<>();
        afterMethods = new ArrayList<>();
    }

    public static void assertEquals(Object expected, Object actual) {
        boolean objectsAreEqual = Objects.equals(expected, actual);
        if (!objectsAreEqual) {
            throw new AssertionException("expected: " + expected + ", actual: " + actual);
        }
    }

    public void runTests() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> testClass = Class.forName(this.testClass);
        inspectClass(testClass);

        for (Method test : testMethods) {
            Object instance = testClass.getDeclaredConstructor().newInstance();
            try {
                invokeMethods(beforeMethods, instance);
                test.invoke(instance);
                invokeMethods(afterMethods, instance);
                total++;
                passed++;
            } catch (Exception ex) {
                invokeMethods(afterMethods, instance);
                total++;
                failed++;
                logger.warn(ex.getCause().getMessage());
            }
        }

        logResult();
    }

    private void inspectClass(Class<?> testClass) throws NoSuchMethodException {
        Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            if (Modifier.isStatic(method.getModifiers())) {
                continue;
            }

            Method currentMethod = testClass.getMethod(method.getName());
            if (currentMethod.isAnnotationPresent(Before.class)) {
                beforeMethods.add(currentMethod);
            }
            if (currentMethod.isAnnotationPresent(Test.class)) {
                testMethods.add(currentMethod);
            }
            if (currentMethod.isAnnotationPresent(After.class)) {
                afterMethods.add(currentMethod);
            }
        }
    }

    private void invokeMethods(List<Method> methods, Object instance) throws InvocationTargetException, IllegalAccessException {
        for (Method method : methods) {
            method.invoke(instance);
        }
    }

    private void logResult() {
        logger.info("total: {}", total);
        logger.info("passed: {}", passed);
        logger.info("failed: {}", failed);
    }
}
