package tasks;

import io.restassured.response.Response;
import utilities.RestMethods;

public class ViewContent {
    public Response inTheNetwork(String ... args) {
        return RestMethods.getResource(args);
    }
}
