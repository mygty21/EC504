// adapted from http://memorynotfound.com/add-junit-listener-example/
// Highly redundant with GradedTestListenerJSON. Maybe refactor later.
// Also, should output go to StdErr? That's what Paul did.
package jh61b.junit;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

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

//import jh61b.junit.JUnitUtilities;
import java.util.HashSet;

import java.util.Arrays;

public class TestRunnerGenerateResultsHash extends RunListenerWithCapture {
    private String emailString;
	private byte[] email;
	private byte[] sourceCode;
	private String editorSource;
	private byte[] aggregate;
	String simpleName;

	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	private static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}

    /* .getBytes seems to do weird stuff... */
    private static byte[] stringToBytes(String x) {
        byte[] output = new byte[x.length()];
        for (int i = 0; i < x.length(); i += 1) {
            output[i] = (byte) x.charAt(i);
        }
        return output;
    }

	public TestRunnerGenerateResultsHash(String emailString, String className) {
        this.emailString = emailString;
		email = emailString.getBytes();
    	System.out.println(className);

		String[] tokens = className.split("\\.");
		String packageName = tokens[0];
		simpleName = tokens[1];
		BinaryIn binaryIn = new BinaryIn(packageName + "/" + simpleName + ".class");
		String code = binaryIn.readString();
		sourceCode = stringToBytes(code);//.getBytes();
        
        //System.out.println("h1: " + Arrays.hashCode(email));

		binaryIn = new BinaryIn("editor" + "/" + "Editor.class");
        byte[] rawSource = stringToBytes(binaryIn.readString());
		editorSource = bytesToHex(rawSource);

        //System.out.println("code hex: " + bytesToHex(sourceCode));

        //System.out.println("h2: " + Arrays.hashCode(sourceCode));

		aggregate = new byte[email.length + sourceCode.length];
		System.arraycopy(email, 0, aggregate, 0, email.length);
		System.arraycopy(sourceCode, 0, aggregate, email.length, sourceCode.length);
	}

    private boolean mostRecentTestPassed;

    /* Code to run at the beginning of a test run. */
    public void testRunStarted(Description description) throws Exception {
        System.out.println("Running JUnit tests using jh61b.junit.TestRunner in \"all\" mode.\n");
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

    private int generateAchievementToken() {
    	int x = 5 & 0xff;
    	if (email.length > 0) {
    		x = x & email[0];
    	}
    	return x;
    }

    /** When a test completes, add the test output at the bottom. Then stop capturing
      * StdOut. Open question: Is putting the captured output at the end clear? Or is that
      * possibly confusing? We'll see... */
    public void testFinished(Description description) throws Exception {
        String printedOutput = this.endCapture();
        String printedOutputNoTrailingWS = printedOutput.replaceFirst("\\s+$", "");
        if (printedOutputNoTrailingWS.length() > 0) {
            System.out.println(printedOutputNoTrailingWS);    
        }
        
        if (mostRecentTestPassed) {
            System.out.println("=====> Passed, generating achievement token.\n");
            generateAchievementToken();
            String tokenName = "Token" + simpleName;
            Out out = new Out(tokenName + ".java");
            int hashy = Arrays.hashCode(aggregate);
            out.println("public class " + tokenName + " { public String email = \"" + emailString + "\"; public String testName = \"" + simpleName + "\"; public int token = " + hashy + "; public String token2 = \"" + editorSource + "\"; }");
            out.close();
            System.out.println("Token generated: " + tokenName + ".java");
        } else {
            System.out.println("=====> FAILED!\n");            
        }
        //System.out.println(String.format("==> Score: %.2f / %.2f", currentTestResult.score, currentTestResult.maxScore));
    }

    /** Sets score to 0 and appends reason for failure and dumps a stack trace.
      * TODO: Clean up this stack trace so it is not hideous. 
      * Other possible things we might want to consider including: http://junit.sourceforge.net/javadoc/org/junit/runner/notification/Failure.html.
      */
    public void testFailure(Failure failure) throws Exception {
        System.out.println(JUnitUtilities.failureToString(failure));
        mostRecentTestPassed = false;
    }

    public static void runTests(Class<?>... classes) {
    	Class<?> c = classes[0];
    	String className = c.getName();

        String email = null;
        try {
            In in = new In("email.txt");
            email = in.readString().trim();
            System.out.println("Your email address was read as: " + email);
        } catch (Exception e) {
            System.out.println("To avoid having to type in your email address, create a file called email.txt that contains your gradescope email address.");
            System.out.println("Could not read email adddress, defaulting to asking user for input.");
        }

        if (email == null) {
            System.out.print("Enter your gradescope email address: ");
            Scanner reader = new Scanner(System.in);
            email = reader.next();
            try {
                Thread.sleep(1000);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }                    
        }

        JUnitCore runner = new JUnitCore();
        runner.addListener(new TestRunnerGenerateResultsHash(email, className));
        runner.run(classes);
    }
}