package machineLearning;

public class Transition {
    public int action;
    public double[] state_before;
    public double[] state_after;
    public double reward;
    
    public Transition(double[] s_before,int a, double r, double[] s_after){
        this.action         = a;
        this.state_before   = s_before.clone();
        this.state_after    = s_after.clone();
        this.reward         = r;
    }
}
