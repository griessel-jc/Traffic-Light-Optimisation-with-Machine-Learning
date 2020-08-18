package com.aegis.aegis.controller;
 
import com.aegis.aegis.modal.Intersection;
import com.aegis.aegis.service.IntersectionService;
import com.google.gson.Gson;
import dto.intersectionDto;
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
    private final ReinforcementLearning rl = new ReinforcementLearning(
            0.8,                //Discount factor
            1000,               //Number iterations before copying prediction model to target model
            6,                  //Numbr of intersection
            new int[]{100,100}, //Number hidden layers
            0.04,               //Learning rate
            0.05                //Epsilon - for epsilon-greedy algorithm
    );
    
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
        double [] state = new double[NeuralNetworkUtitlities.numIntersections*NeuralNetworkUtitlities.numNumbersData];
        for (int i = 0; i < NeuralNetworkUtitlities.numIntersections; i++) {
            addStat(stats[i]);
            state[(i*NeuralNetworkUtitlities.numNumbersData)+0] = stats[i].getStationaryX();
            state[(i*NeuralNetworkUtitlities.numNumbersData)+1] = stats[i].getStationaryY();
            state[(i*NeuralNetworkUtitlities.numNumbersData)+2] = stats[i].getMovingX();
            state[(i*NeuralNetworkUtitlities.numNumbersData)+3] = stats[i].getMovingY();
            state[(i*NeuralNetworkUtitlities.numNumbersData)+3] = stats[i].getPhase();
        } 
        return rl.getAction(state);
    }
}
