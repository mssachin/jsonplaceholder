package core;

public enum Resource {
    POST("post"),
    ALBUM("album"),
    PHOTO("photo"),
    COMMENT("comment"),
    USER("user"),
    TODO("todo");

    private final String value;

    Resource(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
