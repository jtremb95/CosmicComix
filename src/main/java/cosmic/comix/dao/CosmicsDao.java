package cosmic.comix.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSchneier on 6/3/2015.
 */
public interface CosmicsDao<T, PK extends Serializable>{

    public void insert(T object) throws SQLException;

    public T findById(String id) throws SQLException;

    public void delete(String id) throws SQLException;

    public void update(T object) throws SQLException;

    public List<T> findAll() throws SQLException;

}
