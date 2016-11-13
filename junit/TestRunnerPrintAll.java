package junit;
import java.util.List;
import java.util.ArrayList;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.JUnitCore;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.Collection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;


import java.util.HashSet;

import java.util.Arrays;

public class TestRunnerPrintAll extends RunListenerWithCapture {

    private boolean mostRecentTestPassed;

    /* Code to run at the beginning of a test run. */
    public void testRunStarted(Description description) throws Exception {
        System.out.println("Running tests");
    }

    /* Code to run at the end of test run. */
    public void testRunFinished(Result result) throws Exception {
        int count = result.getRunCount();
        int numFailed = result.getFailureCount();
        int numPassed = count - numFailed;
        System.out.println(String.format("Passed: %d/%d tests.", numPassed, count));  
    }

    public void testStarted(Description description) throws Exception {
        String testSummary = String.format("%s", description.getMethodName());
        System.out.println("Running " + testSummary + ": ");
        System.out.println("====================================");
        mostRecentTestPassed = true;
        this.startCapture();
    }


    public void testFinished(Description description) throws Exception {
        String printedOutput = this.endCapture();
        String printedOutputNoTrailingWS = printedOutput.replaceFirst("\\s+$", "");
        if (printedOutputNoTrailingWS.length() > 0) {
            System.out.println(printedOutputNoTrailingWS);    
        }
        
        if (mostRecentTestPassed) {
            System.out.println("=====> Passed\n");
        } else {
            System.out.println("=====> FAILED!\n");            
        }
       
    }

    public void testFailure(Failure failure) throws Exception {
        System.out.println(JUnitUtilities.failureToString(failure));
        mostRecentTestPassed = false;
    }

    public static void runTests(Class<?>... classes) {
        JUnitCore runner = new JUnitCore();
        runner.addListener(new TestRunnerPrintAll());
        runner.run(classes);
    }
}