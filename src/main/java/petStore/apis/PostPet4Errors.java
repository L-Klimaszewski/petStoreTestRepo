package petStore.apis;

import petStore.AbstractTest;
import petStore.models.Error;
import petStore.models.Pet;


public class PostPet4Errors implements AbstractTest<Pet,Error,Pet> {

    private String url;
    private Pet request;
    private Error response;

    public PostPet4Errors() {
        this.request = new Pet();
        this.url = "https://petstore.swagger.io/v2/pet";
    }

    public Pet getRequestBody() {
        return request;
    }

    public Pet getRequest() {
        return request;
    }

    public void setRequest(Pet request) {
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
