package cosmic.comix.dao.mapper;

import cosmic.comix.domain.Favorites;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by NSchneier on 6/17/2015.
 */
public class FavoritesMapper implements RowMapper<Favorites> {

    @Override
    public Favorites mapRow(ResultSet resultSet, int i) throws SQLException {
        Favorites favorites = new Favorites();
        favorites.setSeries(resultSet.getString("series"));
        favorites.setTitle(resultSet.getString("title"));
        favorites.setUsername(resultSet.getString("username"));
        return favorites;
    }
}
