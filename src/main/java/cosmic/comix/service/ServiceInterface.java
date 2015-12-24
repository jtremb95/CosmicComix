package cosmic.comix.service;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSchneier on 6/3/2015.
 */
public interface ServiceInterface<T, PK extends Serializable> {

    public void create(T object) throws SQLException;

    public void delete(PK id) throws SQLException;

    public List<T> findAll() throws SQLException;

    public T findById(String id) throws SQLException;

    public void update(T object) throws SQLException;

    public DataSource getDataSource();

    public void setDataSource(DataSource dataSource);

}
