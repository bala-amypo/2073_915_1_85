package com.example.demo;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestResultListener implements ITestListener { //

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("PASS: " + result.getName()); //
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("FAIL: " + result.getName()); //
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("SKIP: " + result.getName()); //
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Starting Test Suite: " + context.getName());
    }
}