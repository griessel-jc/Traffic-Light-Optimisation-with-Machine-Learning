package dto;
 
public class completeDto {
    private int numStationaryCars;
    private statisticDto[] statistics;
    
    public int getNumStationaryCars(){
        return this.numStationaryCars;
    }
    
    public void setNumStationaryCars(int cars){
        this.numStationaryCars = cars;
    }
    
    public statisticDto[] getStatistics(){
        return this.statistics;
    }
    
    public void setStatistics(statisticDto[] stats){
        this.statistics = stats;
    }   
}
