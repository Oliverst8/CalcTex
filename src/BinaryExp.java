public abstract class BinaryExp extends Exp{

    protected final Exp left;
    protected final Exp right;


    protected BinaryExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }
}
