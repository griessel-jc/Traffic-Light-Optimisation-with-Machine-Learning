package com.aegis.aegis.service;
 
import dto.intersectionDto;
import dto.intersectionsDto;
import dto.statisticDto;
import java.util.List;

public interface IntersectionService {
    List<intersectionDto> getIntersections();
    
    intersectionsDto getIntersections2();
    
    void addIntersection(intersectionDto i);
    
    void addStatistic(statisticDto statistic);
    
    void addStatistic2(statisticDto statistic);
}
