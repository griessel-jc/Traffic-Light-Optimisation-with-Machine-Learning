package dto;
public class statisticDto {
    private String name;
    private float stationary_vehicles_X;
    private float stationary_vehicles_Y;
    private float moving_vehicles_X;
    private float moving_vehicles_Y;
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
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
