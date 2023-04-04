package com.example.pps.controller;


import com.example.pps.entity.User;
import com.example.pps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/skalometr")
public class SignupController {

    private final UserRepository userRepository;

    @Autowired
    public SignupController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = (List<User>) userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/signup")
    public String getSignup() {
        return "signup";
    }

    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> signup(
            @RequestParam(name = "nickname") String nickname,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "second_name") String second_name,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password
            ) {
        User user = new User(nickname, name, second_name, email, phone, password);
        System.out.println(userRepository.findAll());
        Optional<User> row =  userRepository.findByNickname(nickname);
        Optional<User> row1 =  userRepository.findByEmail(email);
        Optional<User> row2 =  userRepository.findByPhone(phone);
        if (row.isEmpty() && row1.isEmpty() && row2.isEmpty()) {
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
