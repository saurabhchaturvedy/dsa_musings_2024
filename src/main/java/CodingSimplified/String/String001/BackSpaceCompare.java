package CodingSimplified.String.String001;

public class BackSpaceCompare {

    public static boolean backspaceCompare(String s, String t) {

        if (s == null || t == null) {
            return true;
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            System.out.println(i);
            if (s.charAt(i) != '#') {
                if (count == 0) {
                    sb.append(s.charAt(i));
                } else {

                    count--;
                }
            }

            if (s.charAt(i) == '#') {
                count++;
            }
        }

        String first = sb.toString();
        System.out.println(first);

        sb.setLength(0);

        count = 0;
        for (int i = t.length() - 1; i >= 0; i--) {
            if (t.charAt(i) != '#') {
                if (count == 0) {
                    sb.append(t.charAt(i));
                } else {

                    count--;
                }

            }

            if (t.charAt(i) == '#') {
                count++;
            }
        }

        String second = sb.toString();
        System.out.println(second);
        return first.equals(second);
    }


    public static void main(String[] args) {


        String s = "ab#c";
        String t = "ad#c";


        System.out.println(" Backspace compare ? " + backspaceCompare(s, t));

    }
}
