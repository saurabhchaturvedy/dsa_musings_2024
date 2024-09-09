package LLD.LLD002.SplitWise;

import java.util.HashMap;
import java.util.Map;

class BalanceSheet {
    private Map<User, Map<User, Double>> balances;

    public BalanceSheet() {
        this.balances = new HashMap<>();
    }

    public void addBalance(User fromUser, User toUser, double amount) {
        balances.putIfAbsent(fromUser, new HashMap<>());
        balances.get(fromUser).put(toUser, balances.get(fromUser).getOrDefault(toUser, 0.0) + amount);
    }

    public double getBalance(User fromUser, User toUser) {
        return balances.getOrDefault(fromUser, new HashMap<>()).getOrDefault(toUser, 0.0);
    }

    public void settleBalance(User fromUser, User toUser) {
        if (balances.containsKey(fromUser) && balances.get(fromUser).containsKey(toUser)) {
            balances.get(fromUser).remove(toUser);
        }
    }

    public void displayBalances() {
        for (Map.Entry<User, Map<User, Double>> entry : balances.entrySet()) {
            User user1 = entry.getKey();
            for (Map.Entry<User, Double> balanceEntry : entry.getValue().entrySet()) {
                User user2 = balanceEntry.getKey();
                double amount = balanceEntry.getValue();
                System.out.println(user1.getName() + " owes " + user2.getName() + ": " + amount);
            }
        }
    }
}
