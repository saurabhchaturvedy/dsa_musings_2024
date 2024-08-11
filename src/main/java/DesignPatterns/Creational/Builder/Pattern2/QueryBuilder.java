package DesignPatterns.Creational.Builder.Pattern2;

public interface QueryBuilder {

	void from(String from);

	void where(String where);

	Query getQuery();

}