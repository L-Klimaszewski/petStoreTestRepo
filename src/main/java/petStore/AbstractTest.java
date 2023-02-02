package petStore;

public interface AbstractTest<T,U,W>{
    T getRequestBody();
    W getRequest();
    U getResponseBody();

    String getUrl();

    Class<U> getResponseClass();

    void setResponse(Object response);
}
