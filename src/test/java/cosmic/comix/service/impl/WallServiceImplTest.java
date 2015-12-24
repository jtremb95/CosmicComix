package cosmic.comix.service.impl;

import cosmic.comix.Application;
import cosmic.comix.BaseControllerTest;
import cosmic.comix.dao.impl.WallDaoImpl;
import cosmic.comix.domain.Wall;
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
public class WallServiceImplTest extends BaseControllerTest{

    private EmbeddedDatabase db;
    WallServiceImpl wallService;
    private Connection connection;
    private WallDaoImpl wallDao;

    @Before
    public void setUpBefore() throws SQLException {
        db = mock(EmbeddedDatabase.class);
        connection = mock(Connection.class);
        when(db.getConnection()).thenReturn(connection);
        wallDao = mock(WallDaoImpl.class);
        wallService = new WallServiceImpl(wallDao);
        wallService.setDataSource(db);
    }

    @Test
    public void testFindByIdPositive() throws SQLException {
        Wall wall = new Wall();
        wall.setReceiver("Receiver");
        wall.setSender("Sender");
        wall.setMessage("Message");
        when(wallDao.findById(anyString())).thenReturn(wall);
        wall = wallService.findById("Amazing Spiderman");
        assertEquals( "Message", wall.getMessage());
    }

    @Test
    public void testCreatePositive() throws SQLException {
        assertEquals( null, wallService.findById("Series"));
        Wall wall = new Wall();
        wall.setReceiver("Receiver");
        wall.setSender("Sender");
        wall.setMessage("Message");
        doNothing().when(wallDao).insert(wall);
        when(wallDao.findById(anyString())).thenReturn(wall);
        wallService.create(wall);
        assertEquals("Message", wallService.findById("Series").getMessage());
    }

    @Test
    public void testDeletePositive() throws SQLException {
        Wall wall = new Wall();
        wall.setReceiver("Receiver");
        wall.setSender("Sender");
        wall.setMessage("Message");
        doNothing().when(wallDao).insert(wall);
        when(wallDao.findById(anyString())).thenReturn(wall);
        wallService.delete("Series");
        assertEquals("Message", wallService.findById("Series").getMessage());
    }

    @Test
    public void testUpdatePositive() throws SQLException {
        Wall wall = new Wall();
        wall.setReceiver("Receiver");
        wall.setSender("Sender");
        wall.setMessage("Message");
        doNothing().when(wallDao).update(wall);
        when(wallDao.findById(anyString())).thenReturn(wall);
        assertEquals("Message", wallService.findById("Series").getMessage());

        wall.setMessage("Message2");
        wallService.update(wall);
        assertEquals("Message2", wallService.findById("Series").getMessage());
    }

    @Test
    public void testFindAll() throws SQLException {
        List<Wall> wallList = wallService.findAll();
        assertEquals("User List", 0, wallList.size());
        List<Wall> comics = new ArrayList<Wall>();
        Wall wall = new Wall();
        comics.add(wall);
        when(wallDao.findAll()).thenReturn(comics);
        List<Wall> comicsList = wallService.findAll();
        assertEquals("User List", 1, comicsList.size());
    }
}
