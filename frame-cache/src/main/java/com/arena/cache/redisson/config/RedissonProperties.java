package com.arena.cache.redisson.config;

/**
 * @author guofazhan
 * @version [版本号, 2019/2/12 0012 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RedissonProperties implements Validator {

	/**
	 * 源名称
	 */
	private String sourceName;

	/**
	 * 源的组成方式
	 */
	private String sourceMode;

	/**
	 * cluster 配置信息
	 */
	private ClusterProperties cluster;

	/**
	 * sentinel 配置信息
	 */
	private SentinelProperties sentinel;

	@Override
	public boolean verify() {
		return false;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceMode() {
		return sourceMode;
	}

	public void setSourceMode(String sourceMode) {
		this.sourceMode = sourceMode;
	}

	public ClusterProperties getCluster() {
		return cluster;
	}

	public void setCluster(ClusterProperties cluster) {
		this.cluster = cluster;
	}

	public SentinelProperties getSentinel() {
		return sentinel;
	}

	public void setSentinel(SentinelProperties sentinel) {
		this.sentinel = sentinel;
	}

	@Override
	public String toString() {
		return "RedissonProperties{" + "sourceName='" + sourceName + '\'' + ", sourceMode='" + sourceMode + '\''
				+ ", cluster=" + cluster + ", sentinel=" + sentinel + '}';
	}
}
