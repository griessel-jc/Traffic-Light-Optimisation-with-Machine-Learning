package dto;
import java.util.List;

public class intersectionsDto {
    private List<intersectionDto> ai;
    private List<intersectionDto2> normal;
    
    public List<intersectionDto> getAi(){
        return this.ai;
    }
    
    public void setAi(List<intersectionDto> ai){
        this.ai = ai;
    }
    
    public List<intersectionDto2> getNormal(){
        return this.normal;
    }
    
    public void setNormal(List<intersectionDto2> normal){
        this.normal = normal;
    }
}
