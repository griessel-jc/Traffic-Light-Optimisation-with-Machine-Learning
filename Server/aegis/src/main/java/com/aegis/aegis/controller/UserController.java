/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aegis.aegis.controller;


import dto.loginDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.aegis.aegis.modal.User;
import com.aegis.aegis.service.UserService;
import dto.userDto;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    
    
    @GetMapping("/getUsers")
    public List<User> get() {
        return userService.get();
    }
    
    /*
    @PostMapping("/saveUser")
    public User save(@RequestBody User user) {
        userService.save(user);
        return user;
    }
    */
    
    @GetMapping("/getUser/{id}")
    public User get(@PathVariable int id) {
        return userService.get(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String delete(@PathVariable int id) {

        userService.delete(id);

        return "Employee removed with id " + id;

    }
    /*
    @PutMapping("/user")
    public User update(@RequestBody User user) {
        userService.save(user);
        return user;
    }
    */
    @PostMapping("/findByUsername")
    public User FindByUsername(@RequestBody userDto username){
        return userService.findByUsername(username.getUsername());
    }
    
    @PostMapping("/login")
    public User Login(@RequestBody loginDto login){
        return userService.checkLogin(login.getUsername(), login.getPassword());
    }
    
    @PostMapping("/register")
    public String Register(@RequestBody loginDto regUser){
        userService.save(regUser);
        return "Success";
    }

}
