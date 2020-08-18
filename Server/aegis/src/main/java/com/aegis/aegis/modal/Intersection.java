 
package com.aegis.aegis.modal;
 
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="intersection")
public class Intersection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tl_id")
    private Integer tl_id;
    
    @Column(name = "name")
    private String name; 
    
    public Integer getId(){
        return tl_id;
    }
    
    public void setId(Integer id){
        this.tl_id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    
}
