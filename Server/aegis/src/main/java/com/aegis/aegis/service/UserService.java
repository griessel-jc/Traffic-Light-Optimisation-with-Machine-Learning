 
package com.aegis.aegis.service;

import com.aegis.aegis.modal.User; 
import dto.loginDto;
import java.util.List;

public interface UserService {

    List<User> get();

    User get(int id);

    void save(loginDto user);

    void saveEncrypted(loginDto user);
    
    void delete(int id); 
    
    User checkLogin(String username, String password);
    
    User findByUsername(String username);
}
