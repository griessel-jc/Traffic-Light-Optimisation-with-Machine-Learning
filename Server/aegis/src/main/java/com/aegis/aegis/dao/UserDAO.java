/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aegis.aegis.dao;

import com.aegis.aegis.modal.User;
import dto.loginDto;
import java.util.List;

/**
 *
 * @author rudo5
 */
public interface UserDAO {
    
    public List<User> get();
    public User get(int id);
    public void save(loginDto user);
    public void saveEncrypted(loginDto user);
    public void delete(int id);
    public User checkLogin(String username, String password);
    public boolean validate(loginDto user);
    public User findByUsername(String username);
}
