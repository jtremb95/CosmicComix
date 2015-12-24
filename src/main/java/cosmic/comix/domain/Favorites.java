package cosmic.comix.domain;

/**
 * Created by NSchneier on 6/3/2015.
 */
public class Favorites extends AbstractDomain {
    private String username;
    private String series;
    private String title;

    public Favorites() {
        setId(Long.valueOf("123"));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
