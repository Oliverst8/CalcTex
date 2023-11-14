import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.*;


public class CalcTex {

    public static String input(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input.replace(" ", "");
    }

    public static Exp stringToNumExp(String input){
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
            case "^":
                return new PowExp(left, right);
            default:
                break;
        }
        throw new UnsupportedOperationException("Not implemented");
    }

    public static int splittingIndex(long occurences, String character, String equation){
        int amountFound = 0;
        int startBracket = 0;
        int endBracket = 0;
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < equation.length(); i++) {

                    switch (equation.charAt(i)) {
                        case '(':
                            startBracket++;
                            break;
                        case ')':
                            endBracket++;
                            break;
                        default:
                            if (equation.charAt(i) == character.charAt(0) ){
                                if((startBracket == endBracket || j == 1)){
                                    amountFound++;
                                } else{
                                    occurences--;
                                    if(occurences == 0){
                                        return -1;
                                    }
                                }

                            }
                            break;
                    }



                if (Math.ceil(occurences / 2.0) == amountFound || occurences == amountFound) return i;
            }
        }
        throw new UnsupportedOperationException("No character index found");
    }


    public static String[] splitEquation(String equation){
        //throw new UnsupportedOperationException("Not implemented");
        String[] splitEquation = new String[3];
        String[] operators = {"+","-","*","/","^"};
            for (String operator : operators) {
                if (occuringses(equation, operator) > 0) {
                    int splitI = splittingIndex(occuringses(equation, operator), operator, equation);
                    if(splitI == -1) continue;
                    splitEquation[0] = equation.substring(0, splitI);
                    splitEquation[1] = equation.substring(splitI + 1);
                    splitEquation[2] = operator;
                    return splitEquation;
                }
            }
        return splitEquation(equation.substring(1, equation.length()-1));
        //throw new NullPointerException("Split equation cant be null. Was null for: " + equation);
    }

    public static Exp stringToExp(String equation){
        if (equation == null) throw new NullPointerException("Equation cant be null");
        Pattern operators = Pattern.compile("(:?[\\+\\/\\*\\-\\^])");
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

    public static String removeParenthesis(String input){
        return input.replaceAll("[()]","");
    }

    //Takes a string with one operator, and returns a matching expression
    public static Exp stringToSingleExp(String input){
        if(input == null) throw new NullPointerException("Input cant be null");
        input = removeParenthesis(input);
        Exp output = null;
        if(input.contains("^")){
            String[] numberExpressions = input.split("(:?\\^)");
            output = new PowExp(stringToNumExp(numberExpressions[0]), stringToNumExp(numberExpressions[1]));
        } else if(input.contains("*")){
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
            output = stringToNumExp(input);
        }
        return output;
    }

    public static String latexFracToStringFrac(String latexFrac){
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        boolean isFirstEndBracketFound = false;
        int startBrackets = 0;
        int endBrackets = 0;
        boolean fractionStartBracketFound = false;
        for (int i = 5; i < latexFrac.length(); i++) {
            if(latexFrac.charAt(i) != '{' && latexFrac.charAt(i) != '}' && startBrackets != endBrackets) {
                builder.append(latexFrac.charAt(i));
            } else{
                switch (latexFrac.charAt(i)) {
                    case '{' -> startBrackets++;
                    case '}' -> endBrackets++;
                }
                if(startBrackets == endBrackets){
                    if(isFirstEndBracketFound) return builder.append(")").toString();
                    isFirstEndBracketFound = true;
                    builder.append(")/(");
                    fractionStartBracketFound = false;
                } else if (fractionStartBracketFound){
                    builder.append(latexFrac.charAt(i));
                } else{
                    fractionStartBracketFound = true;
                }
            }


        }

        throw new NoSuchElementException("No fraction in argument");
    }


    public static String replaceAllFractions(String equation){
        String output = equation;

        for(int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '\\') {
                if (equation.charAt(i + 1) == 'f') {
                    int end = 0;
                    boolean isFirstBracketFound = false;
                    int startBrackets = 0;
                    int endBrackets = 0;
                    for (int j = i; j < equation.length(); j++) {
                        switch (equation.charAt(j)) {
                            case '{' -> startBrackets++;
                            case '}' -> endBrackets++;
                        }
                        if(equation.charAt(j) == '}' && startBrackets == endBrackets) {
                            end = j;
                            if(isFirstBracketFound) break;
                            isFirstBracketFound = true;
                        }

                    }

                    output = equation.substring(0, i) + latexFracToStringFrac(equation.substring(i,end+1)) + equation.substring(end+1);
                }
            }
        }
        return output;
    }





    public static String replaceAllLatex(String input){
        String output = input.replace("\\cdot", "*");
        while(output.contains("\\frac")){
            output = replaceAllFractions(output);
        }
        output = replaceCurlyBrackets(output);
        return output;
    }

    private static String replaceCurlyBrackets(String input) {
        return input.replace("{","(").replace("}",")");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Please enter equation: ");
                String input = input();
                if (input.equals("quit")) break;
                System.out.println("Please enter amount of decimals");
                int decimals;
                if(scanner.hasNextInt()){
                    decimals = scanner.nextInt();
                } else{
                    throw new Exception("Amount of decimals must be a number");
                }
                System.out.println(calc(input, decimals));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }

    public static double calc(String input, int decimals){
        return round(stringToExp(replaceAllLatex(input.replace(" ", ""))).eval(), decimals);
    }

    public static double round(double number, int decimals){
        return (Math.round(number * (Math.pow(10, decimals)))/(Math.pow(10, decimals)));
    }
}