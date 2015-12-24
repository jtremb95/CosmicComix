package cosmic.comix.dao.mapper;

import cosmic.comix.domain.Comic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by NSchneier on 6/17/2015.
 */
public class ComicMapper implements RowMapper<Comic> {

    @Override
    public Comic mapRow(ResultSet resultSet, int i) throws SQLException {
        Comic comic = new Comic();
        comic.setInfo(resultSet.getString("info"));
        comic.setTitle(resultSet.getString("title"));
        comic.setSeries(resultSet.getString("series"));
        return comic;
    }
}
