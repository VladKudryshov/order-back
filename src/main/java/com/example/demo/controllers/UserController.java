package com.example.demo.controllers;

import com.example.demo.controllers.abstr.IControllerApp;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.Role;
import com.example.demo.service.IUsersService;
import com.example.demo.core.utils.ProjectUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "/api/users", description = "Get information about users")
@ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid request"),
        @ApiResponse(code = 401, message = "Unauthorized")})
@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = {"https://products-order.herokuapp.com"})
public class UserController extends IControllerApp<User,Integer> {

    private final static Logger LOGGER = ProjectUtils.getLogger(UserController.class);

    @Autowired
    IUsersService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    protected User get(@PathVariable Integer id) {
        return service.get(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    protected List<User> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "changeInfo", method = RequestMethod.PUT)
    protected User edit(@RequestBody User entity) {
        return service.edit(entity);
    }

    @RequestMapping(value = "changeEmail", method = RequestMethod.PUT)
    protected HttpStatus changeEmail(@RequestBody() Map<String, Object> params) {

        return HttpStatus.OK;
    }

    @RequestMapping(value = "{id}/changePassword", method = RequestMethod.PUT)
    protected HttpStatus changePassword(
            @PathVariable Integer id,
            @RequestBody String password) {
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        service.changePassword(user);
        return HttpStatus.OK;
    }

    @RequestMapping(value = "{id}/changeRole", method = RequestMethod.PUT)
    protected ResponseEntity<User> changeRole( @PathVariable Integer id,
                                                Role role) {
        User user = new User();
        user.setId(id);
        user.setRole(role);
        return new ResponseEntity<>(service.changeRole(user),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    protected void remove(@PathVariable Integer id) {
        service.remove(id);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public void removeAll() {
        service.removeAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    protected User save(@RequestBody User entity) {
        return service.save(entity);
    }
}
