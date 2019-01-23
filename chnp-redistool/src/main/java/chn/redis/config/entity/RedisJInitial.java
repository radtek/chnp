package chn.redis.config.entity;

import redis.clients.jedis.JedisPoolConfig;

public class RedisJInitial extends JedisPoolConfig {

	private String host = "localhost";
	private Integer port = 6379;
	private Integer protocolTimeout = 2000;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		if (null != host && !"".equals(host.trim())) {
			this.host = host;
		}
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(String port) {
		if (null != port && !"".equals(port.trim())) {
			this.port = Integer.valueOf(port);
		}
	}

	public Integer getProtocolTimeout() {
		return protocolTimeout;
	}

	public void setProtocolTimeout(String protocolTimeout) {
		if (null != protocolTimeout && !"".equals(protocolTimeout.trim())) {
			this.protocolTimeout = Integer.valueOf(protocolTimeout.trim());
		}
	}

	public void setMaxTotal(String maxTotal) {
		if (null != maxTotal && !"".equals(maxTotal.trim())) {
			super.setMaxTotal(Integer.valueOf(maxTotal));
		}
	}

	public void setMaxIdle(String maxIdle) {
		if (null != maxIdle && !"".equals(maxIdle.trim())) {
			super.setMaxIdle(Integer.valueOf(maxIdle));
		}
	}

	public void setMinIdle(String minIdle) {
		if (null != minIdle && !"".equals(minIdle.trim())) {
			super.setMinIdle(Integer.valueOf(minIdle));
		}
	}

	public void setBlockWhenExhausted(String waitWhenExhausted) {
		if (null != waitWhenExhausted && "0".equals(waitWhenExhausted.trim())) {
			super.setBlockWhenExhausted(false);
		}
	}

	public void setMaxWaitMillis(String maxWaitMillis) {
		if (null != maxWaitMillis && !"".equals(maxWaitMillis.trim())) {
			super.setMaxWaitMillis(Long.valueOf(maxWaitMillis));
		}
	}

	public void setTestOnBorrow(String testOnBorrow) {
		if (null != testOnBorrow && "1".equals(testOnBorrow.trim())) {
			super.setTestOnBorrow(true);
		}
	}

	public void setTestOnReturn(String testOnReturn) {
		if (null != testOnReturn && "1".equals(testOnReturn.trim())) {
			super.setTestOnReturn(true);
		}
	}
}