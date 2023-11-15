public class FacExp extends Exp{

    Exp expression;
    public FacExp(Exp expression){
        this.expression = expression;
    }

    public void print(){
        expression.print();
        System.out.print("!");
    }

    public double eval(){
        double value = expression.eval();
        if(value == 0) return 1;
        return value * (new FacExp(new NumExp(value-1))).eval();
    }

}
