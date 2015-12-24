package cosmic.comix.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by NSchneier on 6/3/2015.
 */
public abstract class AbstractCosmicsDao<T, PK extends Serializable>{

    protected static final String URL = "jdbc:mysql://192.168.1.17:3306/cosmic";
    protected static final String USERNAME = "jeff";
    protected static final String PASSWORD = "jeff";
    protected RowMapper mapper;
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    protected static DataSource dataSource;

    public AbstractCosmicsDao () throws SQLException {
        setDataSource(new DriverManagerDataSource(URL, USERNAME, PASSWORD));
    }
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public DataSource getDataSource(){
        return dataSource;
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(){
        return namedParameterJdbcTemplate;
    }
}
