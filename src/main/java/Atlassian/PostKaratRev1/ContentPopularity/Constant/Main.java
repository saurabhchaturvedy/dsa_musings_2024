package Atlassian.PostKaratRev1.ContentPopularity.Constant;

public class Main {


    public static void main(String[] args) {


        PopularityContent popularityContent = new PopularityContent();


        popularityContent.increasePopularity(7);
        popularityContent.increasePopularity(7);
        popularityContent.increasePopularity(8);
        popularityContent.increasePopularity(8);
        popularityContent.decreasePopularity(8);

        System.out.println(popularityContent.getMostPopularContent());
    }
}
