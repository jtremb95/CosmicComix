package cosmic.comix.service.impl;

import cosmic.comix.dao.CosmicsDao;
import cosmic.comix.dao.impl.WallDaoImpl;
import cosmic.comix.domain.Wall;
import cosmic.comix.service.ServiceInterface;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSchneier on 6/17/2015.
 */
public class WallServiceImpl implements ServiceInterface<Wall, String> {
    private CosmicsDao<Wall, String> dao;

    public WallServiceImpl() throws SQLException {
        dao = new WallDaoImpl();
    }

    public WallServiceImpl(WallDaoImpl wallDao) throws SQLException {
        this.dao = wallDao;
    }

    @Override
    public void create(Wall object) throws SQLException {
        dao.insert(object);
    }

    @Override
    public void delete(String id) throws SQLException {
        dao.delete(id);
    }

    @Override
    public List<Wall> findAll() throws SQLException {
        return dao.findAll();
    }

    @Override
    public Wall findById(String id) throws SQLException {
        return dao.findById(id);
    }

    @Override
    public void update(Wall object) throws SQLException {
        dao.update(object);
    }

    @Override
    public DataSource getDataSource() {
        return ((WallDaoImpl) dao).getDataSource();
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        ((WallDaoImpl)dao).setDataSource(dataSource);
    }

}
