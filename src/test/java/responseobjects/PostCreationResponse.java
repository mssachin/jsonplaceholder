package responseobjects;

public class PostCreationResponse {
    private String id;

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PostCreationResponse{" +
                "id='" + id + '\'' +
                '}';
    }
}
