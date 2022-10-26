package tasks;

import io.restassured.response.Response;
import utilities.RestMethods;

public class PostAResource {
    public Response inTheNetwork(String path, Object body){
        return RestMethods.postResource(path, body);
    }
}
