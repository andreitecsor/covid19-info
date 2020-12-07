package ie.dam.covid19_info.async_task;

public interface Callback<R> {
    void runResultOnUiThread(R result);
}
