package responseobjects;

public class Standard {
    private String id;

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "StandardResponse{" +
                "id='" + id + '\'' +
                '}';
    }
}
