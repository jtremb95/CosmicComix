package cosmic.comix.dao.impl;

import cosmic.comix.dao.AbstractCosmicsDao;
import cosmic.comix.dao.CosmicsDao;
import cosmic.comix.dao.mapper.UsersMapper;
import cosmic.comix.domain.Users;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSchneier on 6/3/2015.
 */
public class UsersDaoImpl extends AbstractCosmicsDao<Users, String>
        implements CosmicsDao<Users, String> {


    public UsersDaoImpl() throws SQLException {
        super();
        mapper = new UsersMapper();
    }

    @Override
    public void insert(Users object) throws SQLException{
        namedParameterJdbcTemplate.getJdbcOperations().execute("insert into users (username, password) values ('"
                + object.getUsername() + "', '" + object.getPassword() + "')");
    }

    @Override
    public Users findById(String id) throws SQLException {

        List<Users> list = namedParameterJdbcTemplate.query("select * from users where username='"
                + id + "'", mapper);
        Users user = new Users();
        if (list.size() > 0) {
            user = list.get(0);
        }
        return user;
    }

    @Override
    public void delete(String id) throws SQLException{
        namedParameterJdbcTemplate.getJdbcOperations().execute("delete from users where username ='" + id + "'");
    }

    @Override
    public void update(Users object) throws SQLException{
        namedParameterJdbcTemplate.getJdbcOperations().execute("update users set password='" + object.getPassword()
                + "' where username='" + object.getUsername() + "'");
    }

    @Override
    public List<Users> findAll() throws SQLException{
        return namedParameterJdbcTemplate.query("select * from users", mapper);
    }

}
