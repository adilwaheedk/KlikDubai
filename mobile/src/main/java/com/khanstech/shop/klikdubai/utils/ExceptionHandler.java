package com.khanstech.shop.klikdubai.utils;

import android.content.Context;
import android.os.Build;

import com.khanstech.shop.klikdubai.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;

/**
 * Handler class to catch Uncaught Exceptions. Log exceptions to desired file.
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final Context context;
    private final String LINE_SEPARATOR = "\n";
    private final String PATH_SEPARATOR = "/";
    Thread.UncaughtExceptionHandler defaultUEH;

    public ExceptionHandler(Context context) {
        this.context = context;
        defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }

    public void uncaughtException(Thread thread, Throwable exception) {

        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        StringBuilder errorReport = new StringBuilder();
        errorReport.append("************ CAUSE OF ERROR ************" + LINE_SEPARATOR + LINE_SEPARATOR);
        errorReport.append(stackTrace.toString());

        errorReport.append(LINE_SEPARATOR + "************ DEVICE INFORMATION ***********" + LINE_SEPARATOR);
        errorReport.append("Brand: ");
        errorReport.append(Build.BRAND);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Device: ");
        errorReport.append(Build.DEVICE);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Model: ");
        errorReport.append(Build.MODEL);
        errorReport.append(LINE_SEPARATOR);

        File root = android.os.Environment.getExternalStorageDirectory();
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        File dir = new File(root.getAbsolutePath() + PATH_SEPARATOR + context.getString(R.string.directory_name)
                + PATH_SEPARATOR + context.getString(R.string.directory_log));
        if (!dir.exists()) dir.mkdirs();
        File file = new File(dir, context.getString(R.string.log_filename));
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(file, true));
            buf.append(currentDateTimeString + ":" + errorReport.toString());
            buf.newLine();
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        defaultUEH.uncaughtException(thread, exception);
        System.exit(0);
    }

}
