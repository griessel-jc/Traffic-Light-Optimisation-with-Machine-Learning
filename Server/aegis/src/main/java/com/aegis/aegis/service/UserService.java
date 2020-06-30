 
package com.aegis.aegis.service;

import com.aegis.aegis.modal.User; 
import java.util.List;

public interface UserService {

    List<User> get();

    User get(int id);

    void save(User user);

    void delete(int id); 
    
    boolean checkLogin(String username, String password);
    
    User findByUsername(String username);
}
