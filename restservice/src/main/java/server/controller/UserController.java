package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.Song;
import server.User;
import server.user.UserRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/server/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public User getUser(@RequestBody User user) {
        if( userRepository.getOne(user.getUsername()) != null){
            user.setResult(0);
        } else {
            user.setResult(1);
        }
        return user;
    }
}
