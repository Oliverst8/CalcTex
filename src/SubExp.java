public class SubExp extends BinaryExp {

    public SubExp(Exp left, Exp right) {
        super(left, right);

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