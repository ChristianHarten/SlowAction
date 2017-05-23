package de.hochschule_trier.handlersend;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by eschs on 23.05.2017.
 */

public class LongRunningThread extends Thread
{
    private long total;
    private Handler handler;

    public LongRunningThread (long total, Handler handler)
    {
        this.total = total;
        this.handler = handler;
    }

    @Override
    public void run()
    {
        Log.d("thread run()", Thread.currentThread().getName());

        long rest = total;
        while (rest > 0) {
            long thisTime = Math.min(rest, 1000L);
            try {
                Thread.sleep(thisTime);
            } catch (InterruptedException e) {
            }
            rest -= thisTime;

            Message message = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putLong("left", rest);
            message.setData(bundle);
            handler.sendMessage(message);
        }
        Message message = handler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putLong("left", rest);
        bundle.putLong("total", total);
        message.setData(bundle);
        handler.sendMessage(message);
    }
}
