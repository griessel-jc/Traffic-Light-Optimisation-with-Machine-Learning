package dto;
 
import com.aegis.aegis.modal.Statistic;
import java.util.List;

public class intersectionDto {
    
    private Integer tl_id;
    
    private String name;
    
    private List<Statistic> statistics;
    
    public List<Statistic> getStatistics(){
        return this.statistics;
    }
    
    public void setStatistics(List<Statistic> statistics){
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
