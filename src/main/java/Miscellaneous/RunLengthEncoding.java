package Miscellaneous;

public class RunLengthEncoding {


    public String runLengthEncoding(String str) {

        if (str == null || str.length() == 0) return str;

        int count = 1;
        StringBuilder encodedString = new StringBuilder();

        for (int i = 1; i < str.length(); i++) {

            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;

            } else {

                encodedString.append(str.charAt(i - 1)).append(count);
                count=1;
            }
        }


        encodedString.append(str.charAt(str.length() - 1)).append(count);

        return encodedString.toString();
    }


    public static void main(String[] args) {


        String str = "wwwwaaadexxxxxxywww";
        RunLengthEncoding runLengthEncoding = new RunLengthEncoding();
        System.out.println(runLengthEncoding.runLengthEncoding(str));
    }
}
