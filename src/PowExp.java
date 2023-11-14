public class PowExp extends Exp{
    Exp number;
    Exp power;
    public PowExp(Exp number, Exp power){
        this.number = number;
        this.power = power;
    }

    public void print() {
        System.out.print("(");
        number.print();
        System.out.print("^");
        power.print();
        System.out.print(")");
    }

    @Override
    public double eval() {
        return Math.pow(number.eval(), power.eval());
    }
}
