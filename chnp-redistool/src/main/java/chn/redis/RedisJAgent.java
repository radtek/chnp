package chn.redis;

import chn.redis.api.RedisJTemplate;
import chn.redis.config.RedisJPool;
import chn.redis.exception.RedisJException;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisJAgent extends RedisJTemplate {

	private RedisJPool redisJPool = new RedisJPool();

	/**<p>构造方法</p>
	 * <p>
	 *     Redis的配置应当包括但不强制包括以下参数：
	 *     <ul>
	 *         <li>host：Redis服务器的主机。默认：localhost</li>
	 *         <li>port：Redis服务器的端口。默认：6379</li>
	 *         <li>maxTotal：每个Redis客户端的资源池允许的最大连接数。默认：8</li>
	 *         <li>maxIdle：每个Redis客户端的资源池允许的最大空闲连接数。默认：8。并发量较大的情况下，建议与maxTotal保持一致，以避免创建连接的开销。</li>
	 *         <li>minIdle：每个Redis客户端的资源池允许的最小空闲连接数。默认：0</li>
	 *         <li>waitWhenExhausted：当资源池没有空闲连接时是否需要等待。默认：1（等待）。</li>
	 *         <li>maxWaitMillis：当资源池没有空闲连接时，调用者等待的时长。默认：-1（永远等待）。单位：毫秒。</li>
	 *         <li>testOnBorrow：从资源池中获取连接时是否需要检测可用性。默认：0（不检测）。连接不可用则会移除该连接。</li>
	 *         <li>testOnReturn：将连接归还给资源池时是否需要检测可用性。默认：0（不检测）。连接不可用则会移除该连接。</li>
	 *     </ul>
	 *     注意：规定所有布尔取值0表示“否”，1表示“是”。
	 * </p>
	 *
	 * @param redisConfigs Redis配置列表。
	 * @param keyMaps 键名与Redis服务器IP的映射关系。
	 * @throws RedisJException 未提供Redis配置参数
	 */
	public RedisJAgent(List<Map<String, String>> redisConfigs, Map<String, String> keyMaps) throws RedisJException {
		this.redisJPool.initializePool(redisConfigs, keyMaps);
	}

	public void refresh(List<Map<String, String>> redisConfigs) {
		this.redisJPool.initializePool(redisConfigs, this.redisJPool.getKeyMappings());
	}

	public void refresh(Map<String, String> keyMaps) {
		this.redisJPool.setKeyMappings(keyMaps);
	}

	public void refresh(List<Map<String, String>> redisConfigs, Map<String, String> keyMaps) {
		this.redisJPool.initializePool(redisConfigs, keyMaps);
	}

	@Override
	public JedisPool getClient(String key) throws RedisJException {
		if (null == key || "".equals(key)) throw new RedisJException("键名不能为空");
		String prefix = key.split("-")[0];

		String ip = this.redisJPool.getKeyMapping(prefix);
		if (null == ip) throw new RedisJException("键名前缀" + prefix + "无IP映射");
		else return this.redisJPool.getClient(ip);
	}

	@Override
	public void close() {
		this.redisJPool.destroy();
	}

	public static void main(String[] args) throws Exception {
		// 使用样例
		List<Map<String, String>> configs = new ArrayList<Map<String, String>>() {
			{
				add(new HashMap<String, String>() {
					{
						put(RedisJPool.CONFIG_HOST, "192.168.21.32");
					}
				});
			}
		};
		Map<String, String> mappings = new HashMap<String, String>() {
			{
				put("TestInfo", "192.168.21.32");
			}
		};

		RedisJAgent agent = new RedisJAgent(configs, mappings);

		agent.batch("TestInfo-", "id", new ArrayList<Map<String, String>>() {
			{
				add(new HashMap<String, String>() {
					{
						put("id", "1");
					}
				});
				add(new HashMap<String, String>() {
					{
						put("id", "2");
					}
				});
			}
		});

//		configs.add(new HashMap<String, String>() {
//			{
//				put(RedisJPool.CONFIG_HOST, "192.168.21.32");
//			}
//		});
//		mappings.put("TestInfo", "192.168.21.32");
//
//		agent.refresh(configs, mappings);
//
//		agent.set("TestInfo-2", "TestInfo111");

		agent.close();
//		agent.set("TestInfo-0000001", "TestCpeInfo011");
	}


}