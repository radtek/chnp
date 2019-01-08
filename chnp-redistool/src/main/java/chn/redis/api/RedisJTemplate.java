package chn.redis.api;

import chn.redis.exception.RedisJException;
import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**<p>Redis Java客户端工具类。</p>
 * <p>
 *    按照数据的类型，方法分为以下几个大类：
 *    <ul>
 *        <li>s*：操作Set类型的数据。如{@code sadd()}</li>
 *        <li>hm*：操作HashMap类型的数据。如{@code hmset()}</li>
 *        <li>其他方法属于通用方法</li>
 *    </ul>
 * </p>
 *
 */
public abstract class RedisJTemplate {

	/**<p>获取客户端。</p>
	 *
	 * @param key 缓存的键名
	 * @return 客户端
	 * @throws RedisJException 客户端获取失败
	 */
	public JedisPool getClient(String key) throws RedisJException {
		return null;
	}

	/**<p>关闭代理。</p>
	 * <p>调用本方法，关闭代理中所有的Redis客户端实例，释放资源。</p>
	 *
	 */
	public void close() {}


	/**<p>缓存Set数据。</p>
	 *
	 * @param key 键名
	 * @param values 键值
	 */
	public void sadd(String key, String... values) throws Exception {
		sadd(key, values, null);
	}

	/**<p>缓存Set数据。</p>
	 *
	 * @param key 键名
	 * @param values 键值
	 * @param seconds 失效时长。单位：秒
	 */
	public void sadd(String key, String[] values, Integer seconds) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			jedis.sadd(key, values);

			if (null != seconds)
				jedis.expire(key, seconds);
		}
	}

	/**<p>判断Set中是否存在指定数据。</p>
	 *
	 * @param key 键名
	 * @param value 成员数据
	 * @return true - 存在
	 */
	public boolean sexist(String key, String value) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			return jedis.sismember(key, value);
		}
	}

	/**<p>统计指定Set的长度。</p>
	 *
	 * @param key 键名
	 * @return 长度。如果键名不存在，默认返回0
	 * @see RedisJTemplate#scount(String...)
	 */
	public long scount(String key) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			return jedis.scard(key);
		}
	}

	/**<p>批量统计指定Set的长度。</p>
	 *
	 * @param keys 键名集合
	 * @return 长度集合。如果键名不存在，默认返回0
	 * @throws Exception
	 * @see RedisJTemplate#scount(String)
	 */
	public List<Long> scount(String... keys) throws Exception {
		List<Response<Long>> responses = new ArrayList<>();
		try (Jedis jedis = getClient(keys[0]).getResource(); Pipeline pipeline = jedis.pipelined()) {
			for (String key : keys) {
				responses.add(pipeline.scard(key));
			}
			pipeline.sync();
		}

		List<Long> result = new ArrayList<>();
		for (Response<Long> response : responses) {
			result.add(response.get());
		}
		return 0 == result.size() ? null : result;
	}

	/**<p>删除Set中的指定元素。</p>
	 *
	 * @param key 键名
	 * @param values 元素集合
	 * @return 影响的元素个数
	 */
	public Long sdel(String key, String... values) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			return jedis.srem(key, values);
		}
	}

	public Set<String> sget(String key) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			return jedis.smembers(key);
		}
	}


	/**<p>缓存HashMap数据。</p>
	 *
	 * @param key 键名
	 * @param value 键值。不能为空
	 */
	public void hmset(String key, Map<String, String> value) throws Exception {
		hmset(key, value, null);
	}

	/**<p>缓存HashMap数据。</p>
	 *
	 * @param key 键名
	 * @param value 键值
	 * @param seconds 失效时长。单位：秒
	 */
	public void hmset(String key, Map<String, String> value, Integer seconds) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			jedis.hmset(key, value);

			if (null != seconds)
				jedis.expire(key, seconds);
		}
	}

	/**<p>批量缓存HashMap数据。</p>
	 * <p>注意：单批次数量尽量保持在10000以下，因为管道会一次性取回所有操作的响应信息。</p>
	 *
	 * @param data 哈希数据集。
	 */
	public void hmset(Map<String, Map<String, String>> data) throws Exception {
		hmset(data, null);
	}

	/**<p>批量缓存HashMap数据。</p>
	 * <p>注意：单批次数量尽量保持在10000以下，因为管道会一次性取回所有操作的响应信息。</p>
	 *
	 * @param data 哈希数据集。
	 * @param seconds 失效时间。单位：秒
	 */
	public void hmset(Map<String, Map<String, String>> data, Integer seconds) throws Exception {
		String[] keys = new String[data.size()];
		data.keySet().toArray(keys);
		try (Jedis jedis = getClient(keys[0]).getResource(); Pipeline pipeline = jedis.pipelined()) {
			for (String key : data.keySet()) {
				pipeline.hmset(key, data.get(key));
				if (null != seconds) pipeline.expire(key, seconds);
			}
			pipeline.sync();
		}
	}

	/**<p>缓存HashMap的某个字段。</p>
	 *
	 * @param key 键名。必填
	 * @param fieldName 字段名。必填
	 * @param value 字段值。必填
	 */
	public void hmset(String key, String fieldName, String value) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			jedis.hset(key, fieldName, value);
		}
	}

	/**<p>获取HashMap数据。</p>
	 *
	 * @param key 键名
	 * @return 键值
	 */
	public Map<String, String > hmget(String key) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			Map<String, String> result = jedis.hgetAll(key);
			return 0 == result.size() ? null : result;
		}
	}

	/**<p>查询HashMap数据的某个字段。</p>
	 *
	 * @param key 键名
	 * @param filedName 要查询的字段名
	 * @return 字段值
	 */
	public String hmget(String key, String filedName) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			return jedis.hget(key, filedName);
		}
	}

	/**<p>批量查询HashMap的字段。</p>
	 *
	 * @param key 键名
	 * @param filedNames 字段名
	 * @return 字段值列表
	 */
	public List<String> hmget(String key, String... filedNames) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			List<String> result = jedis.hmget(key, filedNames);
			return 0 == result.size() ? null : result;
		}
	}

	/**<p>判断HashMap中是否存在指定字段。</p>
	 *
	 * @param key 键名
	 * @param fieldName 字段名
	 * @return true - 存在
	 * @see RedisJTemplate#exist(String)
	 */
	public boolean hmexist(String key, String fieldName) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			return jedis.hexists(key, fieldName);
		}
	}

	/**<p>删除HashMap指定的字段。</p>
	 *
	 * @param key 键名
	 * @param fieldNames 要删除的字段名。如果不指定，则删除整条数据。
	 */
	public void hmdel(String key, String... fieldNames) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			if (0 < fieldNames.length)
				jedis.hdel(key, fieldNames);
			else jedis.del(key);
		}
	}

	/**<p>对HashMap的指定字段做加法运算。</p>
	 *
	 * @param key 键名
	 * @param field 字段名
	 * @param num 数字。可以为负。
	 * @throws Exception
	 */
	public void hmInc(String key, String field, long num) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			jedis.hincrBy(key, field, num);
		}
	}


	public void set(String key, String value) throws Exception {
		set(key, value, null);
	}

	public void set(String key, String value, Integer seconds) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			jedis.set(key, value);

			if (null != seconds)
				jedis.expire(key, seconds);
		}
	}

	/**<p>根据键名删除整条缓存。</p>
	 *
	 * @param key 键名
	 * @see RedisJTemplate#hmdel(String, String...)
	 */
	public Long del(String key) throws Exception {
		Long result;
		try (Jedis jedis = getClient(key).getResource()) {
			result = jedis.del(key);
		}
		return result;
	}

	public String get(String key) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			return jedis.get(key);
		}
	}

	/**<p>设置缓存过期时长。</p>
	 *
	 * @param key 键名
	 * @param seconds 过期时长。单位：秒
	 */
	public void expire(String key, Integer seconds) throws Exception {
		if (null != seconds) {
			try (Jedis jedis = getClient(key).getResource()) {
				jedis.expire(key, seconds);
			}
		}
	}

	/**<p>清空缓存。</p>
	 *
	 * @see RedisJTemplate#delMulti(String)
	 */
	/*public void delAll() {
		Jedis jedis = getClient(key).getResource();
		jedis.flushAll();
		jedis.close();
	}*/

	/**<p>根据通配格式批量删除。其中，通配符包括：</p>
	 * <ul>
	 *     <li>? - 通配单个字符</li>
	 *     <li>* - 通配0或多个字符</li>
	 * </ul>
	 * <p>例如，“CpeInfo-*”能够匹配到“CpeInfo-1”和“CpeInfo-12”</p>
	 *
	 * @param pattern 键名格式
	 */
	public void delMulti(String pattern) throws Exception {
		try (Jedis jedis = getClient(pattern).getResource()) {
			Set<String> keys = jedis.keys(pattern);
			if (0 < keys.size()) {
				String[] keysArray = keys.toArray(new String[0]);
				jedis.del(keysArray);
			}
		}
	}

	/**<p>批量缓存</p>
	 *
	 * @param prefix 键名前缀。根据键名前缀选择Redis客户端实例，并结合主键拼接键名。
	 * @param keyName 主键参数。例如，键名CpeInfo-001由前缀CpeInfo-和主键001组成，主键001在Map中的参数是hostId。
	 * @param dataset 数据集。HashMap数据集。
	 * @throws Exception
	 */
	public void batch(String prefix, String keyName, List<Map<String, String>> dataset) throws Exception {
		try (Jedis jedis = getClient(prefix).getResource();
			Pipeline pipeline = jedis.pipelined()) {
			for (Map<String, String> data : dataset) {
				pipeline.hmset(prefix + data.get(keyName), data);
			}
			pipeline.sync();
			pipeline.close();
		}
	}

	/**<p>根据通配格式批量查询键名。其中，通配符包括：</p>
	 * <ul>
	 *     <li>? - 通配单个字符</li>
	 *     <li>* - 通配0或多个字符</li>
	 * </ul>
	 * <p>例如，“CpeInfo-*”能够匹配到“CpeInfo-1”和“CpeInfo-12”</p>
	 *
	 * @param pattern 键名的通配格式。
	 * @return 键名列表。
	 */
	public Set<String> findKeys(String pattern) throws Exception {
		try (Jedis jedis = getClient(pattern).getResource()) {
			Set<String> result = jedis.keys(pattern);
			return 0 == result.size() ? null : result;
		}
	}

	/**<p>判断是否存在指定键名的缓存。</p>
	 *
	 * @param key 键名
	 * @return true - 存在
	 */
	public boolean exist(String key) throws Exception {
		try (Jedis jedis = getClient(key).getResource()) {
			return jedis.exists(key);
		}
	}

	public List<Boolean> batchExist(String prefix, List<String> keys) throws Exception {
		List<Response<Boolean>> responses = new ArrayList<>(keys.size());
		try(Jedis jedis = getClient(prefix).getResource();
			Pipeline pipeline = jedis.pipelined()) {
			for (String key : keys)
				responses.add(pipeline.exists(key));
			pipeline.sync();
			pipeline.close();
		}

		List<Boolean> results = new ArrayList<>(responses.size());
		for (Response<Boolean> response : responses)
			results.add(response.get());
		return results;
	}

	/**<p>缓存Java对象。注意：如果缓存已存在，会覆盖掉相同的字段。</p>
	 *
	 * @param entity Java对象
	 * @throws Exception 未指定键名
	 */
	public void insertJ(String key, Object entity) throws Exception {
		Map<String, String> value = new HashMap<>();
		assembleValue(entity, value);

		if (null != key && !"".equals(key.trim())) {
			hmset(key, value);
		}else throw new RuntimeException("未指定键名");

	}

	/**<p>更新Java对象。注意，被更新的缓存必须存在，否则会抛异常。</p>
	 *
	 * @param entity Java对象
	 * @throws Exception 未指定键名或缓存不存在
	 * @see RedisJTemplate#updateJIgnoreExc(String, Object)
	 */
	public void updateJ(String key, Object entity) throws Exception {
		Map<String, String> value = new HashMap<>();
		assembleValue(entity, value);

		if (null != key && !"".equals(key.trim())) {
			if (exist(key)) hmset(key, value);
			else throw new RuntimeException("缓存" + key + "不存在");
		}else throw new RuntimeException("未指定键名");
	}

	/**<p>更新Java对象。注意：如果缓存不存在则不会更新，且不会抛异常。</p>
	 *
	 * @param entity Java对象
	 * @throws Exception 无法组装键名
	 * @see RedisJTemplate#updateJ(String, Object)
	 */
	public void updateJIgnoreExc(String key, Object entity) throws Exception {
		Map<String, String> value = new HashMap<>();
		assembleValue(entity, value);

		if (null != key && !"".equals(key.trim())) {
			if (exist(key)) hmset(key, value);
		}else throw new RuntimeException("未指定键名");
	}

	/**<p>根据键名查询Java对象。</p>
	 *
	 * @param key 键名
	 * @param clazz Java类型
	 * @return Java对象
	 */
	public Object getByKey(String key, Class clazz) throws Exception {
		Map<String, String> value = hmget(key);
		if (null == value) return null;
		else return parseValue(value, clazz);
	}

	/**<p>根据通配键名批量查询Java对象。</p>
	 *
	 * @param pattern 通配键名
	 * @param clazz Java类型
	 * @return Java对象集合
	 */
	public List<Object> findByReg(String pattern, Class clazz) throws Exception {
		List<Object> result = new ArrayList<>();

		Set<String> keys = findKeys(pattern);
		if (null != keys && 0 < keys.size()) {

			List<Response<Map<String, String>>> responses = new ArrayList<>();
			try (Jedis jedis = getClient(pattern).getResource(); Pipeline pipeline = jedis.pipelined()) {
				for (String key : keys) responses.add(pipeline.hgetAll(key));
				pipeline.sync();
			}

			for (Response<Map<String, String>> response : responses) {
				result.add(parseValue(response.get(), clazz));
			}
		}
		return 0 == result.size() ? null : result;
	}



	private void assembleValue(Object entity, Map<String, String> value) throws Exception {
		Class clazz = entity.getClass();

		Field[] fields = clazz.getDeclaredFields();
		Field.setAccessible(fields, true);
		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers())) continue;

			Object val = field.get(entity);
			if (null == val || "".equals(val)) continue;

			value.put(field.getName(), field2String(field.getType().getName(), val));
		}
	}

	private Object parseValue(Map<String, String> value, Class clazz) {
		return JSONObject.parseObject(JSONObject.toJSONString(value), clazz);
	}

	private String field2String(String type, Object value) {
		switch(type) {
			case "java.util.Date":
				return String.valueOf(((Date) value).getTime());
			default:
				return value.toString();
		}
	}

}