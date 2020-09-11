 
package com.aegis.aegis.dao;
 
import com.aegis.aegis.modal.Intersection; 
import dto.intersectionDto;
import dto.intersectionsDto;
import dto.statisticDto;
import java.util.List;

public interface IntersectionDAO {
    public  List<intersectionDto> get();
    
    public  intersectionsDto get2();
    
    public void addIntersection(intersectionDto intersection);
    
    public void addStatistic(statisticDto statistic);
    
    public void addStatistic2(statisticDto statistic);
    
    public Intersection findbyName(String name); 
    
    public void addGeneration();
}
