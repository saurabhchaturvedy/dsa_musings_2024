package Atlassian.KaratFriday;

import java.util.*;

public class DomainVisit1 {


    public static List<String> subDomainvisitCount(List<String> domains) {


        Map<String, Integer> domainvisitmap = new HashMap<>();


        for (String domain : domains) {

            String[] domainData = domain.split(" ");

            Integer visitCount = Integer.parseInt(domainData[0]);
            String domainName = domainData[1];
            int length = domainName.length();

            if (domainvisitmap.containsKey(domainName)) {

                domainvisitmap.put(domainName, domainvisitmap.get(domainName) + visitCount);
            } else {

                domainvisitmap.put(domainName, visitCount);
            }

            for (int i = 0; i < length; i++) {

                if (domainName.charAt(i) == '.') {

                    String temp = domainName.substring(i + 1, length);

                    if (domainvisitmap.containsKey(temp)) {

                        domainvisitmap.put(temp, domainvisitmap.get(temp) + visitCount);
                    } else {

                        domainvisitmap.put(temp, visitCount);
                    }

                }
            }
        }


        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : domainvisitmap.entrySet()) {

            int visitCount = entry.getValue();
            String domainName = entry.getKey();

            result.add(visitCount + " " + domainName);
        }

        return result;
    }


    public static void main(String[] args) {

        String[]domaincount = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};

        System.out.println(subDomainvisitCount(Arrays.asList(domaincount)));
    }
}
