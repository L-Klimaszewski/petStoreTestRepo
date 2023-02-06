package petStore.apis;

import petStore.AbstractTest;
import petStore.models.Order;
import petStore.models.Pet;

public class PostOrder implements AbstractTest<Order, Pet,Order> {

    private String url;
    private Order request;
    private Pet response;

    public PostOrder() {
        this.request = new Order();
        this.url = "https://petstore.swagger.io/v2/pet";
    }

    public Order getRequestBody() {
        return request;
    }

    public Order getRequest() {
        return request;
    }

    public void setRequest(Order request) {
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
