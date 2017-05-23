package de.hochschule_trier.slowaction;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by eschs on 23.05.2017.
 */

public class Request implements Runnable
{
    private EditText input;
    private TextView output;
    private String message;

    public Request (EditText input, String message)
    {
        this.input = input;
        this.message = message;
    }

    public Request (TextView output, String message)
    {
        this.output = output;
        this.message = message;
    }

    @Override
    public void run()
    {
        Log.d("request run()", Thread.currentThread().getName());

        if (input != null)
        {
            input.setText(message);
        }
        else if (output != null)
        {
            output.setText(message);
        }
    }
}
