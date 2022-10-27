package tasks;

import io.restassured.response.Response;
import utilities.RestMethods;

public class PutResource {
    public Response inTheNetwork(String path, Object body){
        return RestMethods.putResource(path, body);
    }
}
