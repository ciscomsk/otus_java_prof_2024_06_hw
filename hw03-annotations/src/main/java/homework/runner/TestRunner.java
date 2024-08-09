package homework.runner;

import homework.runner.annotations.After;
import homework.runner.annotations.Before;
import homework.runner.annotations.Test;
import homework.runner.model.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public class TestRunner {
    private static final Logger logger = LoggerFactory.getLogger(TestRunner.class);

    private final String testClass;

    public TestRunner(String testClass) {
        this.testClass = testClass;
    }

    public void runTests() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> testClass = Class.forName(this.testClass);
        TestInfo testInfo = inspectClass(testClass);

        for (Method test : testInfo.getTestMethods()) {
            Object instance = testClass.getDeclaredConstructor().newInstance();
            try {
                invokeMethods(testInfo.getBeforeMethods(), instance);
                test.invoke(instance);
                testInfo.setPassed(testInfo.getPassed() + 1);
            } catch (Exception ex) {
                testInfo.setFailed(testInfo.getFailed() + 1);
                logger.warn(ex.getCause().getMessage());
            } finally {
                invokeMethods(testInfo.getAfterMethods(), instance);
            }
        }

        logResult(testInfo);
    }

    private TestInfo inspectClass(Class<?> testClass) throws NoSuchMethodException {
        Method[] methods = testClass.getDeclaredMethods();
        TestInfo testInfo = new TestInfo();

        for (Method method : methods) {
            if (Modifier.isStatic(method.getModifiers())) {
                continue;
            }

            Method currentMethod = testClass.getMethod(method.getName());
            if (currentMethod.isAnnotationPresent(Before.class)) {
                testInfo.getBeforeMethods().add(currentMethod);
            }
            if (currentMethod.isAnnotationPresent(Test.class)) {
                testInfo.getTestMethods().add(currentMethod);
            }
            if (currentMethod.isAnnotationPresent(After.class)) {
                testInfo.getAfterMethods().add(currentMethod);
            }
        }

        return testInfo;
    }

    private void invokeMethods(List<Method> methods, Object instance) throws InvocationTargetException, IllegalAccessException {
        for (Method method : methods) {
            method.invoke(instance);
        }
    }

    private void logResult(TestInfo statistics) {
        logger.info("total: {}", statistics.getFailed() + statistics.getPassed());
        logger.info("passed: {}", statistics.getPassed());
        logger.info("failed: {}", statistics.getFailed());
    }
}
