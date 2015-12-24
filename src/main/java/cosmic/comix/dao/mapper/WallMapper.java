package cosmic.comix.dao.mapper;

import cosmic.comix.domain.Wall;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by NSchneier on 6/17/2015.
 */
public class WallMapper implements RowMapper<Wall> {

    @Override
    public Wall mapRow(ResultSet resultSet, int i) throws SQLException {
        Wall wall = new Wall();
        wall.setMessage(resultSet.getString("message"));
        wall.setSender(resultSet.getString("sender"));
        wall.setReceiver(resultSet.getString("receiver"));
        return wall;
    }
}
