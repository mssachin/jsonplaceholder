package tasks;

import io.restassured.response.Response;
import utilities.RestMethods;

public class ViewComments {
    public Response pertinentToAPost(String... args){
        return RestMethods.getResource(args);
    }
}
