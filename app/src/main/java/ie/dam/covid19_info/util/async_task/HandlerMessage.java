package ie.dam.covid19_info.util.async_task;

public class HandlerMessage<R> implements Runnable {
    private final Callback<R> mainThreadOperation;
    private final R result;

    public HandlerMessage(Callback<R> mainThreadOperation, R result) {
        this.mainThreadOperation = mainThreadOperation;
        this.result = result;
    }

    @Override
    public void run() {
        mainThreadOperation.runResultOnUiThread(result);
    }
}
