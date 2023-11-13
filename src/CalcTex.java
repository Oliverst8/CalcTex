import java.util.NoSuchElementException;
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
        String[] operators = {"+","-","*","/"};
            for (String operator : operators) {
                if (occuringses(equation, operator) > 0) {
                    int splitI = splitingIndex(occuringses(equation, operator), operator, equation);
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

    public static String removeParenthesis(String input){
        return input.replaceAll("[()]","");
    }

    //Takes a string with one operator, and returns a matching expression
    public static Exp stringToSingleExp(String input){
        if(input == null) throw new NullPointerException("Input cant be null");
        input = removeParenthesis(input);
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

    public static String latexFracToStringFrac(String latexFrac){
        StringBuilder builder = new StringBuilder();
        boolean isFirstEndBracketFound = false;
        for(int i = 0; i < latexFrac.length(); i++){
            if(Character.isDigit(latexFrac.charAt(i))) builder.append(latexFrac.charAt(i));
            if(latexFrac.charAt(i) == '}'){
                if(isFirstEndBracketFound) return builder.toString();
                isFirstEndBracketFound = true;
                builder.append("/");
            }
        }
        throw new NoSuchElementException("No fraction in argument");
    }

    //Cant do fractions within fractions
    public static String replaceAllFractions(String equation){
        String output = equation;

        for(int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '\\') {
                if (equation.charAt(i + 1) == 'f') {
                    int end = 0;
                    boolean isFirstBracketFound = false;
                    for (int j = i; j < equation.length(); j++) {
                        if(equation.charAt(j) == '}') {
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

    //Cant do fractions if they have more then on number in both spots
    public static String replaceFractions(String equation){
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
            output = replaceAllFractions(output);
        }
        return output;
    }


    public static void main(String[] args) {
        /*Exp equation;
        String input = input().replace(" ", "");
        equation = stringToExp(replaceAllLatex(input));
        System.out.println(equation.eval()); */

        //String input = "(2+2)/(4)+3/4*2+2";
        //System.out.println(occuringses(input, "("));
        //System.out.print(stringToExp(replaceAllLatex(input.replace(" ", ""))).eval());
    }
}