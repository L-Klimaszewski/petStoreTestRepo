package petStore.apis;

import org.testng.annotations.Test;
import petStore.TestBaseClassBack;

import java.util.HashMap;
import java.util.Map;

public class GetPetTests extends TestBaseClassBack {
    @Test
    public void getPetById(){
        GetPet getPet = new GetPet();
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("id","2");
        getResponseGetPathParamsTest(getPet,"",paramsMap);
    }

    @Test
    public void getPetByStatus(){
        GetPetByStatus getPetByStatus = new GetPetByStatus();
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("status","pending");
        getResponseGetPathParamsTest(getPetByStatus,"",paramsMap);

    }
}
