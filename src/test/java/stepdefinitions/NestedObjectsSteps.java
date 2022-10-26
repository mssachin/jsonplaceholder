package stepdefinitions;

import core.Resource;
import core.Resources;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import responseobjects.Album;
import responseobjects.Photo;
import responseobjects.Todo;
import tasks.ViewContent;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NestedObjectsSteps {
    private Response nestedObjectsResponse;

    @When("I query for all {string} in {string} {string}")
    public void i_query_for_all_in_album(String nestedResource, String resource, String objectId) {
        ViewContent viewContent = new ViewContent();
        if (resource.equalsIgnoreCase(Resource.ALBUM.getValue())) {
            nestedObjectsResponse = viewContent.inTheNetwork(Resources.ALBUMS.getValue(), objectId, nestedResource);
        } else if (resource.equalsIgnoreCase(Resource.USER.getValue())) {
            nestedObjectsResponse = viewContent.inTheNetwork(Resources.USERS.getValue(), objectId, nestedResource);
        }
    }

    @Then("I validate there are a total of {string} {string} inside")
    public void i_validate_there_are_a_total_of_nested_inside(String numberOfObjects, String objects) {
        if (objects.equalsIgnoreCase(Resources.PHOTOS.getValue())) {
            Photo[] nestedPhotos = nestedObjectsResponse.then().statusCode(200).extract().as(Photo[].class);
            assertEquals(Integer.parseInt(numberOfObjects), nestedPhotos.length);
        } else if (objects.equalsIgnoreCase(Resources.ALBUMS.getValue())) {
            Album[] nestedAlbums = nestedObjectsResponse.then().statusCode(200).extract().as(Album[].class);
            assertEquals(Integer.parseInt(numberOfObjects), nestedAlbums.length);
        } else if (objects.equalsIgnoreCase(Resources.TODOS.getValue())) {
            Todo[] nestedTodos = nestedObjectsResponse.then().statusCode(200).extract().as(Todo[].class);
            assertEquals(Integer.parseInt(numberOfObjects), nestedTodos.length);
        } else {
            throw new IllegalArgumentException("Unknown Object");
        }
    }

}
