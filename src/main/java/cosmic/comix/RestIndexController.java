package cosmic.comix;

/**
 * Created by NSchneier on 4/15/2015.
 */

import cosmic.comix.domain.Users;
import cosmic.comix.service.ServiceInterface;
import cosmic.comix.service.impl.UsersServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/rest")
public class RestIndexController {
    private ServiceInterface<Users, String> userService;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public Users ping() throws SQLException {
        userService = new UsersServiceImpl();
        return userService.findById("joe");
    }

    /**
     * Login Rest POST Request
     * Verifies the username and password
     * @param user - the user objects
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(@RequestBody Users user) throws SQLException {
        userService = new UsersServiceImpl();
        Users response = userService.findById(user.getUsername());
        return response.getPassword().equals(user.getPassword());
    }

    /**
     * Register a user POST Request
     * Creates the user
     * @param user - the user to be created
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public boolean register(@RequestBody Users user) throws SQLException {
        userService = new UsersServiceImpl();
        userService.create(user);
        Users response = userService.findById(user.getUsername());
        return response.getPassword().equals(user.getPassword());
    }

    /**
     * remove a user POST Request
     * deletes the user
     * @param user - the user to be deleted
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public boolean deleteUser(@RequestBody Users user) throws SQLException {
        userService = new UsersServiceImpl();
        userService.delete(user.getUsername());
        Users response = userService.findById(user.getUsername());
        return response.getPassword().equals("");
    }
}