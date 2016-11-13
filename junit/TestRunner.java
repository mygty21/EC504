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

public class TestRunner {
    


    public static void runTests(Class<?>... classes) {
        TestRunnerPrintAll.runTests(classes);
    }

}


