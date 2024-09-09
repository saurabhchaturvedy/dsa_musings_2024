package LLD.LLD002.SplitWise;

class PercentageSplit implements SplitStrategy {
    private User user;
    private double percentage;

    public PercentageSplit(User user, double percentage) {
        this.user = user;
        this.percentage = percentage;
    }

    @Override
    public void split() {
        // Logic for percentage split
        System.out.println("Percentage split for " + user.getName() + ": " + percentage + "%");
    }
}