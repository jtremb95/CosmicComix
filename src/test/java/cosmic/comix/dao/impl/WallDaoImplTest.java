package cosmic.comix.dao.impl;

import cosmic.comix.Application;
import cosmic.comix.BaseControllerTest;
import cosmic.comix.domain.Wall;
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
public class WallDaoImplTest extends BaseControllerTest{

    private EmbeddedDatabase db;
    WallDaoImpl wallService;

    @Before
    public void setUpBefore() throws SQLException {
        db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:test.sql").build();
        wallService = new WallDaoImpl();
        wallService.setDataSource(db);
    }

    @After
    public void tearDown() {
        db.shutdown();
    }

    @Test
    public void testFindByIdPositive() throws SQLException {
        Wall wall = wallService.findById("joe");
        assertEquals( "joe", wall.getReceiver());
    }

    @Test
    public void testCreatePositive() throws SQLException {
        assertEquals( null, wallService.findById("Test").getReceiver());
        Wall wall = new Wall();
        wall.setMessage("Message");
        wall.setSender("Sender");
        wall.setReceiver("Receiver");
        wallService.insert(wall);
        assertEquals("Receiver", wallService.findById("Receiver").getReceiver());
    }

    @Test
    public void testDeletePositive() throws SQLException {
        assertEquals( "joe", wallService.findById("joe").getReceiver());
        wallService.delete("joe");
        assertEquals(null, wallService.findById("joe").getReceiver());
    }

    @Test
    public void testUpdatePositive() throws SQLException {
        assertEquals("joe", wallService.findById("joe").getReceiver());
        Wall wall = new Wall();
        wall.setMessage("Message");
        wall.setSender("Sender");
        wall.setReceiver("joe");
        wallService.update(wall);
        assertEquals( "joe", wallService.findById("joe").getReceiver());
        assertEquals("Message", wallService.findById("joe").getMessage());
    }

    @Test
    public void testFindAll() throws SQLException {
        List<Wall> wallList = wallService.findAll();
        assertEquals("User List", 1, wallList.size());
    }
}
