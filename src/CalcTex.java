import java.util.Scanner;
import java.util.regex.*;


public class CalcTex {

    public static String input(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }

    public static NumExp stringToNumExp(String input){
        return new NumExp(Integer.parseInt(input));
    }

    public static Exp stringToExp(String equation, Exp expression){
        Pattern operators = Pattern.compile("(:?[\\+\\/\\*-])");
        Matcher matcher = operators.matcher(equation);
        if(matcher.results().count() == 1) return stringToSingleExp(equation);
        throw new UnsupportedOperationException("Not implemented");
    }

    //Takes a string with one operator, and returns an matching expression
    public static Exp stringToSingleExp(String input){
        if(input == null) throw new NullPointerException("Input cant be null");
        Exp output = null;
        if(input.contains("*") || input.contains("\\cdot")){
            String[] numberExpressions = input.replace("\\cdot", "*").split("(:?\\*)");
            output = new MulExp(stringToNumExp(numberExpressions[0]), stringToNumExp(numberExpressions[1]));
        } else if(input.contains("/") || input.contains("\\frac")){
            throw new UnsupportedOperationException("Converting divison to exp is not implemented yet");
        } else if(input.contains("+")){
            String[] numberExpressions = input.split("(:?\\+)");
            output = new AddExp(stringToNumExp(numberExpressions[0]), stringToNumExp(numberExpressions[1]));
        } else if(input.contains("-")){
            String[] numberExpressions = input.split("-");
            output = new SubExp(stringToNumExp(numberExpressions[0]), stringToNumExp(numberExpressions[1]));
        }
        return output;
    }

    public static void main(String[] args) {
        Exp equation;
        String input = input().replace(" ", "");
        //equation = stringToExp(input);
        //equation.print();
        //System.out.println(input);
        //System.out.println(stringToSingleExp(input).eval());
        //System.out.println(new addExp(new numExp(25), new numExp(25)).eval());
    }
}