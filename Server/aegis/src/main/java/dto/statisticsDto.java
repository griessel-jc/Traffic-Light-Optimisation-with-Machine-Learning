 
package dto;
 
import java.util.List;

public class statisticsDto {
    private List<statisticDto> ai;
    private List<statisticDto> normal;
    
    public List<statisticDto> getAi(){
        return this.ai;
    }
    
    public void setAi(List<statisticDto> ai){
        this.ai = ai;
    }
    
    public List<statisticDto> getNormal(){
        return this.normal;
    }
    
    public void setNormal(List<statisticDto> normal){
        this.normal = normal;
    }
}
