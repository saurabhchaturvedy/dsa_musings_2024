package BackTracking;

public class GenerateBinaryStrings {


    public static void generateStrings(char[] ch, int n, int index) {


        if (index == n) {
            System.out.println(new String(ch));
            return;
        }


        ch[index] = '0';
        generateStrings(ch, n, index + 1);

        ch[index] = '1';
        generateStrings(ch, n, index + 1);
    }


    public static void main(String[] args) {


        int n = 3;
        char[] ch = new char[n];


        generateStrings(ch, n, 0);

    }
}
