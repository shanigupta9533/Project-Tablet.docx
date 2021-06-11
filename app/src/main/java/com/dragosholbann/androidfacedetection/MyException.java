package com.dragosholbann.androidfacedetection;


import java.io.PrintWriter;
import java.io.StringWriter;
import android.content.Intent;

import com.dragosholbann.androidfacedetection.SplashActivity;

public class MyException implements java.lang.Thread.UncaughtExceptionHandler {
    private final SmsActivity myContext;
    private final String LINE_SEPARATOR = "\n";

    public MyException(SmsActivity context) {
        myContext = context;
    }

    public void uncaughtException(Thread thread, Throwable exception) {
        StackTraceElement[] errorData = exception.getStackTrace();
        String set =  errorData[0].toString() + errorData[1].toString() + errorData[2].toString()+errorData[3].toString()+errorData[4].toString();
        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        StringBuffer errorReport = new StringBuffer();
        errorReport.append("************ CAUSE OF ERROR ************\n\n");
        errorReport.append(stackTrace.toString());
        Intent intent = new Intent(myContext, SmsActivity.class);
        intent.putExtra("error", errorReport.toString());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        myContext.ApiCallEmailErrorLog(set, errorReport);
        myContext.startActivity(intent);
        myContext.finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);

    }
}

