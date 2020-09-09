package com.aegis.aegis.controller;
 
import com.aegis.aegis.service.IntersectionService;
import dto.intersectionDto;
import dto.intersectionsDto;
import dto.statisticDto;
import java.util.List; 
import machineLearning.ReinforcementLearning;
import machineLearning.NeuralNetworkUtitlities;
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
    private ReinforcementLearning rl = new ReinforcementLearning(new int[]{300,300});
    
    @Autowired
    private IntersectionService intersectionService;
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getIntersections")
    public List<intersectionDto> getIntersections(){
        return intersectionService.getIntersections();
    } 
    
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getIntersections2")
    public intersectionsDto getIntersections2(){
        return intersectionService.getIntersections2();
    } 
    
    
    @PostMapping("/addStatistic")
    public List<intersectionDto> addStatistic(@RequestBody statisticDto statistic){
        intersectionService.addStatistic(statistic);
        return this.getIntersections();
    }
    
    public void addStat(statisticDto statistic){
        intersectionService.addStatistic(statistic);
    }
    
    public void addStat2(statisticDto statistic){
        intersectionService.addStatistic2(statistic);
    }
    
    @CrossOrigin(origins = "http://localhost:7777")
    @PostMapping("/addStatistics")
    public int addstatistics(@RequestBody statisticDto[] stats){
        double [] state = new double[NeuralNetworkUtitlities.numIntersections*NeuralNetworkUtitlities.numNumbersData];
        for (int i = 0; i < NeuralNetworkUtitlities.numIntersections; i++) {
            addStat(stats[i]);
            addStat2(stats[i]);
            state[(i*NeuralNetworkUtitlities.numNumbersData)+0] = stats[i].getStationaryX();
            state[(i*NeuralNetworkUtitlities.numNumbersData)+1] = stats[i].getStationaryY();
            state[(i*NeuralNetworkUtitlities.numNumbersData)+2] = stats[i].getMovingX();
            state[(i*NeuralNetworkUtitlities.numNumbersData)+3] = stats[i].getMovingY();
            state[(i*NeuralNetworkUtitlities.numNumbersData)+4] = stats[i].getPhase();
        } 
        return rl.getAction(state);
    }
    @CrossOrigin(origins = "http://localhost:7777")
    @PostMapping("/addStatistics2")
    public void addstatistics2(@RequestBody statisticDto[] stats){
        for (int i = 0; i < NeuralNetworkUtitlities.numIntersections; i++) {
            addStat2(stats[i]);
        } 
    }
    @PostMapping("/resetModel")
    public void resetModel(){
        NeuralNetworkUtitlities.deleteModel();
        rl = new ReinforcementLearning(new int[]{300,300});
    }
    
    @PostMapping("/print")
    public void print(){
        rl.prediction.Print();
    }
    
}
