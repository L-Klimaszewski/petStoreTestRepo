package petStore.apis;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import petStore.TestBaseClassAPI;
import petStore.models.Category;
import petStore.models.Pet;
import petStore.models.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeletePetTests extends TestBaseClassAPI {

    @Test
    public void deletingExistingRecord (){

        /* Tworzę nowy obiekt klasy "PostPet", dzięki temu uzyskam dostęp do metod zawartych w tej klasie.
         Obiektu użyję jako jeden z argumentów metody "getResponsePostSuccess". */

        PostPet postPet = new PostPet();

        /* Tworzę nowy obiekt klasy "DeletePet", którego użyję jako jeden z argumentów w metodzie
         "getResponseDelete" do wysłania żądania do API. */

        DeletePet deletePet = new DeletePet();

        /* Tworzę nowy obiekt klasy "SoftAssertions", którego metod użyję do potwierdzenia zgodności otrzymanych
          danych z oczekiwanymi. Dzięki tzw. miekkim asercjom kod nie zostanie zatrzymany w momencie złamania asercji.
          Daje to możliwość sprawdzenia kilku warunków jednocześnie. Dopiero po wywołaniu metody "assertAll", program
          sprawdzi wszystkie asercje i zakończy działanie z błędem jeśli jakaś asercja zostanie złamana.*/

        SoftAssertions softly = new SoftAssertions();

        /* Tworzę nowy obiekt klasy "Map", który jest interfejsem służącym do przechowywania danych w postaci
          klucz- wartość. Obiekt klasy "String" do którego mogę przypisać dane w postaci ciągu znaków przekazuję
          jako drugi argument (wartość) metody "put" wywołanej na obiekcie paramsMap. Pierwszym argumentem (klucz)
          jest "id". */

        Map<String,String> paramsMap = new HashMap<>();
        String id = "1234";
        paramsMap.put("id",id);

        /* Tworzę nowy obiekt klasy "ArrayList", który jest implementacją interfejsu "List". Klasa ta umożliwia
          przechowywanie danych w tablicy. Obiekt "tagList" przechowuje obiekty typu "Tag". Tworząc nowy obiekt klasy
          "Tag" uzyskuję dostęp do jego metod. Wykorzystuję metody "setId" oraz "setName" do zdefiniowania właściwości
          obiektu firstTag. Następnie wywołuję metodę "add" na obiekcie tagList i przekazuję do niego obiekt firstTag. */

        List<Tag> tagList = new ArrayList<>();
        Tag firstTag = new Tag();
        firstTag.setId(17128L);
        firstTag.setName("First Pet");
        tagList.add(firstTag);

        /* Tworzę nowy obiekt klasy "Category" i dzięki dostępowi do jego metod używam "setId", "setName" do ustawienia
          jego właściwości.  */

        Category category = new Category();
        category.setId(1231321L);
        category.setName("Nazwa");

        /* Ustawiam właściwości wcześniej utworzonego obiektu typu "postPet" przy pomocy metod zawartych w klasie "PostPet".
          Do tego testu wykorzystuję wybranych metod, tj. "setId","setName", "setStatus". Dodatkowo przy użyciu metod
          "setCategory" i "setTags" przekazuję wcześniej utworzone i zdefiniowane obiekty "tagList" oraz "category". Tak
          zdefiniowany obiekt "postPet" przekazuję jako argument w metodzie "getResponsePost", której zadaniem
          jest wysłanie żądania do API o utworzenie pozycji o właściowściach zdefiniowanych w obiekcie "postPet". */

        postPet.getRequestBody().setId(Long.parseLong(id));
        postPet.getRequestBody().setName("Łajka");
        postPet.getRequestBody().setTags(tagList);
        postPet.getRequestBody().setCategory(category);
        postPet.getRequestBody().setStatus(Pet.StatusEnum.AVAILABLE);
        getResponsePost(postPet);

        /* Metody "getResponseDelete" używam do wysłania żądania do API o usunięcie konkretnej pozycji. Obiekt "deletePet",
          który służy do opakowania informacji potrzebnych do wykonania żądania "DELETE" jest pierwszym argumentem tej metody.
          Obiekt "paramsMap" zawiera parametry, które zostaną dodane do ścieżki URL przekazuję jako drugi argument. Trzecim
          argumentem jest "statusCode",czyli kod oczekiwanej przeze mnie odpowiedzi HTTP, tj. 200. */

        getResponseDelete(deletePet,paramsMap,200);

        /* Używam metod zawartych w klasie "DeletePet" na obiekcie deletePet do uzyskania dostępu do danych zawartych w
          "body" odpowiedzi z API. Dzięki metodom "getCode", "getType" i "getMessage" pobieram dane z "body" odpowiedzi.
          Nastepnie przy pomocy metody "isEqual" klasy "SoftAssertions" weryfikuję czy otrzymane dane są zgodne z tymi,
          których oczekiwałem. Na koniec wywołuję metodę "assertAll" aby sprawdzić czy asercje zostały złamane. */

        softly.assertThat(deletePet.getResponseBody().getCode()).isEqualTo(200);
        softly.assertThat(deletePet.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(deletePet.getResponseBody().getMessage()).isEqualTo(id);
        softly.assertAll();

    }

    @Test
    public void deletingNonExistingRecord (){

        DeletePet deletePet = new DeletePet();
        Map<String,String> paramsMap = new HashMap<>();
        String id = "1951191000";
        SoftAssertions softly = new SoftAssertions();

        paramsMap.put("id",id);
        getResponseDelete(deletePet,paramsMap,404);

//        --- jesli podam id istniejącego rekordu to wszystko dziala a jesli nie istniejacego to wyswietla blad:
//        java.lang.IllegalStateException: Cannot parse content to class petStore.models.ModelApiResponse because no content-type was present in the response and no default parser has been set.
//        You can specify a default parser using e.g.:
//        RestAssured.defaultParser = Parser.JSON;
//
//        or you can specify an explicit ObjectMapper using as(class petStore.models.ModelApiResponse, <ObjectMapper>);

        softly.assertThat(deletePet.getResponseBody().getCode()).isEqualTo(200);
        softly.assertThat(deletePet.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(deletePet.getResponseBody().getMessage()).isEqualTo(id);
        softly.assertAll();

    }


}


