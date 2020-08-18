package com.aegis.aegis.controller;
 
import com.aegis.aegis.modal.Intersection;
import com.aegis.aegis.service.IntersectionService;
import com.google.gson.Gson;
import dto.intersectionDto;
import dto.statisticDto;
import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/simu")
public class SimulationController { 
    @Autowired
    private IntersectionService intersectionService;
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getIntersections")
    public List<intersectionDto> getIntersections(){
        return intersectionService.getIntersections();
    } 
    
    @PostMapping("/addStatistic")
    public List<intersectionDto> addStatistic(@RequestBody statisticDto statistic){
        intersectionService.addStatistic(statistic);
        return this.getIntersections();
    }
    
    public void addStat(statisticDto statistic){
        intersectionService.addStatistic(statistic);
    }
    
    @CrossOrigin(origins = "http://localhost:7777")
    @PostMapping("/addStatistics")
    public int addstatistics(@RequestBody statisticDto[] stats){
        int numIntersections = stats.length;
        for (int i = 0; i < numIntersections; i++) {
            addStat(stats[i]);
        }
        int max = (int)Math.pow(2, numIntersections+1)-1;
        int min = 0;
        int range = max - min +1;
        return  (int)(Math.random() * range) + min ;
    }
}
