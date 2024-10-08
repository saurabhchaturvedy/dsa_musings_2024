package DesignPatterns.Creational.Builder.Pattern2;

public class SqlQuery implements Query{

	private String from;

	private String where;

	@Override
	public void execute() {
		System.out.println("Executing sqlQuery from: "+from+" where "+where);
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setWhere(String where) {
		this.where = where;
	}



}