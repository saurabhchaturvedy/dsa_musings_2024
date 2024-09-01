package EricProgramming.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParanthesis {


    public List<String> response = new ArrayList<>();


    public List<String> generateParanthesis(int n) {

        dfs(new StringBuilder(), 0, n);
        return response;
    }


    public void dfs(StringBuilder sb, int close, int n) {


        if (close == 0 && n == 0) {
            response.add(sb.toString());
            return;
        }


        if (n > 0) {
            sb.append('(');
            dfs(sb, close + 1, n - 1);
            sb.setLength(sb.length() - 1);
        }


        if (close > 0) {

            sb.append(')');
            dfs(sb, close - 1, n);
            sb.setLength(sb.length() - 1);
        }


    }

    public static void main(String[] args) {

        GenerateParanthesis generateParanthesis = new GenerateParanthesis();

        List<String> strings = generateParanthesis.generateParanthesis(2);

        System.out.println(strings);
    }
}
