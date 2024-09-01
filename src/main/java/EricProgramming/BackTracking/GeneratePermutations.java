package EricProgramming.BackTracking;

public class GeneratePermutations {


    public static void dfs(String str, int left, int right) {


        if (left == right) {
            System.out.println(str);
        }


        for (int i = left; i <= right; i++) {

            str = swap(str, left, i);
            dfs(str, left + 1, right);
            str = swap(str, left, i);
        }
    }


    public static String swap(String str, int i, int j) {

        char[] ch = str.toCharArray();

        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;

        return new String(ch);
    }


    public static void main(String[] args) {


        String str = "ABC";

        dfs(str, 0, str.length() - 1);

    }
}
