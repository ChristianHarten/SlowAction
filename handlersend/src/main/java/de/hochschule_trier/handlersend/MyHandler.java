package de.hochschule_trier.handlersend;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by eschs on 23.05.2017.
 */

public class MyHandler extends Handler
{
    private EditText input;
    private TextView output;
    private String format;

    public MyHandler (EditText input, TextView output, String format)
    {
        this.input = input;
        this.output = output;
        this.format = format;
    }

    @Override
    public void handleMessage(Message message)
    {
        Log.d("handleMessage()", Thread.currentThread().getName());
        
        super.handleMessage(message);
        Bundle bundle = message.getData();
        long left = bundle.getLong("left", -1);
        long total = bundle.getLong("total", -1);
        if (left >= 0)
        {
            input.setText("" + left);
        }
        if (total >= 0)
        {
            String s = String.format(format, total);
            output.setText(s);
        }
    }
}
