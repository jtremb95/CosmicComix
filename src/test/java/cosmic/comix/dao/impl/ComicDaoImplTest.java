package cosmic.comix.dao.impl;

import cosmic.comix.Application;
import cosmic.comix.BaseControllerTest;
import cosmic.comix.domain.Comic;
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
public class ComicDaoImplTest extends BaseControllerTest{

    private EmbeddedDatabase db;
    ComicDaoImpl comicService;

    @Before
    public void setUpBefore() throws SQLException {
        db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:test.sql").build();
        comicService = new ComicDaoImpl();
        comicService.setDataSource(db);
    }

    @After
    public void tearDown() {
        db.shutdown();
    }

    @Test
    public void testFindByIdPositive() throws SQLException {
        Comic comic = comicService.findById("Amazing Spiderman");
        assertEquals( "Amazing Spiderman", comic.getSeries());
    }

    @Test
    public void testCreatePositive() throws SQLException {
        assertEquals( null, comicService.findById("Series").getSeries());
        Comic comics = new Comic();
        comics.setInfo("Info");
        comics.setSeries("Series");
        comics.setTitle("Title");
        comicService.insert(comics);
        assertEquals("Info", comicService.findById("Series").getInfo());
    }

    @Test
    public void testDeletePositive() throws SQLException {
        assertEquals( "Amazing Spiderman", comicService.findById("Amazing Spiderman").getSeries());
        comicService.delete("Amazing Spiderman");
        assertEquals(null, comicService.findById("Amazing Spiderman").getSeries());
    }

    @Test
    public void testUpdatePositive() throws SQLException {
        assertEquals("Amazing Spiderman", comicService.findById("Amazing Spiderman").getSeries());
        Comic comic = new Comic();
        comic.setSeries("Amazing Spiderman");
        comic.setTitle("Superwoman");
        comic.setInfo("This is not real");
        comicService.update(comic);
        assertEquals( "Amazing Spiderman", comicService.findById("Amazing Spiderman").getSeries());
        assertEquals("Superwoman", comicService.findById("Amazing Spiderman").getTitle());
    }

    @Test
    public void testFindAll() throws SQLException {
        List<Comic> comicsList = comicService.findAll();
        assertEquals("User List", 1, comicsList.size());
    }
}
