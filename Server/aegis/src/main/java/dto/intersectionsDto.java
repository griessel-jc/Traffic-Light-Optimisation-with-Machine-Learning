 
package dto;
 
import java.util.List;

public class intersectionsDto {
    private List<statisticDto> statistics;
    
    public void setIntersections(List<statisticDto> stats){
        this.statistics = stats;
    }
    
    public List<statisticDto> getIntersections(){
        return this.statistics;
    }
}
