package BackTracking;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {


    List<String> result = new ArrayList<>();
    char[] arr;

    public List<String> restoreIPAddresses(String str) {

        this.arr = str.toCharArray();


        List<String> currComb = new ArrayList<>();

        dfs(currComb, 0);

        return result;
    }

    private void dfs(List<String> currComb, int index) {

        int size = currComb.size();
        if (size > 4) return;

        if (size == 4 && index == arr.length) {

            String str = prepareString(currComb);
            result.add(str);
            return;
        }


        StringBuilder sb = new StringBuilder();

        for (int i = index; i < arr.length && i < index + 3; i++) {

            sb.append(arr[i]);
            if (!checkValidString(sb.toString())) break;
            currComb.add(sb.toString());
            dfs(currComb, i + 1);
            currComb.remove(currComb.size() - 1);
        }
    }


    public boolean checkValidString(String str) {

        if (str.length() > 1 && str.charAt(0) == '0') return false;
        return Integer.parseInt(str) <= 255;
    }


    public String prepareString(List<String> combinations) {

        StringBuilder sb = new StringBuilder();

        for (String str : combinations) {

            sb.append(str);
            sb.append('.');
        }

        System.out.println(sb.substring(0, sb.length() - 1));
        return sb.substring(0, sb.length() - 1);
    }


    public static void main(String[] args) {


        String str = "101023";

        RestoreIPAddresses restoreIPAddresses = new RestoreIPAddresses();

        List<String> strings = restoreIPAddresses.restoreIPAddresses(str);

        System.out.println(strings);
    }
}
