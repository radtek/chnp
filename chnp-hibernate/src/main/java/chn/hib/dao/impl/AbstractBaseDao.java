package chn.hib.dao.impl;

import chn.hib.annotation.Where;
import chn.hib.common.CommonQuery;
import chn.hib.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

public abstract class AbstractBaseDao<E> implements BaseDao<E> {

	private SessionFactory sessionFactory;

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	protected Class<E> getEntityClass() {
		return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public Serializable insert(E entity) {
		return this.getCurrentSession().save(entity);
	}

	@Override
	public void delete(E entity) {
		this.getCurrentSession().delete(entity);
	}

	@Override
	public E get(Serializable id) {
		return (E) this.getCurrentSession().get(this.getEntityClass(), id);
	}

	@Override
	public void update(E entity) {
		this.getCurrentSession().update(entity);
	}

	/**<p>构建SQL语句中的查询字段列表。</p>
	 * <p>
	 *     注意：构建过程是对实体类进行反射，只会查询被{@code javax.persistence.Column}注解的字段。
	 * </p>
	 *
	 * @param commonQuery 查询条件对象
	 * @return 字段列表
	 */
	protected String buildColumnInSql(CommonQuery commonQuery) {
		StringBuilder select = new StringBuilder();
		String tableName = getEntityClass().getAnnotation(Table.class).name();

		Field[] fields = getEntityClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Column.class)) {
				select.append(", ").append(tableName).append(".").append(field.getAnnotation(Column.class).name());
			}
		}
		select.append(null == commonQuery.getAdditionalColumns() ? "" : commonQuery.getAdditionalColumns());
		return select.substring(1);
	}

	/**<p>构建SQL语句中的WHERE部分。</p>
	 *
	 * @param commonQuery 查询条件对象
	 * @param params 参数列表容器
	 * @return WHERE子句
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	protected String buildWhereInSql(CommonQuery commonQuery, Map<String, Object> params) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder where = new StringBuilder(1024);
		where.append("where 1=1 ");
		String tableName = getEntityClass().getAnnotation(Table.class).name();

		Field[] fields = commonQuery.getClass().getDeclaredFields();
		Field.setAccessible(fields, true);
		for (Field field:fields) {
			Object value = field.get(commonQuery);
			if (null != value && !"".equals(value.toString().trim()) && field.isAnnotationPresent(Where.class)) {
				Where an = field.getAnnotation(Where.class);
				where.append("and ").append(tableName).append(".").append(an.column()).append(an.operator()).append(":").append(field.getName()).append(" ");
				params.put(field.getName(), value);
			}
		}

		if (null != commonQuery.getAdditionalFilters() && !"".equals(commonQuery.getAdditionalFilters())) {
			where.append(commonQuery.getAdditionalFilters());
		}

		return where.toString();
	}
}