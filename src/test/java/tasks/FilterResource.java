package tasks;

import io.restassured.response.Response;
import utilities.RestMethods;

import java.util.Map;

public class FilterResource {
    public Response pertinentToAPost(Map<String, String> filters, String... args){
        return RestMethods.getResource(filters, args);
    }
}
