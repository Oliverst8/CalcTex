public class PowExp extends BinaryExp{
    public PowExp(Exp left, Exp right){
        super(left, right);
    }

    public void print() {
        System.out.print("(");
        left.print();
        System.out.print("^");
        right.print();
        System.out.print(")");
    }

    @Override
    public double eval() {
        return Math.pow(left.eval(), right.eval());
    }
}
