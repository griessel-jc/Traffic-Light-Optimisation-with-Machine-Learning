package com.aegis.aegis.service;
 
import com.aegis.aegis.modal.Intersection;
import com.aegis.aegis.modal.Statistic;
import dto.intersectionDto;
import dto.statisticDto;
import java.util.List;

public interface IntersectionService {
    List<intersectionDto> getIntersections();
    
    void addIntersection(intersectionDto i);
    
    void addStatistic(statisticDto statistic);
}
