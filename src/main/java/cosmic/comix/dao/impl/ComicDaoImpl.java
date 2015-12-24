package cosmic.comix.dao.impl;

import cosmic.comix.dao.AbstractCosmicsDao;
import cosmic.comix.dao.CosmicsDao;
import cosmic.comix.dao.mapper.ComicMapper;
import cosmic.comix.domain.Comic;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSchneier on 6/17/2015.
 */
public class ComicDaoImpl extends AbstractCosmicsDao<Comic, String>
        implements CosmicsDao<Comic, String> {

    public ComicDaoImpl() throws SQLException {
        super();
        mapper = new ComicMapper();
    }

    @Override
    public void insert(Comic object) throws SQLException  {
        namedParameterJdbcTemplate.getJdbcOperations().execute("insert into comics (series, title, info) values ('"
                + object.getSeries() + "', '" + object.getTitle() + "', '"
                + object.getInfo() + "')");
    }

    @Override
    public Comic findById(String id) throws SQLException {
        List<Comic> list = namedParameterJdbcTemplate.query("select * from comics where series='"
                + id + "'", mapper);
        Comic comic = new Comic();
        if (list.size() > 0) {
            comic = list.get(0);
        }
        return comic;
    }

    @Override
    public void delete(String id) throws SQLException {
        namedParameterJdbcTemplate.getJdbcOperations().execute("delete from comics where series ='" + id + "'");
    }

    @Override
    public void update(Comic object) throws SQLException {
        namedParameterJdbcTemplate.getJdbcOperations().execute("update comics set title='" + object.getTitle()
                + "', info='" + object.getInfo() + "' where series='"
                + object.getSeries() + "'");
    }

    @Override
    public List<Comic> findAll() throws SQLException {
        return namedParameterJdbcTemplate.query("select * from comics", mapper);
    }
}
