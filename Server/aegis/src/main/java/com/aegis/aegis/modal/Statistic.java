
package com.aegis.aegis.modal;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name= "statistic")
public class Statistic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "tl_id")
    private Integer tl_id;
    
    @Column(name = "timestamp")
    private java.sql.Timestamp timestamp;
    
    @Column(name = "stationary_vehicles_X")
    private float stationary_vehicles_X;
    
    @Column(name = "stationary_vehicles_Y")
    private float stationary_vehicles_Y;
    
    @Column(name = "moving_vehicles_X")
    private float moving_vehicles_X;
    
    @Column(name = "moving_vehicles_Y")
    private float moving_vehicles_Y;
    
     
    public Integer getId(){
        return this.id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public Integer getIntersection_Id(){
        return this.tl_id;
    }
    
    public void setIntersection_Id(Integer tl_id){
        this.tl_id = tl_id;
    }
    
    public java.sql.Timestamp getTimestamp(){
        return this.timestamp;
    }
    
    public void setTimestamp(java.sql.Timestamp timestamp){
        this.timestamp = timestamp;
    }
    
    public float getStationaryX(){
        return this.stationary_vehicles_X;
    }
    
    public void setStationaryX(float stationary_vehicles_X){
        this.stationary_vehicles_X = stationary_vehicles_X;
    }
    
    
    public float getStationaryY(){
        return this.stationary_vehicles_Y;
    }
    
    public void setStationaryY(float stationary_vehicles_Y){
        this.stationary_vehicles_Y = stationary_vehicles_Y;
    }
    
    public float getMovingX(){
        return this.moving_vehicles_X;
    }
    
    public void setMovingX(float moving_vehicles_X){
        this.moving_vehicles_X = moving_vehicles_X;
    }
    
    
    public float getMovingY(){
        return this.moving_vehicles_Y;
    }
    
    public void setMovingY(float moving_vehicles_Y){
        this.moving_vehicles_Y = moving_vehicles_Y;
    }
    
    
}
