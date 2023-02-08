package petStore.apis;

import petStore.AbstractTest;
import petStore.models.ModelApiResponse;
import petStore.models.Pet;

public class DeletePet implements AbstractTest<Pet, ModelApiResponse,Pet> {

    private String url;
    private Pet request;
    private ModelApiResponse response;

    public DeletePet() {
        this.request = new Pet();
        this.url = "https://petstore.swagger.io/v2/pet/{id}";
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


    private void setResponse(ModelApiResponse response) {
        this.response = response;
    }

    public void setResponse(Object response) {
        setResponse((ModelApiResponse) response);
    }

    public ModelApiResponse getResponseBody() {
        return response;
    }

    public Class<ModelApiResponse> getResponseClass() {
        return ModelApiResponse.class;
    }

}
