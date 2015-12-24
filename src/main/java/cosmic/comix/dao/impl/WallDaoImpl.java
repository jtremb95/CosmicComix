package cosmic.comix.dao.impl;

import cosmic.comix.dao.AbstractCosmicsDao;
import cosmic.comix.dao.CosmicsDao;
import cosmic.comix.dao.mapper.WallMapper;
import cosmic.comix.domain.Comic;
import cosmic.comix.domain.Wall;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSchneier on 6/17/2015.
 */
public class WallDaoImpl extends AbstractCosmicsDao<Comic, String>
        implements CosmicsDao<Wall, String> {
    public WallDaoImpl() throws SQLException {
        super();
        mapper = new WallMapper();
    }

    @Override
    public void insert(Wall object) throws SQLException {
        namedParameterJdbcTemplate.getJdbcOperations().execute("insert into wall (receiver, sender, message) values ('"
                + object.getReceiver() + "', '" + object.getSender() + "', '"
                + object.getMessage() + "')");
    }

    @Override
    public Wall findById(String id) throws SQLException {
        List<Wall> list = namedParameterJdbcTemplate.query("select * from wall where receiver='"
                + id + "'", mapper);
        Wall wall = new Wall();
        if (list.size() > 0) {
            wall = list.get(0);
        }
        return wall;
    }

    @Override
    public void delete(String id) throws SQLException {
        namedParameterJdbcTemplate.getJdbcOperations().execute("delete from wall where receiver ='" + id + "'");
    }

    @Override
    public void update(Wall object) throws SQLException {
        namedParameterJdbcTemplate.getJdbcOperations().execute("update wall set sender='" + object.getSender()
                + "', message='" + object.getMessage() + "' where receiver='"
                + object.getReceiver() + "'");
    }

    @Override
    public List<Wall> findAll() throws SQLException  {
        return namedParameterJdbcTemplate.query("select * from wall", mapper);
    }

}
