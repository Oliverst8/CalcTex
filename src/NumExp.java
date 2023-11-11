public class NumExp extends Exp{

    private int number;

    public NumExp(int number){
        super();
        this.number = number;
    }

    public void print(){
        System.out.println(number);
    }

    public int eval(){
        return number;
    }
}
