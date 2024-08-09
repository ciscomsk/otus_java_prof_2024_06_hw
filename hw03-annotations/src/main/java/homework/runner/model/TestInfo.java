package homework.runner.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestInfo {
    private int passed;
    private int failed;
    private final List<Method> beforeMethods;
    private final List<Method> testMethods;
    private final List<Method> afterMethods;

    public TestInfo() {
        this.passed = 0;
        this.failed = 0;
        this.beforeMethods = new ArrayList<>();
        this.testMethods = new ArrayList<>();
        this.afterMethods = new ArrayList<>();
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public List<Method> getBeforeMethods() {
        return beforeMethods;
    }

    public List<Method> getTestMethods() {
        return testMethods;
    }

    public List<Method> getAfterMethods() {
        return afterMethods;
    }
}
