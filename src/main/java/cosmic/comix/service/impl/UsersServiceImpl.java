package cosmic.comix.service.impl;

import cosmic.comix.dao.CosmicsDao;
import cosmic.comix.dao.impl.UsersDaoImpl;
import cosmic.comix.domain.Users;
import cosmic.comix.service.ServiceInterface;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSchneier on 6/3/2015.
 */
@Service
public class UsersServiceImpl implements ServiceInterface<Users, String>{

    private CosmicsDao<Users, String> dao;

    public UsersServiceImpl() throws SQLException {
        dao = new UsersDaoImpl();
    }

    public UsersServiceImpl(UsersDaoImpl usersService) throws SQLException {
        this.dao = usersService;
    }

    @Override
    public void create(Users object) throws SQLException {
        dao.insert(object);
    }

    @Override
    public void delete(String id) throws SQLException {
        dao.delete(id);
    }


    @Override
    public List<Users> findAll() throws SQLException {
        return dao.findAll();
    }

    @Override
    public Users findById(String id) throws SQLException {
        return dao.findById(id);

    }

    @Override
    public void update(Users object) throws SQLException {
        dao.update(object);
    }

    @Override
    public DataSource getDataSource() {
        return ((UsersDaoImpl) dao).getDataSource();
    }

    public void setDataSource(DataSource dataSource) {
        ((UsersDaoImpl)dao).setDataSource(dataSource);
    }


}
