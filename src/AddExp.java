public class AddExp extends BinaryExp{


    public AddExp(Exp left, Exp right){
        super(left, right);
    }

    public void print(){
        //System.out.print("AddExp(");
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
