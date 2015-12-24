package cosmic.comix.dao.impl;

import cosmic.comix.dao.AbstractCosmicsDao;
import cosmic.comix.dao.CosmicsDao;
import cosmic.comix.dao.mapper.FavoritesMapper;
import cosmic.comix.domain.Comic;
import cosmic.comix.domain.Favorites;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSchneier on 6/17/2015.
 */
public class FavoritesDaoImpl extends AbstractCosmicsDao<Comic, String>
        implements CosmicsDao<Favorites, String> {
    public FavoritesDaoImpl() throws SQLException {
        super();
        mapper = new FavoritesMapper();
    }

    @Override
    public void insert(Favorites object) throws SQLException {
        namedParameterJdbcTemplate.getJdbcOperations().execute("insert into favorites (username, series, title) values ('"
                + object.getUsername() + "', '" + object.getSeries() + "', '"
                + object.getTitle() + "')");
    }

    @Override
    public Favorites findById(String id) throws SQLException {
        List<Favorites> list = namedParameterJdbcTemplate.query("select * from favorites where username='"
                + id + "'", mapper);
        Favorites favorites = new Favorites();
        if (list.size() > 0) {
            favorites = list.get(0);
        }
        return favorites;
    }

    @Override
    public void delete(String id) throws SQLException {
        namedParameterJdbcTemplate.getJdbcOperations().execute("delete from favorites where username ='" + id + "'");
    }

    @Override
    public void update(Favorites object) throws SQLException {
        namedParameterJdbcTemplate.getJdbcOperations().execute("update favorites set series='" + object.getSeries()
                + "', title='" + object.getTitle()
                + "' where username='"
                + object.getUsername() + "'");
    }

    @Override
    public List<Favorites> findAll()throws SQLException   {
        return namedParameterJdbcTemplate.query("select * from favorites", mapper);
    }

}
