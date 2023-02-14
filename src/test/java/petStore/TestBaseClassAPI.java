package petStore;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestBaseClassAPI {

    // Deklaruję nowy obiekt typu "RequestSpecification".
    public RequestSpecification specReqLogger;

    // Korzystam z metod do wykonania żądań HTTP z biblioteki Rest-Assured.

    public void getResponseGetPathParamsTest(AbstractTest<?, ?, ?> test, String authToken, Map<String, ?> paramsMap) {
        Object response = given()
                .spec(specReqLogger)
                .auth()
                .oauth2(authToken)
                .log().all()
                .pathParams(paramsMap)
                .when()
                .header("Connection", "keep-alive")
                .accept("application/json")
                .get(test.getUrl())
                .then()
                .log().all()
                .extract().response().as(test.getResponseClass());
        test.setResponse(response);
    }

    public void getResponsePost(AbstractTest<?, ?, ?> test) {

        Object response = given()
                .log().all()
                .when()
                .header("Connection", "keep-alive")
                .accept("application/json")
                .contentType("application/json")
                .body(test.getRequestBody())
                .post(test.getUrl())
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response().as(test.getResponseClass());
        test.setResponse(response);
    }

    public void getResponsePut(AbstractTest<?, ?, ?> test) {

        Object response = given()
                .log().all()
                .when()
                .header("Connection", "keep-alive")
                .accept("application/json")
                .contentType("application/json")
                .body(test.getRequestBody())
                .put(test.getUrl())
                .then()
                .log().all()
                .extract().response().as(test.getResponseClass());
        test.setResponse(response);
    }

    public void getResponseDelete(AbstractTest<?, ?, ?> test, Map<String, ?> paramsMap,Integer statusCode) {

        Object response = given()
                .log().all()
                .pathParams(paramsMap)
                .when()
                .header("Connection", "keep-alive")
                .accept("application/json")
                .contentType("application/json")
                .body(test.getRequestBody())
                .delete(test.getUrl())
                .then()
                .log().all()
                .assertThat().statusCode(statusCode)
                .extract().response().as(test.getResponseClass());
        test.setResponse(response);

    }


    @BeforeSuite
    public static void getSuiteName(ITestContext context) {

        // Przy pomocy obiektu ITestContext pobieram nazwę bierzącego profilu i wyświetlam ją w konsoli.

        System.out.println("Suite name: " + context.getCurrentXmlTest().getSuite().getName());
    }

    @BeforeClass
    public void beforeClass() {

        /* Inicjuję obiekt klasy "ObjectMapper" dzięki, któremu będę mógł mapować obiekty Java na format
          JSON i odwrotnie. Nastepnie rejestruję dwa nowe moduły odpowiedzialne za obsługę typów związanymi
          z datami i czasem. Ustawiam także widoczność getterów na NONE tak aby Jackson z nich nie korzystał
          podczas serializacji obiektów. Na koniec ustawiam aby Jackson nie zwracał błędów podczas serializacji
          pustych obiektów. */

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);

        /* Tworzę config do RestAssured z wykorzystaniem objectMapperConfig,jackson2ObjectMapperFactory z
          wyrażeniem lambda przekładającym cls,charset na objectMapper zadaklarowanym wcześniej. */

        RestAssuredConfig config = RestAssuredConfig.config().objectMapperConfig(ObjectMapperConfig.objectMapperConfig()
                                    .jackson2ObjectMapperFactory((cls,charset)->objectMapper));

        /* Powołuję nowy obiekt typu String i przypisuję do niego nazwę klasy dla której ten kod jest
          wykonywany. Następnie wynik tej operacji jest wypisywany w konsoli przy użyciu metody "println". */

        String className = this.getClass().getSimpleName();
        System.out.println("Class name: " + className);

        /* Wcześniej utworzony config przekazuję za pomocą metody "setConfig", a następnie wywołuję metodę "build",
          która zwraca gotowy obiekt specReqLogger. Obiekt ten zawiera informacje, które można wykorzystać do
          wysyłania zapytań REST.   */

        specReqLogger = new RequestSpecBuilder()
                .setConfig(config)
                .build();

    }


    @BeforeMethod
    public void beforeMethod(ITestResult result) {

        // Wyświetlam nazwę metody na konsoli

        System.out.println("Method name: " + result.getMethod().getMethodName());

    }


    @AfterMethod
    public void afterMethod(ITestResult result) {

        // Sprawdzam status testu i wyświetlam jego wynik na konsoli

        try {
            if (result.getStatus() == ITestResult.SUCCESS) {
                System.out.println("Test Succeed");
            } else if (result.getStatus() == ITestResult.FAILURE) {
                System.out.println("Test Failed");
            } else if (result.getStatus() == ITestResult.SKIP) {
                System.out.println("Test Skipped");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AfterClass
    public void afterClass() {

        // Wyświetlam informację o końcu danej klasy.

        System.out.println("End of class");
    }

    @AfterSuite
    public void afterSuite() {

        // Wyświetlam informację o końcu danego profilu testowego.

        System.out.println("End of suite");
    }

}
