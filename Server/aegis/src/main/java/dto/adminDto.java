/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author rudo5
 */
public class adminDto {

    private String username;
    private String password;
    private Integer id;
    private Integer role_Id;

    public void setRole_Id(Integer id){
        this.role_Id = id;
    }
    
    public Integer getRole_Id(){
        return role_Id;
    }
    
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
