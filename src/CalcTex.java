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

    public static Exp combineExpressions(Exp left, Exp right, String operator){

        switch (operator){
            case "+":
                return new AddExp(left, right);
            case "-":
                return new SubExp(left, right);
            case "/":
                return new DivExp(left, right);
            case "*":
                return new MulExp(left, right);
            default:
                break;
        }
        throw new UnsupportedOperationException("Not implemented");
    }

    public static int splitingIndex(long occurences, String character, String equation){
        int amountFound = 0;
        for (int i = 0; i < equation.length();i++) {
            if(equation.charAt(i) == character.charAt(0)) amountFound++;
            if(occurences/2 == amountFound) return i;
        }
        throw new UnsupportedOperationException("No character index found");
    }

    public static String[] splitEquation(String equation){
        //throw new UnsupportedOperationException("Not implemented");
        String[] splitEquation = new String[3];
        if(occuringses(equation, "+") > 0){
            int splitI = splitingIndex(occuringses(equation, "+"), "+", equation);
            splitEquation[0] = equation.substring(0,splitI);
            splitEquation[1] = equation.substring(splitI+1);
            splitEquation[2] = "+";
        }

        return splitEquation;
    }

    public static Exp stringToExp(String equation){
        Pattern operators = Pattern.compile("(:?[\\+\\/\\*-])");
        Matcher matcher = operators.matcher(equation);
        if(matcher.results().count() == 1) return stringToSingleExp(equation);
        String[] splitEquation = splitEquation(equation);
        return combineExpressions(stringToExp(splitEquation[0]), stringToExp(splitEquation[1]), splitEquation[2]);
    }

    public static long occuringses(String str, String character){
        Pattern pattern = Pattern.compile(character);
        Matcher matcher = pattern.matcher(str);
        return matcher.results().count();
    }

    public static String mostOccuringOperator(String equation){
        Long plus = occuringses(equation, "+");
        //Long minus = occuringses(equation, "-");
        //Long division = occuringses(equation, "/");
        //Long times = occuringses(equation, "*");
        throw new UnsupportedOperationException("Not implemented");
    }

    //Takes a string with one operator, and returns a matching expression
    public static Exp stringToSingleExp(String input){
        if(input == null) throw new NullPointerException("Input cant be null");
        Exp output = null;
        if(input.contains("*")){
            String[] numberExpressions = input.split("(:?\\*)");
            output = new MulExp(stringToNumExp(numberExpressions[0]), stringToNumExp(numberExpressions[1]));
        } else if(input.contains("/")){
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

    public static String replaceAllLatex(String input){
        return input.replace("\\cdot", "*");
    }


    public static void main(String[] args) {
        Exp equation;
        String input = input().replace(" ", "");
        System.out.println(stringToExp(replaceAllLatex(input)).eval());
        //equation = stringToExp(input);
        //equation.print();
        //System.out.println(input);
        //System.out.println(stringToSingleExp(input).eval());
        //System.out.println(new addExp(new numExp(25), new numExp(25)).eval());
    }
}