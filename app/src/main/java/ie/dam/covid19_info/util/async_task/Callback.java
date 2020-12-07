package ie.dam.covid19_info.util.async_task;

public interface Callback<R> {
    void runResultOnUiThread(R result);
}
