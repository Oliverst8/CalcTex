public class NumExp extends Exp{

    private final double number;

    public NumExp(double number){
        super();
        this.number = number;
    }

    public void print(){
        System.out.print(number);
    }

    public double eval(){
        return number;
    }
}
