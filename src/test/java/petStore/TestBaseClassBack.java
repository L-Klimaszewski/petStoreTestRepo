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

public class TestBaseClassBack {

    public RequestSpecification specReqLogger;

    public void getResponseGetParamsTest(AbstractTest<?, ?, ?> test, String authToken, Map<String, ?> paramsMap, Integer statusCode) {

        Object response = given()
                .auth()
                .oauth2(authToken)
                .log().all()
                .params(paramsMap)
                .when()
                .header("Connection", "keep-alive")
                .accept("application/json")
                .get(test.getUrl())
                .then()
                .log().all()
                .assertThat().statusCode(statusCode)
                .extract().response().as(test.getResponseClass());
        test.setResponse(response);
    }

    public void getResponseGetPathParamsTest(AbstractTest<?, ?, ?> test, String authToken, Map<String, ?> paramsMap, Integer statusCode) {
        Object response = given()
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
                .assertThat().statusCode(statusCode)
                .extract().response().as(test.getResponseClass());
        test.setResponse(response);

    }

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

    public void getResponsePostSuccessTest(AbstractTest<?, ?, ?> test) {

        Object response = given()
                .log().all()
                .when()
                .header("Connection", "keep-alive")
                .accept("application/json")
                .body(test.getRequestBody())
                .post(test.getUrl())
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response().as(test.getResponseClass());
        test.setResponse(response);

    }

    public void getResponsePutSuccessTest(AbstractTest<?, ?, ?> test, Map<String, ?> paramsMap) {

        Object response = given()
                .log().all()
                .pathParams(paramsMap)
                .when()
                .header("Connection", "keep-alive")
                .accept("application/json")
                .body(test.getRequestBody())
                .put(test.getUrl())
                .then()
                .log().all()
                .extract().response().as(test.getResponseClass());
        test.setResponse(response);
    }

    public void getResponseDeleteSuccessTest(AbstractTest<?, ?, ?> test, Map<String, ?> paramsMap) {

        Object response = given()
                .log().all()
                .pathParams(paramsMap)
                .when()
                .header("Connection", "keep-alive")
                .accept("application/json")
                .body(test.getRequestBody())
                .delete(test.getUrl())
                .then()
                .log().all()
                .extract().response().as(test.getResponseClass());
        test.setResponse(response);


    }




    @BeforeSuite
    public static void getSuiteName(ITestContext context) {
        System.out.println("Suite name: " + context.getCurrentXmlTest().getSuite().getName());
    }

    @BeforeClass
    public void beforeClass() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);




        //  Tworzę config do RestAssured z wykorzystaniem objectMapperConfig,jackson2ObjectMapperFactory z
        //      wyrażeniem lambda przekładającym cls,charset na objectMapper zadaklarowanym wcześniej

        RestAssuredConfig config = RestAssuredConfig.config().objectMapperConfig(ObjectMapperConfig.objectMapperConfig()
                                    .jackson2ObjectMapperFactory((cls,charset)->objectMapper));

        String className = this.getClass().getSimpleName();

        System.out.println("Class name: " + className);

        specReqLogger = new RequestSpecBuilder()
                .setConfig(config)
                .build();

    }


    @BeforeMethod
    public void beforeMethod(ITestResult result) {
        System.out.println("Method name: " + result.getMethod().getMethodName());

    }


    @AfterMethod
    public void afterMethod(ITestResult result) {

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
        //driver.quit();
    }

    @AfterClass
    public void afterClass() {
        System.out.println("End of class");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("End of the suite");
    }

}
