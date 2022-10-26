package tasks;

import io.restassured.response.Response;
import utilities.RestMethods;

public class UpdatePosts {
    public Response inTheNetwork(String path, Object body){
        return RestMethods.patchResource(path, body);
    }
}
