package chn.hib.dao.impl;

import chn.hib.common.CommonQuery;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>Hibernate基础Dao接口的实现类</p>
 * <p>基础Dao提供了三种操作数据库的方式：Hibernate现有的API、HQL和SQL。</p>
 * <ul>
 *     <li>Hibernate的API：该类方法不带任何{@code In*}后缀；</li>
 *     <li>HQL：该类方法带有{@code InHql}后缀；</li>
 *     <li>SQL：该类方法带有{@code InSql}后缀。</li>
 * </ul>
 * <p>注意：本实现类基于Hibernate 5.0版本，与5.0及以下版本存在差异。</p>
 *
 * @CreateBy chngzhen@outlook.com
 * @CreateOn 2018-11-06
 * @UpdateBy chngzhen@outlook.com
 * @UpdateOn 2018-11-06
 *
 * @param <E> 实体类型
 */
public class Hib50BaseDaoImpl<E> extends AbstractBaseDao<E> {

	@Override
	public List<E> findAllInHql() {
		String hql = "FROM " + getEntityClass().getSimpleName();
        Query query = this.getCurrentSession().createQuery(hql);
        List<E> list = query.list();
        return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int updateByIdInHql(E entity) throws Exception {
		StringBuilder hql = new StringBuilder("update ").append(this.getEntityClass().getSimpleName()).append(" t ");
		StringBuilder where = new StringBuilder(" where ");
		boolean whereIsNull = true, setIsNull = true;
		Map<String, Object> params = new HashMap<>();

		Field[] fields = this.getEntityClass().getDeclaredFields();
		Field.setAccessible(fields, true);
		for (Field field:fields) {
			Object value = field.get(entity);
			if (null == value || "".equals(value.toString()) || field.isAnnotationPresent(Transient.class)) continue;
			if(field.isAnnotationPresent(Id.class)) {
				where.append(whereIsNull ? "t." : "and t.").append(field.getName()).append("=:").append(field.getName());
				params.put(field.getName(), value);
				whereIsNull = false;
			}else if (field.isAnnotationPresent(Column.class)) {
				hql.append(setIsNull ? "set t." : ", t.").append(field.getName()).append("=:").append(field.getName());
				params.put(field.getName(), value);
				setIsNull = false;
			}
		}
		if (whereIsNull || setIsNull) throw new RuntimeException("未提供主键或更新数据");

		Query query = this.getCurrentSession().createQuery(hql.append(where).toString());
		this.addParameter(query, params);
		return query.executeUpdate();
	}



	@SuppressWarnings("unchecked")
	@Override
	public E getByConditionInSql(CommonQuery commonQuery)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Map<String, Object> params = new HashMap<>();

		String tableName = getEntityClass().getAnnotation(Table.class).name();
		String sql = "select " + buildColumnInSql(commonQuery) +
				" from " + tableName + " " + (null == commonQuery.getAdditionalTables() ? "" : commonQuery.getAdditionalTables()) +
				buildWhereInSql(commonQuery, params);

		Query query = this.getCurrentSession().createSQLQuery(sql).addEntity(getEntityClass());
	    this.addParameter(query, params);
	    List<E> results = query.list();
	    if (null == results || 1 > results.size()) {
			return null;
		}
		return results.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findByConditionInSql(CommonQuery commonQuery) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> params = new HashMap<>();

		String tableName = getEntityClass().getAnnotation(Table.class).name();
		String sql = "select " + buildColumnInSql(commonQuery) +
				" from " + tableName + " " + (null == commonQuery.getAdditionalTables() ? "" : commonQuery.getAdditionalTables()) +
				buildWhereInSql(commonQuery, params);

		Query query = this.getCurrentSession().createSQLQuery(sql).addEntity(getEntityClass());
		if (null != commonQuery.getStart() && null != commonQuery.getLength()) {
			query.setFirstResult(commonQuery.getStart()).setMaxResults(commonQuery.getLength());
		}

	    this.addParameter(query, params);
		return query.list();
	}

	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	@Override
	public List findMapByConditionInSql(CommonQuery commonQuery) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> params = new HashMap<>();

		String tableName = getEntityClass().getAnnotation(Table.class).name();
		String sql = "select " + buildColumnInSql(commonQuery) +
				" from " + tableName + " " +
				(null == commonQuery.getAdditionalTables() ? "" : commonQuery.getAdditionalTables()) +
				buildWhereInSql(commonQuery, params);

		Query query = this.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	    this.addParameter(query, params);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public long countByConditionInSql(CommonQuery commonQuery) throws IllegalArgumentException, IllegalAccessException {
		String tableName = getEntityClass().getAnnotation(Table.class).name();
		StringBuilder sql = new StringBuilder("select count(*) ")
				.append(" from ")
				.append(tableName)
				.append(" ")
				.append(null == commonQuery.getAdditionalTables() ? "" : commonQuery.getAdditionalTables())
				.append(" where 1=1");

		Map<String, Object> params = new HashMap<>();
		Field[] fields = commonQuery.getClass().getDeclaredFields();
		Field.setAccessible(fields, true);
		for (Field field:fields) {
			Object value = field.get(commonQuery);
			if (null != value && !"".equals(value.toString()) && field.isAnnotationPresent(Where.class)) {
				sql.append(" and ")
						.append(tableName)
						.append(".")
						.append(field.getAnnotation(Where.class).column())
						.append(field.getAnnotation(Where.class).operation())
						.append(":")
						.append(field.getName());
				params.put(field.getName(), value);
			}
		}

		Query query = this.getCurrentSession().createSQLQuery(sql.toString());
	    this.addParameter(query, params);
		return ((BigInteger) query.uniqueResult()).longValue();
	}


    @SuppressWarnings("rawtypes")
	protected void addParameter(Query query, Map<String, Object> params) {
        for (String key:params.keySet()) query.setParameter(key, params.get(key));
    }

}