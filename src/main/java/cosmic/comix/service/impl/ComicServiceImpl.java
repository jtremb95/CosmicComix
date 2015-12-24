package cosmic.comix.service.impl;

import cosmic.comix.dao.CosmicsDao;
import cosmic.comix.dao.impl.ComicDaoImpl;
import cosmic.comix.domain.Comic;
import cosmic.comix.service.ServiceInterface;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSchneier on 6/17/2015.
 */
public class ComicServiceImpl implements ServiceInterface<Comic, String> {

    private CosmicsDao<Comic, String> dao;

    public ComicServiceImpl() throws SQLException {
        dao = new ComicDaoImpl();
    }

    public ComicServiceImpl(ComicDaoImpl dao) throws SQLException {
        this.dao = dao;
    }

    @Override
    public void create(Comic object) throws SQLException {
        dao.insert(object);
    }

    @Override
    public void delete(String id) throws SQLException {
        dao.delete(id);
    }

    @Override
    public List<Comic> findAll() throws SQLException {
        return dao.findAll();
    }

    @Override
    public Comic findById(String id) throws SQLException {
        return dao.findById(id);
    }

    @Override
    public void update(Comic object) throws SQLException {
        dao.update(object);
    }

    @Override
    public DataSource getDataSource() {
        return ((ComicDaoImpl) dao).getDataSource();
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        ((ComicDaoImpl)dao).setDataSource(dataSource);
    }

}
