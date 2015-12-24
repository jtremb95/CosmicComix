package cosmic.comix.dao.mapper;

import cosmic.comix.domain.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by NSchneier on 6/3/2015.
 */
public class UsersMapper implements RowMapper<Users> {
    @Override
    public Users mapRow(ResultSet resultSet, int i) throws SQLException {
        Users users = new Users();
        users.setPassword(resultSet.getString("password"));
        users.setUsername(resultSet.getString("username"));
        return users;
    }
}
