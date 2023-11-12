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
            if(Math.ceil(occurences/2.0) == amountFound || occurences == amountFound) return i;
        }
        throw new UnsupportedOperationException("No character index found");
    }

    public static String[] splitEquation(String equation){
        //throw new UnsupportedOperationException("Not implemented");
        String[] splitEquation = new String[3];
        String[] operators = {"+","-","*","/"};
        for(String operator : operators){
            if(occuringses(equation, operator) > 0){
                int splitI = splitingIndex(occuringses(equation, operator), operator, equation);
                splitEquation[0] = equation.substring(0,splitI);
                splitEquation[1] = equation.substring(splitI+1);
                splitEquation[2] = operator;
                return splitEquation;
            }
        }
        return splitEquation;
    }

    public static Exp stringToExp(String equation){
        if (equation == null) throw new NullPointerException("Equation cant be null");
        Pattern operators = Pattern.compile("(:?[\\+\\/\\*\\-])");
        Matcher matcher = operators.matcher(equation);
        long amountOfOperators = matcher.results().count();
        if(amountOfOperators <= 1) return stringToSingleExp(equation);
        String[] splitEquation = splitEquation(equation);
        return combineExpressions(stringToExp(splitEquation[0]), stringToExp(splitEquation[1]), splitEquation[2]);
    }

    public static long occuringses(String str, String character){
        Pattern pattern = Pattern.compile(Pattern.quote(character));
        Matcher matcher = pattern.matcher(str);
        return matcher.results().count();
    }

    //Takes a string with one operator, and returns a matching expression
    public static Exp stringToSingleExp(String input){
        if(input == null) throw new NullPointerException("Input cant be null");
        Exp output = null;
        if(input.contains("*")){
            String[] numberExpressions = input.split("(:?\\*)");
            output = new MulExp(stringToNumExp(numberExpressions[0]), stringToNumExp(numberExpressions[1]));
        } else if(input.contains("/")){
            String[] numberExpressions = input.split("(:?\\/)");
            output = new DivExp(stringToNumExp(numberExpressions[0]), stringToNumExp(numberExpressions[1]));
        } else if(input.contains("+")){
            String[] numberExpressions = input.split("(:?\\+)");
            output = new AddExp(stringToNumExp(numberExpressions[0]), stringToNumExp(numberExpressions[1]));
        } else if(input.contains("-")){
            String[] numberExpressions = input.split("-");
            output = new SubExp(stringToNumExp(numberExpressions[0]), stringToNumExp(numberExpressions[1]));
        } else{
            output = new NumExp(Integer.parseInt(input));
        }
        return output;
    }

    //Cant do fractions if they have more then on number in both spots
    public static String replaceFrac(String equation){
        String output = equation;
        for(int i = 0; i < equation.length(); i++){
            if(equation.charAt(i) == '\\'){
                if(equation.charAt(i+1) == 'f'){
                    output = equation.substring(0,i) + equation.charAt(i+6) + "/" + equation.charAt(i+9) + equation.substring(i+11);
                }
            }
        }
        return output;
    }

    public static String replaceAllLatex(String input){
        String output = input.replace("\\cdot", "*");
        if(output.contains("\\frac")){
            output = replaceFrac(output);
        }
        return output;
    }


    public static void main(String[] args) {
        Exp equation;
        String input = input().replace(" ", "");
        //System.out.println(stringToExp(replaceAllLatex(input)).eval());
        equation = stringToExp(replaceAllLatex(input));
        System.out.println(equation.eval());
        //System.out.println(stringToExp("1/2+2").eval());
        //System.out.println(replaceFrac(input));
        //equation = stringToExp(input);
        //equation.print();
        //System.out.println(input);
        //System.out.println(stringToSingleExp(input).eval());
        //System.out.println(new addExp(new numExp(25), new numExp(25)).eval());
    }
}