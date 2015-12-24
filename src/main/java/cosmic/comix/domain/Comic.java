package cosmic.comix.domain;


/**
 * Created by NSchneier on 5/27/2015.
 */
public class Comic extends AbstractDomain{

    private String series;
    private String title;
    private String info;


    public Comic() {
        setId(Long.valueOf("123"));
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
