package tasks;

import io.restassured.response.Response;
import utilities.RestMethods;

public class PatchResource {
    public Response inTheNetwork(String path, Object body){
        return RestMethods.patchResource(path, body);
    }
}
