public class MulExp extends BinaryExp {

    
    public MulExp(Exp left, Exp right) {
        super(left,right);

    }
    
    public void print() {
        //System.out.print("MulExp(");
        System.out.print("(");
        left.print(); // recursion!
        //System.out.print(" \\cdot ");
        System.out.print(" * ");
        right.print(); // recursion!
        System.out.print(")");
    }

    public double eval() {
        return left.eval() * right.eval();
    }
}