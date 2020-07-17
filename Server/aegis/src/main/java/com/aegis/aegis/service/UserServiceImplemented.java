/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aegis.aegis.service;

import com.aegis.aegis.dao.TrafficlightDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aegis.aegis.dao.UserDAO;
import com.aegis.aegis.modal.User;
import dto.adminDto;
import dto.loginDto;

@Service
public class UserServiceImplemented implements UserService {
    @Autowired
    private UserDAO userDao;
     
    @Transactional
    @Override
    public List<User> getUsers() {
        return userDao.get();
    }
 
    @Transactional
    @Override
    public void changeRole(adminDto admin){
        userDao.changeRole(admin);
    }
    
    @Transactional
    @Override
    public User getUser(int id) {
        return userDao.get(id);
    }

    @Transactional
    @Override
    public void save(loginDto user) {
        userDao.save(user);
    }

    @Transactional 
    @Override
    public void saveEncrypted(loginDto user){
        userDao.saveEncrypted(user);
    }
    
    @Transactional
    @Override
    public void delete(int id) {
        userDao.delete(id);

    }
    
    @Override
    public User checkLogin(String username, String password){
        return userDao.checkLogin(username, password);
    }
    
    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

}
