package machineLearning; 

import java.util.ArrayList;

public class ReinforcementLearning {
    public NeuralNetwork target, prediction;
    private NeuralNetworkUtitlities utility;
    private int lastAction;
    private int currentAction; 
    private double[] lastState;
    private double[] currState;
    private int totalIterations;                             //how many iterations before 
    private int currIteration;
    private double gamma;
    private double discount;
    
    
    /**
     * ReinforcementLearning() - Instantiates a Reinforcement Learning Object
     * @param gamma
     * @param iterations - number of iterations before updating the target network with the prediction network.
     * @param numInt - number of intersections.
     * @param hl - the hidden layer configuration.
     * @param lr - the learning rate.
     * @param e - epsilon.
     */
    public ReinforcementLearning(double gamma,int iterations,int numInt, int[] hl, double lr, double e) {
        this.discount               = gamma;
        this.lastAction             = -1;
        this.utility                = new  NeuralNetworkUtitlities(numInt,hl, lr,e);
        this.target                 = NeuralNetworkUtitlities.createTargetNeuralNetwork();
        this.prediction             = NeuralNetworkUtitlities.createPredictionNeuralNetwork();
        this.currIteration          = 0;
        this.totalIterations        = iterations;
        NeuralNetworkUtitlities.saveModel(target);
        NeuralNetworkUtitlities.saveModel(prediction);
    } 
    
    /**
     * getAction() - get the action to take given the state
     * @param state
     * @return 
     */
    public int getAction(double[] state){
        System.out.println("GETTING ACTION");
        this.lastAction             = this.currentAction;
        this.lastState              = this.currState;
        this.currState              = state; 
        this.currentAction          = prediction.maxA(state);
        if(lastState != null) this.prediction.Backpropagate(lastAction,Loss());
        ++currIteration;
        if(currIteration >= totalIterations){
            currIteration = 0;
            NeuralNetworkUtitlities.copy(target,prediction);
            NeuralNetworkUtitlities.saveModel(target);
        }
        NeuralNetworkUtitlities.saveModel(prediction);
        return this.currentAction;
    }
   
    /**
     * Reward() - calculate the reward for the action
     * @param s_before
     * @param a
     * @param s_after
     * @return a double holding the reward
     */
    private double Reward(double[] s_before, int a, double[] s_after){
        double r                    = 0;//the reward
        double incNumMoving         = 0;
        double incNumStationary     = 0;
        
        for (int i = 0; i < NeuralNetworkUtitlities.numIntersections; i++) {
            incNumStationary            += (currState[(i*NeuralNetworkUtitlities.numNumbersData)+0]-lastState[(i*NeuralNetworkUtitlities.numNumbersData)+0]) + (currState[(i*NeuralNetworkUtitlities.numNumbersData)+1]-lastState[(i*NeuralNetworkUtitlities.numNumbersData)+1]);
            incNumMoving                += (currState[(i*NeuralNetworkUtitlities.numNumbersData)+2]-lastState[(i*NeuralNetworkUtitlities.numNumbersData)+2]) + (currState[(i*NeuralNetworkUtitlities.numNumbersData)+3]-lastState[(i*NeuralNetworkUtitlities.numNumbersData)+3]); 
        }
        r                           = incNumMoving - (incNumStationary);
        return r;
    }
    
    /**
     * Loss() - Calculate the loss.
     * @return the loss
     */
    private double Loss(){ 
        double temporalDifference   = Reward(lastState, lastAction, lastState)+ (discount*this.target.maxQ(currState));
        double TDerror              = temporalDifference - this.prediction.Q(this.lastState,this.lastAction);
        double behaviourDistribution = 0;
        /*return behaviourDistribution*Math.pow(TDerror,2);*/
        return Math.pow(TDerror,2);
    }
    
    /**
     * print() - prints the neural network in it's current state.
     */
    public void print(){
        if(target != null){
            System.out.println("TARGET");
            System.out.println(target.toString());
        }
        if(prediction != null){
            System.out.println("PREDICTION");
            System.out.println(prediction.toString());
        }
    }
    
    
}
