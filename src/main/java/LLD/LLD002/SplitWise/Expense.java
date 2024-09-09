package LLD.LLD002.SplitWise;

import java.util.List;

class Expense {
    private String expenseId;
    private double amount;
    private User paidBy;
    private Group group;
    private List<SplitStrategy> splits;
    private ExpenseType expenseType;

    public Expense(String expenseId, double amount, User paidBy, Group group, List<SplitStrategy> splits, ExpenseType expenseType) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.paidBy = paidBy;
        this.group = group;
        this.splits = splits;
        this.expenseType = expenseType;
    }

    public void calculateSplits() {
        for (SplitStrategy split : splits) {
            split.split();
        }
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<SplitStrategy> getSplits() {
        return splits;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    @Override
    public String toString() {
        return "Expense{" + "expenseId='" + expenseId + '\'' + ", amount=" + amount + ", paidBy=" + paidBy.getName() + ", group=" + group.getName() + ", expenseType=" + expenseType + '}';
    }
}
