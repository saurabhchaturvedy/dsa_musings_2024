package DesignPatterns.Creational.Singleton.Serializable;

import java.io.Serializable;

public class DemoSingleton implements Serializable {
	private volatile static DemoSingleton instance = null;

	private static final long serialVersionUID = 1L;
	public static DemoSingleton getInstance() {
		if (instance == null) {
			instance = new DemoSingleton();
		}
		return instance;
	}
	private int i = 10;
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}

	protected Object readResolve() {
		return instance;
	}
}