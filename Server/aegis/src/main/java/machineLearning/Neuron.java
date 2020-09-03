package machineLearning;
 
public class Neuron {
    public double output, error;
    private final static int ACTIVATION = 0;
    public Neuron() {
        output = 0;
        error = 0;
    }
    
    public double F(double n){
        return (output =  1.0*n * (1/(1+Math.exp((-1)*n))));
    }
    
    public double F_Derivative(double n){
        return (1/2)*(1+F(n))*(1-F(n));
    }   
}
