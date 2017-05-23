package de.hochschule_trier.slowaction;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clicked(View v)
    {
        Log.d("clicked()", Thread.currentThread().getName());

        EditText input = (EditText) findViewById(R.id.input);
        String s = input.getText().toString();
        TextView output = (TextView) findViewById(R.id.output);

        try
        {
            long total = Long.parseLong(s);
            if (total < 0)
            {
                throw new NumberFormatException();
            }
            String format = getString(R.string.successMessage);
            Handler handler = new Handler();
            Thread t = new LongRunningThread(total, input, output, handler, format);
            t.start();
        }
        catch (NumberFormatException e)
        {
            String message = getString(R.string.failureMessage) + " " + e.getStackTrace();
            output.setText(message);
        }
    }
}
