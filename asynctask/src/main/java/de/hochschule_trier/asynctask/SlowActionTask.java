package de.hochschule_trier.asynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by eschs on 23.05.2017.
 */

public class SlowActionTask extends AsyncTask<Long, String, String>
{
    private EditText input;
    private TextView output;
    private String format;

    public SlowActionTask (EditText input, TextView output, String format)
    {
        this.input = input;
        this.output = output;
        this.format = format;
    }

    @Override
    public String doInBackground(Long... total)
    {
        Log.d("doInBackground()", Thread.currentThread().getName());

        long rest = total[total.length - 1];
        while (rest > 0) {
            long thisTime = Math.min(rest, 1000L);
            try {
                Thread.sleep(thisTime);
            } catch (InterruptedException e) {
            }
            rest -= thisTime;
            publishProgress("" + rest);
        }
        String message = String.format(format, total[total.length - 1]);
        return message;
    }

    @Override
    protected void onProgressUpdate(String... message)
    {
        Log.d("onProgressUpdate()", Thread.currentThread().getName());
        input.setText(message[message.length - 1]);
    }

    @Override
    protected void onPostExecute(String result)
    {
        Log.d("onPostExecute()", Thread.currentThread().getName());
        output.setText(result);
    }
}
