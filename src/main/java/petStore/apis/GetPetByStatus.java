package petStore.apis;

import petStore.AbstractTest;
import petStore.models.Empty;
import petStore.models.Pet;

public class GetPetByStatus implements AbstractTest<Empty, Pet,Empty> {

    private String url;
    private Empty request;
    private Pet response;

    public GetPetByStatus() {
        this.request = new Empty();
        this.url = "https://petstore.swagger.io/v2/pet/findByStatus?status={status}";
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


    private void setResponse(Pet response) {
        this.response = response;
    }

    public void setResponse(Object response) {
        setResponse((Pet) response);
    }

    public Pet getResponseBody() {
        return response;
    }

    public Class<Pet> getResponseClass() {
        return Pet.class;
    }


}
