package CodingSimplified.String.String002;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class FrequencyOfEveryWord {


    public static void main(String[] args) {


        String str = "Best items in category are Samsung lenovo. Samsung items are nice. Lenovo are cool";


        String[] stx = str.split("\\W+");

        Map<String, Long> stringFrequencyMap = Arrays.stream(stx).collect(Collectors.groupingBy(word -> word, Collectors.counting()));


        System.out.println(stringFrequencyMap);

    }
}
