package machineLearning; 


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class NeuralNetwork {

    private final Random random;
    private double epsilon;
    /* Holds the levels(layers) of Neurons */
    private final ArrayList<Neuron>[] levels;
    /* Holds the weights of Neurons */
    private ArrayList<Double>[] weights;
    /* Holds the biases of Neurons */
    private ArrayList<Double>[] biases;
    private final double learningRate;
    private final int numInput;
    private final int numOutput;
    private final int numHiddenLayers;
    private final int numberLayers;
    private final int[] numNodesHiddenLayer;
    private int lastAction;
    private int currAction;
    private boolean target;

    /**
     * NeuralNetwork() - instantiates the neural network.
     *
     * @param target - the target neural network, otherwise prediction neural
     * network.
     * @param numInput - the number of inputs.
     * @param numOutput - the number of outputs, corresponding to the number of
     * actions.
     * @param numNodesHiddenLayer - an array holding the number of nodes in each
     * hidden layer.
     * @param learningRate - the learning rate.
     * @param epsilon - a threshold for whether the neural network should
     * explore or exploit.
     */
    public NeuralNetwork(boolean target, int numInput, int numOutput, int[] numNodesHiddenLayer, double learningRate, double epsilon) {
        /*
        ====================
        Initialize variables
        ====================
         */
        this.target = target;
        this.lastAction = 0;
        this.numHiddenLayers = numNodesHiddenLayer.length;
        this.numNodesHiddenLayer = numNodesHiddenLayer;
        this.numOutput = numOutput;
        this.numInput = numInput;
        this.learningRate = learningRate;
        this.random = new Random(42069);
        this.numberLayers = 1
                + //1 input layer 
                this.numHiddenLayers
                + //N hidden layers
                1;                        //1 output layer

        levels = (ArrayList<Neuron>[]) new ArrayList[numberLayers];
        weights = (ArrayList<Double>[]) new ArrayList[numberLayers - 1];
        biases = (ArrayList<Double>[]) new ArrayList[numberLayers - 1];

        /*
        =================
        Initialize levels
        =================
         */
        //Input
        int i = 0;
        levels[0] = new ArrayList<>();
        for (int input = 0; input < this.numInput; input++) {
            levels[i].add(new Neuron()); //create input nodes
        }

        ++i;
        //Hidden
        for (; i <= numHiddenLayers; i++) {
            levels[i] = new ArrayList<>();
            biases[i - 1] = new ArrayList<>();
            weights[i - 1] = new ArrayList<>();
            for (int j = 0; j < this.numNodesHiddenLayer[i - 1]; j++) {
                levels[i].add(new Neuron());//add the necessary number of nodes in each hidden layer
                biases[i - 1].add(Random());//add a bias for the current neuron
                for (int k = 0; k < levels[i - 1].size(); k++) {
                    //add a weight from every neuron in the previous level to the current neuron
                    weights[i - 1].add(Random());
                }
            }
        }

        //Output
        levels[i] = new ArrayList<>();
        for (int output = 0; output < this.numOutput; output++) {
            levels[numberLayers - 1].add(new Neuron()); //create output nodes
        }

        biases[i - 1] = new ArrayList<>();
        weights[i - 1] = new ArrayList<>();
        for (int j = 0; j < levels[i].size(); j++) {
            biases[i - 1].add(Random());
            //For each node in the current layer
            for (int k = 0; k < levels[i - 1].size(); k++) {
                //Add a weight from every node in the previous level to the current node
                weights[i - 1].add(Random());
            }
        }
    }

    
    /**
     * isTarget() - determine if the current neural network is the target neural network or not
     * @return a boolean whether or not this neural network is the target
     */
    public boolean isTarget() {
        return this.target;
    }
    
    /**
     * setBiases() - sets the biases of the neural network.
     * @param biases
     */
    public void setBiases(ArrayList<Double>[] biases) {
        this.biases = biases;
    }
    
    /**
     * getBiases() - gets the biases of the neural network
     * @return biases
     */
    public ArrayList<Double>[] getBiases(){
        ArrayList<Double>[] copy = new ArrayList[this.numHiddenLayers+1];
        for (int i = 0; i <= this.numHiddenLayers; i++) {
            copy[i] = new ArrayList<>();
            for (int j = 0; j < this.biases[i].size(); j++) {
                copy[i].add(biases[i].get(j)); 
            }
            copy[i].addAll(this.biases[i]);
        }
        return copy;
    }
    /**
     * setWeights() - sets the weights of the neural network.
     * @param weights
     */
    public void setWeights(ArrayList<Double>[] weights) {
        this.weights = weights;
    }
    
    /**
     * getWeights() - get the weights of the neural network.
     * @return the weights
     */
    public ArrayList<Double>[] getWeights(){
        ArrayList<Double>[] copy = new ArrayList[numHiddenLayers+1];
        for (int i = 0; i <= this.numHiddenLayers; i++) {
            copy[i] = new ArrayList<>();
            for (int j = 0; j < this.weights[i].size(); j++) {
                copy[i].add(weights[i].get(j)); 
            } 
        }
        return copy;
    }
     
    
    
    /**
     * getNumInput()
     *
     * @return the number of inputs
     */
    public int getNumInput() {
        return this.numInput;
    }

    /**
     * maxA() - determines the action with the greatest Q value
     *
     * @param input - an array of input for the neural network
     * @return an int corresponding to which action should be taken
     */
    public int maxA(double[] s) {
        //INPUT: feed in input
        this.lastAction = this.currAction;
        Q(s);
        if (this.Random() > epsilon) {
            /*Exploit*/
            double greatest = levels[numberLayers - 1].get(0).output;
            for (int j = 1; j < this.numOutput; j++) {
                if (levels[numberLayers - 1].get(j).output > greatest) {
                    currAction = j;
                    greatest = levels[numberLayers - 1].get(j).output;
                }
            }
        } else {
            /*Explore*/
            currAction = this.random.nextInt(numOutput);
        }
        return lastAction;
    }

    public double Q(double[] s, int a) {
        Q(s);
        return levels[numberLayers - 1].get(a).output;
    }

    
    /**
     * maxQ() - calculate the maxQ
     * @param s - state
     * @return a double representing the maximum Q value for a state
     */
    public double maxQ(double[] s) {
        Q(s);
        double greatestQ = levels[numberLayers - 1].get(0).output;
        for (int j = 1; j < this.numOutput; j++) {
            if (levels[numberLayers - 1].get(j).output > greatestQ) {
                greatestQ = levels[numberLayers - 1].get(j).output;
            }
        }
        return greatestQ;
    }

    /**
     * Q()- A function that will feed input into the neural network, estimating
     * the Q values.
     *
     * @param S - the action
     */
    private void Q(double[] s) {
        //INPUT     : feed in input
        int i = 0;
        for (int in = 0; in < numInput; in++) {
            levels[0].get(in).output = s[in];
        }
        ++i;

        //HIDDEN    : for each node in the hidden layer
        for (; i <= this.numHiddenLayers; i++) {
            for (int j = 0; j < this.numNodesHiddenLayer[i - 1]; j++) {
                //For each node in the current level:
                //Invoke the activation function corresponding to each node in the current layer
                levels[i].get(j).F(CalculateN(i, j));
            }
        }

        //OUTPUT    : 
        for (int j = 0; j < levels[i].size(); j++) {
            //For each node in the output level
            levels[i].get(j).F(CalculateN(i, j));
        }

    }

    /**
     * Backpropagate() - adjusts the weights and biases of the neural network to
     * reflect the reward.
     *
     * @param output - the output node that corresponds to the chosen action
     * @param loss - the amount to promote demote the action by
     */
    public void Backpropagate(int output, double loss) {
        System.out.println("BACKPROPAGATING:");
        System.out.println("-----------------");
        System.out.println("output  : "+output);
        System.out.println("loss    : "+loss);
        System.out.println("-----------------");
        double target = levels[numberLayers - 1].get(output).output + learningRate*(loss);
        /*
        ======
        OUTPUT
        ======
         */
        //1.) Error Infomation Term
        levels[numberLayers - 1].get(output).error
                = (target - levels[numberLayers - 1].get(output).output)
                * levels[numberLayers - 1].get(output).F_Derivative(CalculateN(numberLayers - 1, output));

        //2.) Weight Error Term
        for (int i = 0; i < numNodesHiddenLayer[numHiddenLayers - 1]; i++) {
            /*For each node in the last hidden layer, adjust its weights to the current output node*/
            int pos = (i * numOutput) + output;
            weights[numHiddenLayers]
                    .set(pos, weights[numHiddenLayers]
                            .get(pos)
                            + (learningRate
                            * levels[numberLayers - 1].get(output).error
                            * levels[numberLayers - 1].get(output).output)
                    );

        }
        //3.) Bias Error Term 
        biases[numHiddenLayers]
                .set(output,
                        learningRate * levels[numberLayers - 1].get(output).error);

        /*
        ======
        HIDDEN
        ======
         */
        //For each hidden layer
        for (int h = numberLayers - 2; h >= 1; h--) {
            int j = levels[h].size();
            //For each hidden node
            for (int i = 0; i < j; i++) {
                //1.) Error Information Term
                levels[h].get(i).error
                        = CaculateSumDeltaInputs(h, i)
                        * levels[h].get(i).F_Derivative(CalculateN(h, i));
                int n = levels[h - 1].size();
                for (int l = 0; l < n; l++) {
                    //For Each Weight from the previous layer(h-1) to the current node i in layer (h)
                    //2.) Weight Error Term
                    int pos = (l * j) + i;
                    weights[h - 1].set(pos, weights[h - 1].get(pos)
                            + (learningRate
                            * levels[h].get(i).error
                            * levels[h].get(i).output));
                }
                //3.) Bias Error Term
                biases[h - 1].set(i, learningRate
                        * levels[h].get(i).error);
            }

        }
    }

    /**
     * Calculates the sum of weights and bias of a Neuron.
     *
     * @param level - the level that the neuron of concern is in.
     * @param neuron - the neuron of concern
     * @return
     */
    private double CalculateN(int level, int neuron) {
        double N = 0;
        if (level == 0) {
            //INPUT 
            N = levels[level].get(neuron).output;
        } else {
            //HIDDEN OR OUTPUT
            int prevLevelSize, currLevelSize;
            if (level == numberLayers - 1) {
                // the last level? 
                prevLevelSize = numNodesHiddenLayer[numHiddenLayers - 1];
                currLevelSize = numOutput;
            } else if (level == 1) {
                prevLevelSize = numInput;
                currLevelSize = numNodesHiddenLayer[level - 1];
            } else {
                // a hidden level ?
                prevLevelSize = numNodesHiddenLayer[level - 1 - 1];
                currLevelSize = numNodesHiddenLayer[level - 1];
            }
            for (int i = 0; i < prevLevelSize; i++) {
                /*For each node in the previous level*/
                N = N
                        + (weights[level - 1].get(i * currLevelSize + neuron)
                        * levels[level - 1].get(i).output);
            }
            N = N + (biases[level - 1].get(neuron));
        }
        return N;
    }

    /**
     * Calculates the sum of delta inputs.
     *
     * @param level - the level that the neuron of concern is in.
     * @param neuron - the neuron of concern
     * @return a double
     */
    private double CaculateSumDeltaInputs(int level, int neuron) {
        //sum of the products of the next layer's error with the weights from them to the current node 
        double sumDeltaInputs = 0;
        //int m = levels[level + 1].size();
        int m;
        if (level == this.numHiddenLayers) {
            m = this.numOutput;
        } else {
            m = numNodesHiddenLayer[level];
        }

        for (int k = 0; k < m; k++) {
            //Add the product of its's error and the weight from the current neuron(neuron - in the current level) to that neuron(k - in the next level)
            sumDeltaInputs
                    += levels[level + 1].get(k).error //the error for each node in the next level
                    * weights[level].get((neuron * m) + k); //multiplied by the weight between it and the current node
        }
        return sumDeltaInputs;
    }

    private double Random() {
        return this.random.nextDouble() * ((1)) - 0.5;
    }

    /**
     * Print() - Print the Neural Network, including weights and biases.
     */
    public void Print() {
        System.out.println("\n\n\n");
        System.out.println((target? "TARGET" : "PREDICTION"));
        //INPUT 
        int i = 0;
        System.out.print("LEVEL " + (i) + " - INPUT(" + (numInput) + ")[ ");
        for (int j = 0; j < numInput; j++) {
            System.out.print(levels[0].get(j).output + " ");
        }
        System.out.print("]\n");

        //HIDDEN
        ++i;
        for (; i <= numHiddenLayers; i++) {
            int numNodesPrevLevel = (i == 1) ? numInput : numNodesHiddenLayer[i - 2];
            int numNodesCurrLevel = numNodesHiddenLayer[i - 1];
            System.out.println("W" + (i - 1));
            for (int j = 0; j < numNodesPrevLevel; j++) {
                System.out.print((j) + "{ ");
                for (int k = 0; k < numNodesCurrLevel; k++) {
                    System.out.print(weights[i - 1].get((j * numNodesCurrLevel) + k) + " ");
                }
                System.out.print("}");
            }
            System.out.println("]");
            System.out.println("B" + (i - 1));
            System.out.print("[");
            for (int j = 0; j < numNodesCurrLevel; j++) {
                System.out.print(biases[i - 1].get(j) + " ");
            }
            System.out.print("]\n");

            System.out.print("LEVEL " + (i) + " - HIDDEN(" + (numNodesCurrLevel) + ")[ ");
            for (int j = 0; j < numNodesCurrLevel; j++) {
                System.out.print(levels[i].get(j).output + " ");
            }
            System.out.print("]\n");
        }

        //OUTPUT
        System.out.println("W" + (i + 1) + " : ");
        for (int j = 0; j < numNodesHiddenLayer[numHiddenLayers - 1]; j++) {
            //for each node in the last output layer
            System.out.print((j) + "{");
            for (int k = 0; k < numOutput; k++) {
                //for each weight from the hidden node to each output node
                System.out.print(weights[i - 1].get((j * numOutput) + k) + " ");
            }
            System.out.print("} ");
        }
        System.out.println("");
        System.out.println("B" + (i - 1) + " : ");
        for (int j = 0; j < numOutput; j++) {
            System.out.print(biases[i - 1].get(j) + "   ");
        }
        System.out.println("");
        System.out.print("LEVEL " + (i) + " - OUTPUT(" + (numOutput) + ")[");
        for (int j = 0; j < numOutput; j++) {
            System.out.print(levels[i].get(j).output + "    ");
        }
        System.out.print("]\n");
        System.out.println("\n\n\n");
    }

    /**
     * getNumberLayers()
     *
     * @return the number of layers
     */
    public int getNumberLayers() {
        return this.numberLayers;
    }

    /**
     * toString()
     *
     * @return produces a string of the state of the neural network.
     */
    public String toString() {
        String output = "";
        output += "number input :   " + numInput + "\n";
        output += "number output:   " + numOutput + "\n";
        output += "learning rate:   " + learningRate + "\n";
        output += "hidden layers:   " + Arrays.toString(this.numNodesHiddenLayer) + "\n";
        output += "weight matrix:   ";
        for (int i = 0; i <= numHiddenLayers; i++) {
            output += weights[i].toString();
            if (i < numHiddenLayers) {
                output += "#";
            }
        }
        output += "\n";
        output += "bias matrix:   ";
        for (int i = 0; i <= numHiddenLayers; i++) {
            output += biases[i].toString();
            if (i < numHiddenLayers) {
                output += "#";
            }
        }

        output += "\n";
        return output;
    }

}
