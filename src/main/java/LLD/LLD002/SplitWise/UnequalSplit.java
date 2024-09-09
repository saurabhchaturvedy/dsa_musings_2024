package LLD.LLD002.SplitWise;

class UnequalSplit implements SplitStrategy {
    private User user;
    private double amount;

    public UnequalSplit(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    @Override
    public void split() {
        // Logic for unequal split
        System.out.println("Unequal split for " + user.getName() + ": " + amount);
    }
}