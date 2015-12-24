package cosmic.comix.service.impl;

import cosmic.comix.Application;
import cosmic.comix.BaseControllerTest;
import cosmic.comix.dao.impl.FavoritesDaoImpl;
import cosmic.comix.domain.Favorites;
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
                         DirtiesContextTestExecutionListener.class,
                         TransactionalTestExecutionListener.class})
public class FavoritesServiceImplTest extends BaseControllerTest{

    private EmbeddedDatabase db;
    FavoritesServiceImpl favoritesService;
    private Connection connection;
    private FavoritesDaoImpl favoritesDao;

    @Before
    public void setUpBefore() throws SQLException {
        db = mock(EmbeddedDatabase.class);
        connection = mock(Connection.class);
        when(db.getConnection()).thenReturn(connection);
        favoritesDao = mock(FavoritesDaoImpl.class);
        favoritesService = new FavoritesServiceImpl(favoritesDao);
        favoritesService.setDataSource(db);
    }

    @Test
    public void testFindByIdPositive() throws SQLException {
        Favorites favorites = new Favorites();
        favorites.setSeries("Series");
        favorites.setUsername("Username");
        favorites.setTitle("Title");
        when(favoritesDao.findById(anyString())).thenReturn(favorites);
        favorites = favoritesService.findById("Amazing Spiderman");
        assertEquals( "Series", favorites.getSeries());
    }

    @Test
    public void testCreatePositive() throws SQLException {
        assertEquals( null, favoritesService.findById("Series"));
        Favorites favorites = new Favorites();
        favorites.setTitle("Title");
        favorites.setUsername("Username");
        favorites.setSeries("Series");
        doNothing().when(favoritesDao).insert(favorites);
        when(favoritesDao.findById(anyString())).thenReturn(favorites);
        favoritesService.create(favorites);
        assertEquals("Series", favoritesService.findById("Series").getSeries());
    }

    @Test
    public void testDeletePositive() throws SQLException {
        Favorites favorites = new Favorites();
        favorites.setSeries("Series");
        favorites.setUsername("Username");
        favorites.setTitle("Title");
        doNothing().when(favoritesDao).insert(favorites);
        when(favoritesDao.findById(anyString())).thenReturn(favorites);
        favoritesService.delete("Series");
        assertEquals("Series", favoritesService.findById("Series").getSeries());
    }

    @Test
    public void testUpdatePositive() throws SQLException {
        Favorites favorites = new Favorites();
        favorites.setSeries("Series");
        favorites.setUsername("Username");
        favorites.setTitle("Title");
        doNothing().when(favoritesDao).update(favorites);
        when(favoritesDao.findById(anyString())).thenReturn(favorites);
        assertEquals("Series", favoritesService.findById("Series").getSeries());

        favorites.setSeries("This is not real");
        favoritesService.update(favorites);
        assertEquals("This is not real", favoritesService.findById("Series").getSeries());
    }

    @Test
    public void testFindAll() throws SQLException {
        List<Favorites> favoritesList = favoritesService.findAll();
        assertEquals("User List", 0, favoritesList.size());
        List<Favorites> comics = new ArrayList<Favorites>();
        Favorites favorites = new Favorites();
        comics.add(favorites);
        when(favoritesDao.findAll()).thenReturn(comics);
        List<Favorites> comicsList = favoritesService.findAll();
        assertEquals("User List", 1, comicsList.size());
    }
}
