package petStore.apis;

import org.testng.annotations.Test;
import petStore.TestBaseClassBack;

import java.util.HashMap;
import java.util.Map;

public class GetPetTests extends TestBaseClassBack {
    @Test
    public void getPetById(){
        // Tworzę nowe obiekty klas GetPet oraz HashMap i przypisuję je do odpowienich zmiennych
        GetPet getPet = new GetPet();
        Map<String, String> paramsMap = new HashMap<>();

        // Do zmiennej paramsMap dodaję parametry w postaci String, które następnie przekazuję jako argumenty do podanej poniżej metody
        paramsMap.put("id","2");
        getResponseGetPathParamsTest(getPet,"",paramsMap);
    }

    @Test
    public void getPetByStatus(){
        // Tworzę nowe obiekty klas GetPetByStatus oraz HashMap i przypisuję je do odpowienich zmiennych
        GetPetByStatus getPetByStatus = new GetPetByStatus();
        Map<String, String> paramsMap = new HashMap<>();

        paramsMap.put("status","pending");
        getResponseGetPathParamsTest(getPetByStatus,"",paramsMap);


//         <dependency>
//            <groupId>com.fasterxml.jackson.datatype</groupId>
//            <artifactId>jackson-datatype-jdk8</artifactId>
//            <version>${jackson-version}</version>2.14.2
//                </dependency>
//        <dependency>
//            <groupId>com.fasterxml.jackson.datatype</groupId>
//            <artifactId>jackson-datatype-jsr310</artifactId>
//            <version>${jackson-version}</version>
//        </dependency>
//        <dependency>
//            <groupId>com.fasterxml.jackson.dataformat</groupId>
//            <artifactId>jackson-dataformat-xml</artifactId>
//            <version>2.9.4</version>
//        </dependency>
//        <dependency>
//            <groupId>com.fasterxml.jackson.core</groupId>
//            <artifactId>jackson-databind</artifactId>
//            <version>2.14.2</version>
//        </dependency>

    }
}
