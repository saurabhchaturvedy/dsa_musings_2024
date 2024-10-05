package Atlassian.Karat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubdomainVisitCount {


    public static List<String> subdomainVisits(String[] cpdomains) {

        Map<String, Integer> map = new HashMap<>();

        for (String s : cpdomains) {

            String[] domainMetaData = s.split(" ");

            int visitCount = Integer.parseInt(domainMetaData[0]);
            String domain = domainMetaData[1];
            int length = domain.length();

            if (map.containsKey(domain)) {

                map.put(domain, map.get(domain) + visitCount);
            } else {

                map.put(domain, visitCount);
            }

            for (int i = 0; i < length; i++) {

                if (domain.charAt(i) == '.') {

                    String temp = domain.substring(i + 1, length);

                    if (map.containsKey(temp)) {

                        map.put(temp, map.get(temp) + visitCount);
                    } else {

                        map.put(temp, visitCount);
                    }
                }
            }
        }

        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            String count = String.valueOf(entry.getValue());
            result.add(count + " " + entry.getKey());
        }

        return result;

    }


    public static void main(String[] args) {


        String[] str = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};


        List<String> result = subdomainVisits(str);

        System.out.println(result);
    }
}
