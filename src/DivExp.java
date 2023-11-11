public class DivExp extends Exp {
    protected Exp left;
    protected Exp right;

    public DivExp(Exp left, Exp right) {
        super();
        if(right.eval() == 0) throw new IllegalArgumentException("Denominator cant be 0");
        this.left = left;
        this.right = right;
    }
    
    public void print() {
        System.out.print("\\frac{");
        left.print();
        System.out.print("}{");
        right.print();
        System.out.print("}");
    }

    public int eval() {
        return left.eval() / right.eval();
    }
}