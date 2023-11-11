public class MulExp extends Exp {
    protected Exp left;
    protected Exp right;
    
    public MulExp(Exp left, Exp right) {
        super();
        this.left = left;
        this.right = right;
    }
    
    public void print() {
        System.out.print("(");
        left.print(); // recursion!
        System.out.print(" \\cdot ");
        right.print(); // recursion!
        System.out.print(")");
    }

    public int eval() {
        return left.eval() * right.eval();
    }
}