package com.oby.autumn.boutika.configuration.pagedList;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Implementation of {@link QueryBuilder} dedicated to building a
 * {@link PagedList}.
 */
public class PagedListBuilder extends QueryBuilder {

	protected final String find_mask;
	protected final String count_mask;
	protected final PageRequest request;
	protected final Transformer<Object[], Object> transformer;

	public PagedListBuilder(final String find_mask, final String count_mask, final PageRequest request,
			final Transformer<Object[], Object> transformer) {
		super();
		this.find_mask = find_mask;
		this.count_mask = count_mask;
		this.request = request;
		this.transformer = transformer;

		this.ob.addColumn(request.getSortColName(), request.getSortDirection());
	}

	public PagedListBuilder(final String find_mask, final String count_mask, final PageRequest request) {
		super();
		this.find_mask = find_mask;
		this.count_mask = count_mask;
		this.request = request;
		this.transformer = new NoopTransformer();

		this.ob.addColumn(request.getSortColName(), request.getSortDirection());
	}

	public PagedList build(final EntityManager em) {
		final String fb_clause = fb.createQueryFilter();
		final String ob_clause = ob.build();

		final Long total = createCountQuery(em, fb_clause);
		final List<Object[]> results = createFindQuery(em, fb_clause, ob_clause);

		final List<PagedRow> rows = handleTransformRows(results, em);
		return new PagedList(request.getPage(), request.getRequestedRowsByPage(), total, rows);
	}

	protected List<Object[]> createFindQuery(final EntityManager em, final String fb_clause, final String ob_clause) {
		final String find_sql = String.format(this.find_mask, fb_clause, ob_clause);
		// log.debug("find query {}", find_sql);

		final TypedQuery<Object[]> query = em.createQuery(find_sql, Object[].class);
		fb.addQueryParameter(query);
		query.setFirstResult(request.getOffset());
		query.setMaxResults(request.getRequestedRowsByPage());
		return query.getResultList();
	}

	protected Long createCountQuery(final EntityManager em, final String fb_clause) {
		final String count_sql = String.format(this.count_mask, fb_clause);
		// log.debug("count query {}", count_sql);

		final TypedQuery<Long> count = em.createQuery(count_sql, Long.class);
		fb.addQueryParameter(count);
		return count.getSingleResult();
	}

	protected List<PagedRow> handleTransformRows(final List<Object[]> results, final EntityManager em) {
		final List<PagedRow> rows = new ArrayList<PagedRow>(results.size());
		for (final Object[] result : results) {
			rows.add(new PagedRow(transformer.id(result), transformer.cells(result)));
		}
		return rows;
	}
}
