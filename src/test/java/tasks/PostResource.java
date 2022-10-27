package tasks;

import io.restassured.response.Response;
import utilities.RestMethods;

public class PostResource {
    public Response inTheNetwork(String path, Object body){
        return RestMethods.postResource(path, body);
    }
}
