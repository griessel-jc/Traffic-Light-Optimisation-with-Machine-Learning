 
package com.aegis.aegis.dao;
 
import com.aegis.aegis.modal.Intersection; 
import dto.intersectionDto;
import dto.statisticDto;
import java.util.List;

public interface IntersectionDAO {
    public  List<intersectionDto> get();
    
    public void addIntersection(intersectionDto intersection);
    
    public void addStatistic(statisticDto statistic);
    
    public Intersection findbyName(String name);
    
}
