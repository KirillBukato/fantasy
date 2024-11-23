package by.bsu.fantasy.controller;

import by.bsu.fantasy.model.User;
import by.bsu.fantasy.service.UserPlayerService;
import by.bsu.fantasy.service.UserService;
import by.bsu.fantasy.service.UserTeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final UserPlayerService userPlayerService;
    private final UserTeamService userTeamService;

    public UserController(UserService userService, UserPlayerService userPlayerService, UserTeamService userTeamService) {
        this.userService = userService;
        this.userPlayerService = userPlayerService;
        this.userTeamService = userTeamService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/user/{id}/addPlayer/{playerId}")
    public User addPlayer(@PathVariable Long id, @PathVariable Long playerId) {
        return userPlayerService.linkPlayerToUser(id, playerId);
    }

    @PutMapping("/user/{id}/addTeam/{teamId}")
    public User addTeam(@PathVariable Long id, @PathVariable Long teamId) {
        return userTeamService.linkTeamToUser(id, teamId);
    }

}
