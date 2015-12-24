package cosmic.comix.service.impl;

import cosmic.comix.dao.CosmicsDao;
import cosmic.comix.dao.impl.FavoritesDaoImpl;
import cosmic.comix.domain.Favorites;
import cosmic.comix.service.ServiceInterface;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSchneier on 6/17/2015.
 */
public class FavoritesServiceImpl implements ServiceInterface<Favorites, String> {

    private CosmicsDao<Favorites, String> dao;

    public FavoritesServiceImpl() throws SQLException {
        dao = new FavoritesDaoImpl();
    }

    public FavoritesServiceImpl(FavoritesDaoImpl favoritesDao) throws SQLException {
        this.dao = favoritesDao;
    }

    @Override
    public void create(Favorites object) throws SQLException {
        dao.insert(object);
    }

    @Override
    public void delete(String id) throws SQLException {
        dao.delete(id);
    }

    @Override
    public List<Favorites> findAll() throws SQLException {
        return dao.findAll();
    }

    @Override
    public Favorites findById(String id) throws SQLException {
        return dao.findById(id);
    }

    @Override
    public void update(Favorites object) throws SQLException {
        dao.update(object);
    }

    @Override
    public DataSource getDataSource() {
        return ((FavoritesDaoImpl) dao).getDataSource();
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        ((FavoritesDaoImpl)dao).setDataSource(dataSource);
    }
}
