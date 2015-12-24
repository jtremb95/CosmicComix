package cosmic.comix.domain;

/**
 * Created by NSchneier on 6/3/2015.
 */
public abstract class AbstractDomain {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;


}
