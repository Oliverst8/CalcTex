public class DivExp extends BinaryExp {

    public DivExp(Exp left, Exp right) {
        super(left, right);
        if(right.eval() == 0) throw new IllegalArgumentException("Denominator cant be 0");

    }
    
    public void print() {
        //System.out.print("\\frac{");
        //System.out.print("DivExp(");
        System.out.print("(");
        left.print();
        //System.out.print("}{");
        System.out.print(" / ");
        right.print();
        //System.out.print("}");
        System.out.print(")");
    }

    public double eval() {
        return left.eval() / right.eval();
    }
}