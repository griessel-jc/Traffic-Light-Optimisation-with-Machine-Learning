package machineLearning;

public class Transition {
    private static long id_incrementer = Long.MIN_VALUE;
    public int action;
    public double[] state_before;
    public double[] state_after;
    public double reward;
    public long id;
    public double difference;
    
    public Transition(double[] s_before,int a, double r, double[] s_after, double difference){
        this.id             = ++id_incrementer;
        this.action         = a;
        this.state_before   = s_before.clone();
        this.state_after    = s_after.clone();
        this.reward         = r;
        this.difference     = difference;
    }
}
