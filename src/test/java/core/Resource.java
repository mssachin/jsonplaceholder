package core;

public enum Resource {
    POST("posts", "post"),
    COMMENT("comments", "comment"),
    ALBUM("albums", "album"),
    PHOTO("photos", "photo"),
    USER("users", "user"),
    TODO("todos", "todo");

    private final String multiple;
    private final String single;

    Resource(String multiple, String single) {
        this.multiple = multiple;
        this.single = single;
    }

    public String getMultiple() {
        return multiple;
    }

    public String getSingle() {
        return single;
    }
}
