package ie.dam.covid19_info.util.async_task;

import android.os.Handler;
import android.util.Log;

import java.util.concurrent.Callable;

public class RunnableTask<R> implements Runnable {
    private final Handler handler;
    private final Callable<R> asyncOperation;
    private final Callback<R> mainThreadOperation;

    public RunnableTask(Handler handler, Callable<R> asyncOperation, Callback<R> mainThreadOperation) {
        this.handler = handler;
        this.asyncOperation = asyncOperation;
        this.mainThreadOperation = mainThreadOperation;
    }

    @Override
    public void run() {
        try {
            final R result = asyncOperation.call();
            handler.post(new HandlerMessage<>(mainThreadOperation, result));
        } catch (Exception e) {
            Log.i("RunnableTask", "Failed call runnable " + e.getMessage());
        }
    }
}
