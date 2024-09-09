package LLD.LLD002.SplitWise;

import java.util.ArrayList;
import java.util.List;

class Group {
    private String groupId;
    private String name;
    private List<User> members;
    private List<Expense> expenses;

    public Group(String groupId, String name) {
        this.groupId = groupId;
        this.name = name;
        this.members = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    public void addMember(User user) {
        if (!members.contains(user)) {
            members.add(user);
        }
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        expense.calculateSplits();
    }

    public List<User> getMembers() {
        return members;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Group{" + "groupId='" + groupId + '\'' + ", name='" + name + '\'' + '}';
    }
}
