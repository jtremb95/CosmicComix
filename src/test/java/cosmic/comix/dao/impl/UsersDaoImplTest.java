package cosmic.comix.dao.impl;

import cosmic.comix.Application;
import cosmic.comix.BaseControllerTest;
import cosmic.comix.domain.Users;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by NSchneier on 6/28/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
public class UsersDaoImplTest extends BaseControllerTest {

    private EmbeddedDatabase db;
    private UsersDaoImpl usersService;

    @Before
    public void setUpBefore() throws SQLException {
        db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:test.sql").build();
        usersService = new UsersDaoImpl();
        usersService.setDataSource(db);
    }

    @After
    public void tearDown() throws SQLException {
        Users users = new Users();
        users.setUsername("joe");
        users.setPassword("tester");
        usersService.insert(users);
        db.shutdown();
    }

    @Test
    public void testFindByIdPositive() throws SQLException {
        Users user = usersService.findById("joe");
        assertEquals("Username should be Joe", "joe", user.getUsername());
    }

    @Test
    public void testFindByIdNegative() throws SQLException {
        Users user = usersService.findById("bob");
        assertEquals("Username should be Joe", "", user.getUsername());
    }

    @Test
    public void testCreatePositive() throws SQLException {
        assertEquals("Username Jason should not be used", "", usersService.findById("jason").getUsername());
        Users users = new Users();
        users.setUsername("jason");
        users.setPassword("test");
        usersService.insert(users);
        assertEquals("Username should be Jason", "jason", usersService.findById("jason").getUsername());
    }

    @Test
    public void testDeletePositive() throws SQLException {
        assertEquals("Username should be Joe", "joe", usersService.findById("joe").getUsername());
        usersService.delete("joe");
        assertEquals("Username should not be Joe", "", usersService.findById("joe").getUsername());
    }

    @Test
    public void testUpdatePositive() throws SQLException {
        assertEquals("Username should be Joe", "joe", usersService.findById("joe").getUsername());
        Users users = new Users();
        users.setUsername("joe");
        users.setPassword("updated");
        usersService.update(users);
        assertEquals("Username should not be Joe", "joe", usersService.findById("joe").getUsername());
        assertEquals("Username should not be Joe", "updated", usersService.findById("joe").getPassword());
    }

    @Test
    public void testFindAll() throws SQLException {
        List<Users> usersList = usersService.findAll();
        assertEquals("User List", 1, usersList.size());
    }
}
