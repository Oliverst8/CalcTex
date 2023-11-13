public class SubExp extends Exp {
    protected Exp left;
    protected Exp right;
    
    public SubExp(Exp left, Exp right) {
        super();
        this.left = left;
        this.right = right;
    }

    public void print() {
        //System.out.print("SubExp(");
        System.out.print("(");
        left.print();
        System.out.print(" - ");
        right.print();
        System.out.print(")");
    }

    public double eval() {
        return left.eval() - right.eval();
    }
}