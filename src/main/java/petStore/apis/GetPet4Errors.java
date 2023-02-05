package petStore.apis;

import petStore.AbstractTest;
import petStore.models.Empty;
import petStore.models.Error;

public class GetPet4Errors implements AbstractTest<Empty,Error,Empty> {

    private String url;
    private Empty request;
    private Error response;

    public GetPet4Errors() {
        this.request = new Empty();
        this.url = "https://petstore.swagger.io/v2/pet/{id}";
    }

    public Empty getRequestBody() {
        return request;
    }

    public Empty getRequest() {
        return request;
    }

    public void setRequest(Empty request) {
        this.request = request;
    }


    public String getUrl() {
        return url;
    }


    private void setResponse(Error response) {
        this.response = response;
    }

    public void setResponse(Object response) {
        setResponse((Error) response);
    }

    public Error getResponseBody() {
        return response;
    }

    public Class<Error> getResponseClass() {
        return Error.class;
    }
    

}
