package responseobjects;

public class Photo {
    private String albumId;
    private String id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public String getAlbumId() {
        return albumId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "albumId='" + albumId + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
