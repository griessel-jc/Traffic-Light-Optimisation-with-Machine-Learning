
package com.aegis.aegis.dao;

import com.aegis.aegis.modal.User;
import dto.adminDto;
import dto.loginDto;
import java.util.List;
 
public interface UserDAO {
    public List<User> get();
    public User get(int id);
    public void save(loginDto user);
    public void saveEncrypted(loginDto user);
    public void delete(int id);
    public void changeRole(adminDto admin);
    public User checkLogin(String username, String password);
    public boolean validate(loginDto user);
    public User findByUsername(String username);
}
