package CodingSimplified.String.String001;

public class PrintFirstCharWord {


    public static void main(String[] args) {


        String str = "    Hello   World Everyone!!!";

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) != ' ' && (i == 0 || str.charAt(i - 1) == ' ')) {

                System.out.println(str.charAt(i));

            }
        }
    }
}
