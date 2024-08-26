package CodingSimplified.String.String001;

public class ReplaceCharacter {


    public static void main(String[] args) {


        String str = "Narendra modi is prime minister of India";
        char[] ch = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) == 'i') {

                ch[i] = 'K';
            }
        }

        System.out.println(new String(ch));

    }
}
