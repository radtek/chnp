package chn.redis.config;

import chn.redis.config.entity.RedisJInitial;
import chn.redis.exception.RedisJException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;

import java.util.*;

public class RedisJPool {
	private static final Logger log = LoggerFactory.getLogger(RedisJPool.class);

	public static final String CONFIG_HOST = "host";
	public static final String CONFIG_PORT = "port";
	public static final String CONFIG_MAXT_TOTAL = "maxTotal";
	public static final String CONFIG_MAX_IDLE = "maxIdle";
	public static final String CONFIG_MIN_IDLE = "minIdle";
	public static final String CONFIG_WAIT_WHEN_EXHAUSTED = "waitWhenExhausted";
	public static final String CONFIG_MAX_WAIT_MILLIS = "maxWaitMillis";
	public static final String CONFIG_TEST_ON_BORROW = "testOnBorrow";
	public static final String CONFIG_TEST_ON_RETURN = "testOnReturn";

	/**<p>Redis客户端池</p>
	 * <p>
	 *     Key为IP，Value为Redis客户端实例。注意：一个IP只能映射一个Redis客户端。
	 * </p>
	 */
	private Map<String, JedisPool> clients = new HashMap<>();

	/**<p>键名前缀与IP的映射关系</p>
	 * <p>
	 *     Key为缓存键名的前缀，Value为Redis服务器的IP。
	 * </p>
	 */
	private Map<String, String> keyMappings = new HashMap<>();

	/**<p>根据IP获取客户端实例。</p>
	 *
	 * @param host Redis服务器的IP
	 * @return 客户端实例
	 * @throws RedisJException 未提供IP或客户端不存在
	 */
	public JedisPool getClient(String host) throws RedisJException {
		if (null == host || "".equals(host.trim()))
			throw new RedisJException("获取Redis客户端失败！未提供Redis主机IP。");

		JedisPool client = clients.get(host);
		if (null == client)
			throw new RedisJException("获取Redis客户端失败！IP为" + host + "的Redis客户端不存在。");

		return client;
	}

	/**<p>清空Redis客户端池。</p>
	 *
	 */
	public void destroy() {
		destroy(null);
	}

	/**<p>根据新的Redis地址集合清理被移除的Redis服务客户端。</p>
	 *
	 * @param newHosts 新的Redis服务IP集合。为空时会清空客户端池。
	 */
	public void destroy(Set<String> newHosts) {
		boolean removeAll = null == newHosts;

		synchronized (this) {
			for (Map.Entry<String, JedisPool> entry : this.clients.entrySet()) {
				// 若Redis服务器本次被移除，则销毁客户端
				if (removeAll || !newHosts.contains(entry.getKey())) {
					JedisPool client = entry.getValue();
					if (!client.isClosed()) client.close();

					this.clients.remove(entry.getKey());

					if (!removeAll)
						newHosts.remove(entry.getKey());

					log.info("IP为" + entry.getKey() + "的Redis服务已被移除！客户端已被销毁！！");
				}
			}
		}
	}

	/**<p>初始化客户端池。</p>
	 *
	 * @param configs Redis配置集合
	 * @param keyMaps KEY-IP映射关系
	 * @throws RedisJException
	 */
	public void initializePool(List<Map<String, String>> configs, Map<String, String> keyMaps) throws RedisJException {
		if (null == configs || 0 == configs.size())
			throw new RedisJException("Redis客户端代理创建失败！未提供Redis配置。");

		Set<String> newHosts = new HashSet<>();
		List<RedisJInitial> initials = new ArrayList<>();
		for (Map<String, String> config : configs) {
			initials.add(create(config));
			newHosts.add(config.get(CONFIG_HOST).trim());
		}

		synchronized (this) {
			// 销毁被移除的Redis服务客户端
			destroy(newHosts);

			// 创建新的Redis客户端
			for (RedisJInitial initial : initials) {
				if (!this.clients.containsKey(initial.getHost())) {
					this.clients.put(
							initial.getHost(),
							new JedisPool(initial, initial.getHost(), initial.getPort())
					);
					log.info("IP为" + initial.getHost() + "的Redis服务被添加！客户端已生成！！");
				}
			}

			// 设置KEY-IP映射关系
			if (null == keyMaps) this.keyMappings.clear();
			else this.keyMappings = keyMaps;
		}
	}

	private RedisJInitial create(Map<String, String> config) {
		RedisJInitial redisJInitial = new RedisJInitial();
		redisJInitial.setHost(config.get(CONFIG_HOST));
		redisJInitial.setPort(config.get(CONFIG_PORT));
		redisJInitial.setMaxTotal(config.get(CONFIG_MAXT_TOTAL));
		redisJInitial.setMaxIdle(config.get(CONFIG_MAX_IDLE));
		redisJInitial.setMinIdle(config.get(CONFIG_MIN_IDLE));
		// TODO: More configurations
		redisJInitial.setBlockWhenExhausted(config.get(CONFIG_WAIT_WHEN_EXHAUSTED));
		redisJInitial.setMaxWaitMillis(config.get(CONFIG_MAX_WAIT_MILLIS));
		redisJInitial.setTestOnBorrow(config.get(CONFIG_TEST_ON_BORROW));
		redisJInitial.setTestOnReturn(config.get(CONFIG_TEST_ON_RETURN));

		return redisJInitial;
	}

	public String getKeyMapping(String keyPrefix) {
		return this.keyMappings.get(keyPrefix);
	}

	public Map<String, String> getKeyMappings() {
		return keyMappings;
	}

	/**<p>设置键名前缀与IP映射。</p>
	 * <p>
	 *     注意：该操作会覆盖掉旧的映射关系。
	 *     如不想覆盖，可使用{@code addaddKeyMappings()}或{@code addKeyMapping()}方法。
	 * </p>
	 *
	 * @param keyMappings 映射关系
	 * @see RedisJPool#addKeyMappings(Map)
	 * @see RedisJPool#addKeyMapping(String, String)
	 */
	public void setKeyMappings(Map<String, String> keyMappings) {
		this.keyMappings = keyMappings;
	}

	/**<p>批量添加键名前缀与IP映射。</p>
	 *
	 * @param keyMappings 映射关系
	 * @see RedisJPool#addKeyMappings(Map)
	 * @see RedisJPool#addKeyMapping(String, String)
	 */
	public void addKeyMappings(Map<String, String> keyMappings) {
		this.keyMappings.putAll(keyMappings);
	}

	/**<p>添加键名前缀与IP映射。</p>
	 *
	 * @param keyPrefix 键名前缀
	 * @param ip 缓存的目标IP
	 * @see RedisJPool#setKeyMappings(Map)
	 * @see RedisJPool#addKeyMappings(Map)
	 */
	public void addKeyMapping(String keyPrefix, String ip) {
		this.keyMappings.put(keyPrefix, ip);
	}

}