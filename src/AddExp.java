public class AddExp extends Exp{
    private final Exp left;
    private final Exp right;

    public AddExp(Exp left, Exp right){
        super();
        this.left = left;
        this.right = right;
    }

    public void print(){
        System.out.print("(");
        left.print();
        System.out.print(" + ");
        right.print();
        System.out.print(")");
    }

    public double eval(){
        return left.eval() + right.eval();
    }

}
