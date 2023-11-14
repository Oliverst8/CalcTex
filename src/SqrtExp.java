public class SqrtExp extends Exp{
    Exp expression;

    public SqrtExp(Exp expression){
        this.expression = expression;
    }

    public void print(){
        System.out.print("\\sqrt(");
        expression.print();
        System.out.print(")");
    }

    public double eval(){
        return Math.sqrt(expression.eval());
    }

}
