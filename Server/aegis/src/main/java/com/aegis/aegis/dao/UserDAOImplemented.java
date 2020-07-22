/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aegis.aegis.dao;

import Util.AesUtil;
import com.aegis.aegis.modal.Role;
import com.aegis.aegis.modal.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.adminDto;
import dto.loginDto;
import exception.BadGatewayException;
import exception.RecordNotFoundException;
import exception.UnauthorizedException;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.UUID;
import javassist.NotFoundException;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Repository
public class UserDAOImplemented implements UserDAO {
    private final static int workload = 12;
    private final static String PASSWORD = "This is a password";
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> get() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<User> query = currSession.createQuery("from User", User.class);
        List<User> list = query.getResultList();
        return list;
    }

    @Override
    public User get(int id) {
        try {
            Session currSession = entityManager.unwrap(Session.class);
            User user = currSession.get(User.class, id);
            return user;
        } catch (Exception ex) {
            throw new RecordNotFoundException("No User record exists for given id", id + "");
        }

    }

    @Override
    public void delete(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        User user = currSession.get(User.class, id);
        currSession.delete(user);
    }

    @Override
    public User checkLogin(String username, String password) {
        try {
            String decryptedPassword = new String(java.util.Base64.getDecoder().decode(password));
            AesUtil aesUtil = new AesUtil(128,1000);
            String pw = "";
            if(decryptedPassword != null && decryptedPassword.split("::").length == 3){
                pw = aesUtil.decrypt(decryptedPassword.split("::")[1], decryptedPassword.split("::")[0], PASSWORD, decryptedPassword.split("::")[2]);
                User user = this.findByUsername(username);
                if (BCrypt.checkpw(pw,user.getPassword()) /*user.getPassword().equals(password)*/) {
                    return user;
                }
                throw new UnauthorizedException("Invalid login ", "");
            }else{
                throw new BadGatewayException("Invalid user fields", "");
            }
            
        } catch (RecordNotFoundException ex) {
            throw new UnauthorizedException("Invalid login ", "");
        }
    }

    @Override
    public void save(loginDto user) {
        User u;
        try {
            u = this.findByUsername(user.getUsername());
            if (u != null) {
                throw new BadGatewayException("That username is already taken.", user.getUsername());
            }
        } catch (RecordNotFoundException re) {
            if (validate(user)) {
                AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
                textEncryptor.setPassword(PASSWORD);
                u = new User();
                u.setRole_Id(2);
                u.setUsername(user.getUsername());
                u.setPassword(textEncryptor.encrypt(user.getPassword()));
                Session currSession = entityManager.unwrap(Session.class);
                currSession.saveOrUpdate(u);
            } else {
                throw new BadGatewayException("Invalid user fields", "");
            }
        }
    }

    @Override
    public void saveEncrypted(loginDto encrypted) {
        loginDto user = new loginDto();
        User u;
        try {
            String decryptedPassword = new String(java.util.Base64.getDecoder().decode(encrypted.getPassword()));
            AesUtil aesUtil = new AesUtil(128,1000);
            String pw = "";
            if(decryptedPassword != null && decryptedPassword.split("::").length == 3){
                pw = aesUtil.decrypt(decryptedPassword.split("::")[1], decryptedPassword.split("::")[0], PASSWORD, decryptedPassword.split("::")[2]);
                String pw_hash = BCrypt.hashpw(pw,BCrypt.gensalt());
                user.setUsername(encrypted.getUsername());
                user.setPassword(pw_hash);
            }else{
                throw new BadGatewayException("Invalid user fields", "");
            }
            
            
            
            u = this.findByUsername(user.getUsername());
            if (u != null) {
                throw new BadGatewayException("That username is already taken.", user.getUsername());
            }
        }catch (RecordNotFoundException re) {
            if (validate(user)) {
                u = new User();
                u.setRole_Id(2);
                u.setUsername(user.getUsername());
                u.setPassword(user.getPassword());
                Session currSession = entityManager.unwrap(Session.class);
                currSession.saveOrUpdate(u);
            } else {
                throw new BadGatewayException("Invalid user fields", "");
            }
        }catch(Exception e){
            throw new BadGatewayException("Could not decrypt", "");
        }
    }

    @Override
    public boolean validate(loginDto user) {
        return (user.getUsername().length() >= 6 && user.getUsername().length() < 12) && (user.getPassword().length() >= 8 || user.getPassword().length() < 20);
    }

    @Override
    public User findByUsername(String username) {
        Session currSession = entityManager.unwrap(Session.class);
        String SQL_QUERY = "from User as o where o.username=?0";
        Query query = currSession.createQuery(SQL_QUERY);
        query.setParameter(0, username);
        List list = query.list();
        if ((list != null) && (!list.isEmpty())) {
            User user = (User) list.get(0);
            //currSession.close();
            return user;
            //return (User) list.get(0);
        }
//        currSession.close();
        throw new RecordNotFoundException("No User record exists for given username", username);
    }

    @Override
    public void changeRole(adminDto admin){
        User user = this.findByUsername(admin.getUsername());
        Session currSession = entityManager.unwrap(Session.class);
        if(user != null && user.getRole().getName().equalsIgnoreCase("admin")){ 
            
            String SQL_QUERY = "from User as o where o.id=?0";
            Query query = currSession.createQuery(SQL_QUERY);
            query.setParameter(0, admin.getId());
            List list = query.list();
            if ((list != null) && (!list.isEmpty())) {
                User u = (User) list.get(0);
                u.setRole_Id(admin.getRole_Id());
                currSession.saveOrUpdate(u);
            }else throw new RecordNotFoundException("No User record exists for given id", admin.getId().toString());
        }
    }
}
