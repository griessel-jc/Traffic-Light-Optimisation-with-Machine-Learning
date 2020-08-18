package machineLearning;
 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NeuralNetworkUtitlities {
    public static int    numNumbersData = 5;
    public static int    numIntersections;
    public static int    numInput;
    public static int    numOutput;
    public static int[]  hiddenLayers;
    public static double learningRate;
    public static double epsilon;

    public NeuralNetworkUtitlities(int numI, int[] hl, double lr, double e) {
        NeuralNetworkUtitlities.numIntersections   = 5;
        NeuralNetworkUtitlities.numInput           = numIntersections*numNumbersData;
        NeuralNetworkUtitlities.numOutput          = (int) Math.pow(2,NeuralNetworkUtitlities.numIntersections)-1;
        NeuralNetworkUtitlities.hiddenLayers       = hl;
        NeuralNetworkUtitlities.learningRate       = lr;
        NeuralNetworkUtitlities.epsilon            = e;
    }
    
    /**
     * Copy source neural network into destination neural network
     * @param dest - destination neural network
     * @param src - source neural network
     */
    public static void copy(NeuralNetwork dest, NeuralNetwork src){
        System.out.print("COPYING...");
        dest.setWeights(src.getWeights());
        dest.setBiases(src.getBiases());
        if(src.toString().equals(dest.toString())){
            System.out.print("SUCCESS\n");
        }else{
            System.out.print("FAIL\n");
        }
        saveModel(dest);
    }
    
    /**
     * saveModel() - Save the model
     * @param nn - the neural network to save
     */
    public static void saveModel(NeuralNetwork nn) {
        System.out.print("SAVING MODEL...");
        if (nn != null) {
            String filename = (nn.isTarget()) ? "target.txt" : "prediction.txt";
            try {
                File f = new File(filename);
                f.createNewFile();
                FileWriter writer = new FileWriter(filename);
                writer.write(nn.toString());
                writer.close();
                System.out.print("SUCCESS\n");
            } catch (IOException e) {
                System.out.print("FAIL\n");
            }
        }else{
            System.out.print("NULL!\n");
        }
    }

    /**
     * createTargetNeuralNetwork() 
     * @return the Target Neural Network 
     */
    public static NeuralNetwork createTargetNeuralNetwork(){
        System.out.print("CREATING TARGET NN...");
        NeuralNetwork target;
        if(targetExists()){
            target = restoreNeuralNetwork(true);
        }else{
            System.out.print("CREATING NEW\n");
            target = new NeuralNetwork(true,numInput, numOutput, hiddenLayers, learningRate, epsilon);
        }
        return target;
    }
    
    /**
     * createPredictionNeuralNetwork()
     * @return the Prediction Neural Network
     */
    public static NeuralNetwork createPredictionNeuralNetwork(){
        System.out.print("CREATING PREDICTION NN...");
        NeuralNetwork target;
        if(predictionExists()){
            target = restoreNeuralNetwork(false);
        }else{
            System.out.print("CREATING NEW\n");
            target = new NeuralNetwork(false,numInput, numOutput, hiddenLayers, learningRate, epsilon);
        }
        return target;  
    }
    
    /**
     * @param target
     * @return a NeuralNetwork restored from a file 
     */
    private static NeuralNetwork restoreNeuralNetwork(boolean target){
        System.out.print("RESTORING ");
        System.out.print((target? "TARGET" : "PREDICTION") + "\n");
        NeuralNetwork nn;
        try{
                File file           = new File((target ? "target.txt" : "prediction.txt"));
                BufferedReader br   = new BufferedReader(new FileReader(file));
                String line         = "";
                Scanner sc; 
                line                = br.readLine();
                int numInput        = Integer.parseInt(line.substring(line.indexOf(":")+1).trim());
                line                = br.readLine();
                int numOutput       = Integer.parseInt(line.substring(line.indexOf(":")+1).trim());
                line                = br.readLine();
                double learningRate = Double.parseDouble(line.substring(line.indexOf(":")+1).trim());
                line                = br.readLine();
                String [] lines     = line.substring(line.indexOf("[")+1, line.indexOf("]")).split(",");
                int [] hiddenLayers = new int[lines.length];
                for (int i = 0; i < lines.length; i++) {
                    hiddenLayers[i]     = Integer.parseInt(lines[i].trim());
                }
                nn                          = new NeuralNetwork(target,numInput, numOutput, hiddenLayers, learningRate, epsilon);
                ArrayList<Double>[] weights = new ArrayList[nn.getNumberLayers()-1];
                ArrayList<Double>[] biases  = new ArrayList[nn.getNumberLayers()-1];
                line                        = br.readLine();
                lines                       = line.substring(line.indexOf(":")+1).split("#");
                String numbers[];
                ArrayList<Double> weightArr;
                for (int i = 0; i < nn.getNumberLayers()-1; i++) {
                    numbers                     = lines[i].substring(lines[i].indexOf("[")+1, lines[i].indexOf("]")).split(",");
                    weightArr                   = new ArrayList<>();
                    for (String number : numbers) {
                        weightArr.add(Double.parseDouble(number));
                    }
                    weights[i] = weightArr;
                }
                line                        = br.readLine();
                lines                       = line.substring(line.indexOf(":")+1).split("#");
                ArrayList<Double> biasArr;
                for (int i = 0; i < nn.getNumberLayers()-1; i++) {
                    numbers                     = lines[i].substring(lines[i].indexOf("[")+1, lines[i].indexOf("]")).split(",");
                    biasArr                     = new ArrayList<>();
                    for (String number : numbers) {
                        biasArr.add(Double.parseDouble(number));
                    }
                    biases[i] = biasArr;
                }
                nn.setBiases(biases);
                nn.setWeights(weights);
                br.close();
            }catch(IOException | NumberFormatException e){
                deleteModel(target? 1: 0);
                nn = new NeuralNetwork(target,numInput, numOutput, hiddenLayers, learningRate, epsilon);
            }
        return nn;
    }
    
    /**
     * targetExists()
     * @return a boolean whether the target model exists or not 
     */
    private static boolean targetExists(){
        try{
            File f = new File("target.txt");
            return f.exists();
        }catch(Exception e){}
        return false;
    }
    
    
    /**
     * predictionExists()
     * @return a boolean whether the target model exists or not 
     */
    private static boolean predictionExists(){
        try{
            File f = new File("prediction.txt");
            return f.exists();
        }catch(Exception e){}
        return false;
    }
    
    /**
     * deleteModel() - Delete the model
     * @param option
     */
    public static void deleteModel(int option) {
        try {
            switch (option) {
                case 0:
                    {
                        File f = new File("target.txt");
                        if (f.exists()) {
                            f.delete();
                        }       break;
                    }
                case 1:
                    {
                        File f = new File("prediction.txt");
                        if (f.exists()) {
                            f.delete();
                        }       break;
                    }
                default:
                    {
                        File f = new File("target.txt");
                        if (f.exists()) {
                            f.delete();
                        }       f = new File("prediction.txt");
                        if (f.exists()) {
                            f.delete();
                        }       break;
                    }
            }
        } catch (Exception e) { }
    }
}

