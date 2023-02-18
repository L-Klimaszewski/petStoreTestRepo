package petStore.apis;

import petStore.AbstractTest;
import petStore.models.ModelApiResponse;
import petStore.models.Pet;

public class DeletePet implements AbstractTest<Pet, ModelApiResponse,Pet> {


    // Tworzę nową zmienną, która przechowuje adres URL.
    private String url;

   // Inicjuję zmienną typu "Pet" do przechowywania informacji o zwierzęciu.
    private Pet request;

    // Deklaruję zmienną typu "ModelApiResponse", która będzie przechowywała odpowiedź zwrotną po wysłaniu żądania.
    private ModelApiResponse response;

    /* Konstruktor klasy "DeletePet" inicializuje dwie zmienne. Zmienna 'request' jako obiekt klasy "Pet"
     i będzie używana do wysyłania żądania do API o usunięcie rekordu. Zmienna 'url' reprezentuje adres
     punktu końcowego do usunięcia rekordu. Miejsce "{id}" zostanie zastępione przez id rekordu do usunięcia. */

    public DeletePet() {
        this.request = new Pet();
        this.url = "https://petstore.swagger.io/v2/pet/{id}";
    }

    /* Metoda ta zwraca obiekt typu "Pet", przechowywanego w zmiennej 'request'. Jest używana do pobrania informacji
     o zwierzęciu przed wysłaniem żądania do API. Daje ona dodatkowo dostęp do ciała żądania. */
    public Pet getRequestBody() {
        return request;
    }

    // Metoda ta działa tak samo jak "getRequestBody" z wyłączeniem dostępu do ciała żądania.
    public Pet getRequest() {
        return request;
    }

    public void setRequest(Pet request) {
        this.request = request;
    }

    // Metoda zwraca adres URL przechowywany w zmiennej 'url'.
    public String getUrl() {
        return url;
    }

    /* Metoda przyjmuje argument typu "ModelApiResponse" i ustawia wartość zmiennej 'response' na wartość przekazaną
     jako argument. Daje to dostęp do informacji zwrotnych z serwera API takich jak informacje o błędach lub kod
     odpowiedzi.  */
    private void setResponse(ModelApiResponse response) {
        this.response = response;
    }

    /* Metoda najpierw wykonuje rzutowanie obiektu 'response' na obiekt typu "ModelApiResponse". Następnie wykonuję
     wyżej wymienioną metodę. */
    public void setResponse(Object response) {
        setResponse((ModelApiResponse) response);
    }

    // Dzięki tej metodzie uzyskuję dostęp do "ciała" odpowiedzi z serwera API i zawartych w nich informacji.
    public ModelApiResponse getResponseBody() {
        return response;
    }

    /* Ta metoda pozwala na sprawdzenie typu oczekiwanej odpowiedzi z serwera. Ułatwia to jej parsowanie lub
      deserializację. */
    public Class<ModelApiResponse> getResponseClass() {
        return ModelApiResponse.class;
    }

}
