package DesignPatterns.Creational.Builder.Pattern2;

public class SqlQueryBuilder implements QueryBuilder{

	private SqlQuery query = new SqlQuery();

	@Override
	public void from(String from) {
		query.setFrom(from);
	}

	@Override
	public void where(String where) {
		query.setWhere(where);
	}

	@Override
	public Query getQuery() {
		return query;
	}

}