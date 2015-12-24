package cosmic.comix.service.impl;

import cosmic.comix.Application;
import cosmic.comix.BaseControllerTest;
import cosmic.comix.dao.impl.ComicDaoImpl;
import cosmic.comix.domain.Comic;
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
public class ComicServiceImplTest extends BaseControllerTest{

    private EmbeddedDatabase db;
    ComicServiceImpl comicService;
    private Connection connection;
    private ComicDaoImpl comicDao;

    @Before
    public void setUpBefore() throws SQLException {
        db = mock(EmbeddedDatabase.class);
        connection = mock(Connection.class);
        when(db.getConnection()).thenReturn(connection);
        comicDao = mock(ComicDaoImpl.class);
        comicService = new ComicServiceImpl(comicDao);
        comicService.setDataSource(db);
    }


    @Test
    public void testFindByIdPositive() throws SQLException {
        Comic comic = new Comic();
        comic.setSeries("Amazing Spiderman");
        comic.setTitle("Superwoman");
        comic.setInfo("This is real");
        when(comicDao.findById(anyString())).thenReturn(comic);
        Comic comics = comicService.findById("Amazing Spiderman");
        assertEquals( "Amazing Spiderman", comics.getSeries());
    }

    @Test
    public void testCreatePositive() throws SQLException {
        assertEquals( null, comicService.findById("Series"));
        Comic comics = new Comic();
        comics.setInfo("Info");
        comics.setSeries("Series");
        comics.setTitle("Title");
        doNothing().when(comicDao).insert(comics);
        when(comicDao.findById(anyString())).thenReturn(comics);
        comicService.create(comics);
        assertEquals("Info", comicService.findById("Series").getInfo());
    }

    @Test
    public void testDeletePositive() throws SQLException {
        Comic comic = new Comic();
        comic.setSeries("Amazing Spiderman");
        comic.setTitle("Superwoman");
        comic.setInfo("This is real");
        doNothing().when(comicDao).insert(comic);
        when(comicDao.findById(anyString())).thenReturn(comic);
        comicService.delete("Amazing Spiderman");
        assertEquals("Amazing Spiderman", comicService.findById("Amazing Spiderman").getSeries());
    }

    @Test
    public void testUpdatePositive() throws SQLException {
        Comic comic = new Comic();
        comic.setSeries("Amazing Spiderman");
        comic.setTitle("Superwoman");
        comic.setInfo("This is real");
        doNothing().when(comicDao).update(comic);
        when(comicDao.findById(anyString())).thenReturn(comic);
        assertEquals("Amazing Spiderman", comicService.findById("Amazing Spiderman").getSeries());

        comic.setSeries("This is not real");
        comicService.update(comic);
        assertEquals("This is not real", comicService.findById("Amazing Spiderman").getSeries());
    }

    @Test
    public void testFindAll() throws SQLException {
        List<Comic> comics = new ArrayList<Comic>();
        Comic comic = new Comic();
        comics.add(comic);
        when(comicDao.findAll()).thenReturn(comics);
        List<Comic> comicsList = comicService.findAll();
        assertEquals("User List", 1, comicsList.size());
    }
}
