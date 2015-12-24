package cosmic.comix.service.impl;

import cosmic.comix.Application;
import cosmic.comix.BaseControllerTest;
import cosmic.comix.dao.impl.UsersDaoImpl;
import cosmic.comix.domain.Users;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by NSchneier on 6/28/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
public class UsersServiceImplTest extends BaseControllerTest {

    private EmbeddedDatabase db;
    UsersServiceImpl usersService;
    private Connection connection;
    private UsersDaoImpl usersDao;

    @Before
    public void setUpBefore() throws SQLException {
        db = mock(EmbeddedDatabase.class);
        connection = mock(Connection.class);
        when(db.getConnection()).thenReturn(connection);
        usersDao = mock(UsersDaoImpl.class);
        usersService = new UsersServiceImpl(usersDao);
        usersService.setDataSource(db);
    }

    @Test
    public void testFindByIdPositive() throws SQLException {
        Users users = new Users();
        users.setPassword("Password");
        users.setUsername("Username");
        when(usersDao.findById(anyString())).thenReturn(users);
        users = usersService.findById("Amazing Spiderman");
        assertEquals( "Username", users.getUsername());
    }

    @Test
    public void testCreatePositive() throws SQLException {
        assertEquals( null, usersService.findById("Series"));
        Users users = new Users();
        users.setPassword("Password");
        users.setUsername("Username");
        doNothing().when(usersDao).insert(users);
        when(usersDao.findById(anyString())).thenReturn(users);
        usersService.create(users);
        assertEquals("Username", usersService.findById("Series").getUsername());
    }

    @Test
    public void testDeletePositive() throws SQLException {
        Users users = new Users();
        users.setPassword("Password");
        users.setUsername("Username");
        doNothing().when(usersDao).insert(users);
        when(usersDao.findById(anyString())).thenReturn(users);
        usersService.delete("Series");
        assertEquals("Username", usersService.findById("Series").getUsername());
    }

    @Test
    public void testUpdatePositive() throws SQLException {
        Users users = new Users();
        users.setPassword("Password");
        users.setUsername("Username");
        doNothing().when(usersDao).update(users);
        when(usersDao.findById(anyString())).thenReturn(users);
        assertEquals("Username", usersService.findById("Series").getUsername());

        users.setUsername("This is not real");
        usersService.update(users);
        assertEquals("This is not real", usersService.findById("Series").getUsername());
    }

    @Test
    public void testFindAll() throws SQLException {
        List<Users> usersList = usersService.findAll();
        assertEquals("User List", 0, usersList.size());
        List<Users> comics = new ArrayList<Users>();
        Users users = new Users();
        comics.add(users);
        when(usersDao.findAll()).thenReturn(comics);
        List<Users> comicsList = usersService.findAll();
        assertEquals("User List", 1, comicsList.size());
    }
}
