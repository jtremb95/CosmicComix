package cosmic.comix.dao.impl;

import cosmic.comix.Application;
import cosmic.comix.BaseControllerTest;
import cosmic.comix.domain.Favorites;
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
public class FavoritesDaoImplTest extends BaseControllerTest{

    private EmbeddedDatabase db;
    FavoritesDaoImpl favoritesService;

    @Before
    public void setUpBefore() throws SQLException {
        db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:test.sql").build();
        favoritesService = new FavoritesDaoImpl();
        favoritesService.setDataSource(db);
    }

    @After
    public void tearDown() {
        db.shutdown();
    }

    @Test
    public void testFindByIdPositive() throws SQLException {
        Favorites favorites = favoritesService.findById("joe");
        assertEquals( "Amazing Spiderman", favorites.getSeries());
    }

    @Test
    public void testCreatePositive() throws SQLException {
        assertEquals( null, favoritesService.findById("Test").getSeries());
        Favorites favorites = new Favorites();
        favorites.setUsername("Test");
        favorites.setSeries("Series");
        favorites.setTitle("Title");
        favoritesService.insert(favorites);
        assertEquals("Series", favoritesService.findById("Test").getSeries());
    }

    @Test
    public void testDeletePositive() throws SQLException {
        assertEquals( "Amazing Spiderman", favoritesService.findById("joe").getSeries());
        favoritesService.delete("joe");
        assertEquals(null, favoritesService.findById("joe").getSeries());
    }

    @Test
    public void testUpdatePositive() throws SQLException {
        assertEquals("Amazing Spiderman", favoritesService.findById("joe").getSeries());
        Favorites favorites = new Favorites();
        favorites.setSeries("Amazing Spiderman");
        favorites.setTitle("Superwoman");
        favorites.setUsername("joe");
        favoritesService.update(favorites);
        assertEquals( "Amazing Spiderman", favoritesService.findById("joe").getSeries());
        assertEquals("Superwoman", favoritesService.findById("joe").getTitle());
    }

    @Test
    public void testFindAll() throws SQLException {
        List<Favorites> favoritesList = favoritesService.findAll();
        assertEquals("User List", 1, favoritesList.size());
    }
}
