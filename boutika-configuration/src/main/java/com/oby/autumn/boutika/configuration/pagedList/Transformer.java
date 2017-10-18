package com.oby.autumn.boutika.configuration.pagedList;

/**
 * Interface of a transformer, used by {@link QueryBuilder} implementations to transform the type of
 * data returned by the query into the type expected to be returned (String for a
 * {@link PagedListBuilder}).
 * 
 * @param <S> the type returned by the query (a row of data: an entity or arrays of objects)
 * @param <D> the type expected (the type of a single column: Object or String).
 */
public interface Transformer<S, D> {

	/**
	 * Provides a unique identifier from the row.
	 * @param row the row of data.
	 * @return the unique id for the row
	 */
	public D id(S row);

	/**
	 * Transforms a row of data into an array of expected type.
	 * @param row an original row of data
	 * @return an array the expected type.
	 */
	public D[] cells(S row);
}
