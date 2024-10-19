package Learnings.Tesco.Pre;

import Learnings.Tesco.RealStuff19Oct.Cart;

public interface Rule {

    public boolean applyRule(Cart cart);

    public String getName();
}
