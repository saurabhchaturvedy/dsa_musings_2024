package DesignPatterns.Creational.FactoryMethod;

public class CarFactory extends TransportFactory{
	Transport create() {
		return new Car();
	}
}