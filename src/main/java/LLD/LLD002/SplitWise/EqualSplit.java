package LLD.LLD002.SplitWise;

class EqualSplit implements SplitStrategy {
    private User user;
    private double amount;

    public EqualSplit(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    @Override
    public void split() {
        // Logic for equal split
        System.out.println("Equal split for " + user.getName() + ": " + amount);
    }
}