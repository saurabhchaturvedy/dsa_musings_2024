package Atlassian.PostKarat20Oct.Router.RegexPrimer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args) {


        Pattern pattern = Pattern.compile("hello");
        Matcher matcher = pattern.matcher("hello");


        boolean found = matcher.find();

        System.out.println(" found : " + found);

        Pattern pattern2 = Pattern.compile("agent 007");
        Matcher matcher2 = pattern2.matcher("agent 008");

        boolean found2 = matcher2.find();

        System.out.println(" found : " + found2);

        Pattern pattern3 = Pattern.compile("agent \\d\\d\\d");
        Matcher matcher3 = pattern3.matcher("agent 008");

        boolean found3 = matcher3.find();

        System.out.println(" found : " + found3);

        Pattern pattern4 = Pattern.compile("agent \\d{3}");
        Matcher matcher4 = pattern4.matcher("agent 004");

        boolean found4 = matcher4.find();

        System.out.println(" found : " + found4);

        Pattern pattern5 = Pattern.compile("agent \\d{3,4}");
        Matcher matcher5 = pattern5.matcher("agent 004");

        boolean found5 = matcher5.find();

        System.out.println(" found : " + found5);


        Pattern pattern6 = Pattern.compile("agent \\d{3,4}$");
        Matcher matcher6 = pattern6.matcher("agent 0047448");

        boolean found6 = matcher6.find();

        System.out.println(" found : " + found6);

        Pattern pattern7 = Pattern.compile("^agent \\d{3,4}$");
        Matcher matcher7 = pattern7.matcher("agent 0047");

        boolean found7 = matcher7.find();

        System.out.println(" found : " + found7);


        Pattern pattern8 = Pattern.compile("^agent [0-6]{3,4}$");
        Matcher matcher8 = pattern8.matcher("agent 0047");

        boolean found8 = matcher8.find();

        System.out.println(" found : " + found8);


        Pattern pattern9 = Pattern.compile("^[aA]gent [0-9]{3,4}$");
        Matcher matcher9 = pattern9.matcher("agent 0047");

        boolean found9 = matcher9.find();

        System.out.println(" found : " + found9);


        Pattern pattern10 = Pattern.compile("^(a|A|aa)gent [0-9]{3,4}$");
        Matcher matcher10 = pattern10.matcher("aagent 0047");

        boolean found10 = matcher10.find();

        System.out.println(" found : " + found10);


        Pattern pattern11 = Pattern.compile("^\\w+gent [0-9]{3,4}$");
        Matcher matcher11 = pattern10.matcher("aagent 0047");

        boolean found11 = matcher11.find();

        System.out.println(" found : " + found11);


        Pattern pattern12 = Pattern.compile("^(a|A|aa)gent (\\d{3,4})$");  // groups = ()
        Matcher matcher12 = pattern12.matcher("aagent 007");

        if (matcher12.find()) {

            System.out.println(matcher12.group());
            System.out.println(matcher12.group(1));
        }


        String cardNumber = "1234-2222-3333-4343";

        Pattern pattern13 = Pattern.compile("\\d{4}-\\d{4}-\\d{4}");  // groups = ()
        Matcher matcher13 = pattern13.matcher(cardNumber);


        String maskedCardNumber = matcher13.replaceAll("XXXX-XXXX-XXXX");

        System.out.println(maskedCardNumber);


        String cardNumber14 = "1234-2222-3333-4343";

        Pattern pattern14 = Pattern.compile("^(\\d+) divided by (\\d+)$");  // groups = ()
        Matcher matcher14 = pattern14.matcher("10 divided by 2");

        if (matcher14.find()) {

            System.out.println(" Result : " + matcher14.replaceFirst("$1/$2"));
        }


        int result = Integer.valueOf(matcher14.group(1)) / Integer.valueOf(matcher14.group(2));

        System.out.println(" result = " + result);

    }
}
