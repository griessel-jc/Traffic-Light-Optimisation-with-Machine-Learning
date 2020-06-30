/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aegis.aegis.dao;

import com.aegis.aegis.modal.Role;
import com.aegis.aegis.modal.User;
import dto.loginDto;
import exception.BadRequestException;
import exception.RecordNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javassist.NotFoundException;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAOImplemented implements UserDAO{

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
        Session currSession = entityManager.unwrap(Session.class);
        User user = currSession.get(User.class, id);
        return user;
    }

    @Override
    public void delete(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        User user = currSession.get(User.class, id);
        currSession.delete(user);
    }
    
    @Override
    public boolean checkLogin(String username, String password){
        Session currSession = entityManager.unwrap(Session.class );
        boolean userFound = false;
        //Query using Hibernate Query Language
        //String SQL_QUERY = "from User as o where o.username='"+username+"' and o.password='"+password+"'";
        String SQL_QUERY = "from User as o where o.username=?0 and password=?1";
        Query query = currSession.createQuery(SQL_QUERY);
        query.setParameter(0,username);
        query.setParameter(1,password);
        List list = query.list();
        if((list != null) /*&& (list.size() > 0)*/&& (!list.isEmpty()) ){
            userFound = true;
        }
        currSession.close();
        return userFound;
    }
    
    @Override
    public void save(loginDto user) {
        User u;
        try{
             u = this.findByUsername(user.getUsername());
             if(u != null ) 
                throw new BadRequestException("That username is already taken.",user.getUsername());
        }catch(RecordNotFoundException re ){
            u = new User();
            u.setRole_Id(2);
            u.setPassword(user.getPassword());
            u.setUsername(user.getUsername());
            Session currSession = entityManager.unwrap(Session.class);
            currSession.saveOrUpdate(u);
        }
    }
    
    @Override
    public User findByUsername(String username){
        Session currSession = entityManager.unwrap(Session.class);
        String SQL_QUERY = "from User as o where o.username=?0";
        Query query = currSession.createQuery(SQL_QUERY);
        query.setParameter(0,username);
        List list = query.list();
        if((list != null) && (!list.isEmpty())){
            return (User) list.get(0);
        }
        throw new RecordNotFoundException("No User record exists for given username",username);
    }
    
}
