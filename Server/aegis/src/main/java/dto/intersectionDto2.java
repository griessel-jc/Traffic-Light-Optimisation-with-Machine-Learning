 
package dto;
 
import com.aegis.aegis.modal.Statistic2;
import java.util.List;

public class intersectionDto2 {
        
    private Integer tl_id;
    
    private String name;
    
    private List<Statistic2> statistics;
    
    public List<Statistic2> getStatistics(){
        return this.statistics;
    }
    
    public void setStatistics(List<Statistic2> statistics){
        this.statistics = statistics;
    }
    
    public Integer getTl_Id(){
        return tl_id;
    }
    
    public void setTl_Id(Integer id){
        this.tl_id = id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
}
