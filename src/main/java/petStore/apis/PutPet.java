package petStore.apis;

import petStore.AbstractTest;
import petStore.models.Pet;


public class PutPet implements AbstractTest<Pet,Pet,Pet> {

    private String url;
    private Pet request;
    private Pet response;

    public PutPet() {
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
