package com.aegis.aegis.controller;
 
import com.aegis.aegis.modal.Intersection;
import com.aegis.aegis.service.IntersectionService;
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
    public List<Intersection> getIntersections(){
        return intersectionService.getIntersections();
    }
    
    @PostMapping("/addIntersection")
    public List<Intersection> addIntersection(@RequestBody intersectionDto i){
        intersectionService.addIntersection(i);
        return this.getIntersections();
    } 
    
    @PostMapping("/addStatistic")
    public List<Intersection> addStatistic(@RequestBody statisticDto statistic){
        intersectionService.addStatistic(statistic);
        return this.getIntersections();
    }
    
}
