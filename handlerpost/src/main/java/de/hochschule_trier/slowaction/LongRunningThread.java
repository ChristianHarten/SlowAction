package de.hochschule_trier.slowaction;

import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by eschs on 23.05.2017.
 */

public class LongRunningThread extends Thread
{
    private long total;
    private EditText input;
    private TextView output;
    private Handler handler;
    private String format;

    public LongRunningThread(long total, EditText input, TextView output, Handler handler, String format)
    {
        this.total = total;
        this.input = input;
        this.output = output;
        this.handler = handler;
        this.format = format;
    }

    @Override
    public void run()
    {
        Log.d("thread run()", Thread.currentThread().getName());

        long rest = total;
        while (rest > 0)
        {
            long thisTime = Math.min(rest, 1000L);
            try
            {
                Thread.sleep(thisTime);
            }
            catch (InterruptedException e)
            {
            }
            rest -= thisTime;
            Request request = new Request(input, "" + rest);
            handler.post(request);
        }
        String message = String.format(format, total);
        Request request = new Request(output, message);
        handler.post(request);
    }
}