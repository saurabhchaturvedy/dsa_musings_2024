package LLD.LLD002.SplitWise;

import java.util.ArrayList;
import java.util.List;

public class SplitwiseExample {
    public static void main(String[] args) {
        User user1 = new User("U1", "Alice", "alice@example.com");
        User user2 = new User("U2", "Bob", "bob@example.com");
        User user3 = new User("U3", "Charlie", "charlie@example.com");

        Group group = new Group("G1", "Friends Trip");

        group.addMember(user1);
        group.addMember(user2);
        group.addMember(user3);

        // Example Expense: 300 split equally among 3 users
        List<SplitStrategy> equalSplits = new ArrayList<>();
        equalSplits.add(new EqualSplit(user1, 100));
        equalSplits.add(new EqualSplit(user2, 100));
        equalSplits.add(new EqualSplit(user3, 100));

        Expense expense1 = new Expense("E1", 300, user1, group, equalSplits, ExpenseType.EQUAL);
        group.addExpense(expense1);

        BalanceSheet balanceSheet = new BalanceSheet();
        balanceSheet.addBalance(user2, user1, 100);
        balanceSheet.addBalance(user3, user1, 100);

        balanceSheet.displayBalances();
    }
}
