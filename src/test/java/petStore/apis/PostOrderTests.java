package petStore.apis;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import petStore.TestBaseClassBack;

public class PostOrderTests extends TestBaseClassBack {

    @Test
    public void postNewOrder(){

        // Tworzę nowy obiekty klas SoftAssertions, PostOrder oraz Id i przypisuję je do odpowiednich zmiennych
        SoftAssertions softly = new SoftAssertions();
        PostOrder postOrder = new PostOrder();
        long Id = 1951191000L;

        // Do zapytania dodaję parametry do przekazania
        postOrder.getRequestBody().setId(Id);
        postOrder.getRequestBody().setQuantity(1);
        postOrder.getRequestBody().setPetId(1951L);
        postOrder.getRequestBody().setComplete(true);

        // Wywołuję metodę z TestBaseClass do wysłania zapytania do API
        getResponsePostSuccessTest(postOrder);

        // Wywołuję metody SoftAssertions w celu potwierdzenia
        softly.assertThat(postOrder.getResponseBody().getId()).isEqualTo(Id);
        softly.assertAll();

    }


}
