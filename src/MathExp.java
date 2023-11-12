public class MathExp extends Exp{

    private final Exp expression;

    public MathExp(Exp expression){
        if(expression instanceof MathExp) throw new IllegalArgumentException("Expression cant be a MathExp");
        this.expression = expression;
    }

    public void print(){
        expression.print();
    }

    public double eval(){
        return expression.eval();
    }

}
