package xyz.egoistk21.iFantasy.bean;

public class HttpResult<T> {

    private T result;
    private String error;
    private int state;

    public T getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public int getState() {
        return state;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "result=" + result +
                ", error='" + error + '\'' +
                ", state=" + state +
                '}';
    }
}
