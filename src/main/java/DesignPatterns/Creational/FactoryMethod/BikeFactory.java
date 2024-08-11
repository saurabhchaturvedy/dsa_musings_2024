package DesignPatterns.Creational.FactoryMethod;

public class BikeFactory extends TransportFactory{
	Transport create() {
		return new Bike();
	}
}