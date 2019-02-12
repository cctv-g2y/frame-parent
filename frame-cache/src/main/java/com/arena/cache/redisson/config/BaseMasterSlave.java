package com.arena.cache.redisson.config;

/**
 * 主从模式基础配置
 *
 * @author guofazhan
 * @version [版本号, 2019/2/12 0012 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BaseMasterSlave extends BaseOptions {

	/**
	 * slaveConnectionMinimumIdleSize（从节点最小空闲连接数）
	 * 默认值：32
	 * 多从节点的环境里，每个 从服务节点里用于普通操作（非 发布和订阅）的最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时读取反映速度。
	 */
	private int slaveConnectionMinimumIdleSize = 10;
	/**
	 * slaveConnectionPoolSize（从节点连接池大小）
	 * 默认值：64
	 * 多从节点的环境里，每个 从服务节点里用于普通操作（非 发布和订阅）连接的连接池最大容量。连接池的连接数量自动弹性伸缩
	 */
	private int slaveConnectionPoolSize = 64;
	/**
	 * masterConnectionMinimumIdleSize（主节点最小空闲连接数）
	 * 默认值：32
	 * 多从节点的环境里，每个 主节点的最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时写入反应速度。
	 */
	private int masterConnectionMinimumIdleSize = 10;
	/**
	 * masterConnectionPoolSize（主节点连接池大小）
	 * 默认值：64
	 * 主节点的连接池最大容量。连接池的连接数量自动弹性伸缩。
	 */
	private int masterConnectionPoolSize = 64;
	/**
	 * readMode（读取操作的负载均衡模式）
	 * <p>
	 * 默认值： SLAVE（只在从服务节点里读取）
	 * 注：在从服务节点里读取的数据说明已经至少有两个节点保存了该数据，确保了数据的高可用性。
	 * 设置读取操作选择节点的模式。 可用值为： SLAVE - 只在从服务节点里读取。 MASTER - 只在主服务节点里读取。 MASTER_SLAVE - 在主从服务节点里都可以读取
	 */
	private String readMode;
	/**
	 * subscriptionMode（订阅操作的负载均衡模式）
	 * 默认值：SLAVE（只在从服务节点里订阅）
	 * 设置订阅操作选择节点的模式。 可用值为： SLAVE - 只在从服务节点里订阅。 MASTER - 只在主服务节点里订阅。
	 */
	private String subscriptionMode;
	/**
	 * subscriptionConnectionMinimumIdleSize（从节点发布和订阅连接的最小空闲连接数）
	 * 默认值：1
	 * 多从节点的环境里，每个 从服务节点里用于发布和订阅连接的最小保持连接数（长连接）。Redisson内部经常通过发布和订阅来实现许多功能。长期保持一定数量的发布订阅连接是必须的。
	 */
	private int subscriptionConnectionMinimumIdleSize;
	/**
	 * subscriptionConnectionPoolSize（从节点发布和订阅连接池大小）
	 * 默认值：50
	 * 多从节点的环境里，每个 从服务节点里用于发布和订阅连接的连接池最大容量。连接池的连接数量自动弹性伸缩。
	 */
	private int subscriptionConnectionPoolSize;
	/**
	 * dnsMonitoringInterval（DNS监控间隔，单位：毫秒）
	 * 默认值：5000
	 * 用来指定检查节点DNS变化的时间间隔。使用的时候应该确保JVM里的DNS数据的缓存时间保持在足够低的范围才有意义。用-1来禁用该功能。
	 */
	private long dnsMonitoringInterval;

	public int getSlaveConnectionMinimumIdleSize() {
		return slaveConnectionMinimumIdleSize;
	}

	public void setSlaveConnectionMinimumIdleSize(int slaveConnectionMinimumIdleSize) {
		this.slaveConnectionMinimumIdleSize = slaveConnectionMinimumIdleSize;
	}

	public int getSlaveConnectionPoolSize() {
		return slaveConnectionPoolSize;
	}

	public void setSlaveConnectionPoolSize(int slaveConnectionPoolSize) {
		this.slaveConnectionPoolSize = slaveConnectionPoolSize;
	}

	public int getMasterConnectionMinimumIdleSize() {
		return masterConnectionMinimumIdleSize;
	}

	public void setMasterConnectionMinimumIdleSize(int masterConnectionMinimumIdleSize) {
		this.masterConnectionMinimumIdleSize = masterConnectionMinimumIdleSize;
	}

	public int getMasterConnectionPoolSize() {
		return masterConnectionPoolSize;
	}

	public void setMasterConnectionPoolSize(int masterConnectionPoolSize) {
		this.masterConnectionPoolSize = masterConnectionPoolSize;
	}

	public String getReadMode() {
		return readMode;
	}

	public void setReadMode(String readMode) {
		this.readMode = readMode;
	}

	public String getSubscriptionMode() {
		return subscriptionMode;
	}

	public void setSubscriptionMode(String subscriptionMode) {
		this.subscriptionMode = subscriptionMode;
	}

	public int getSubscriptionConnectionMinimumIdleSize() {
		return subscriptionConnectionMinimumIdleSize;
	}

	public void setSubscriptionConnectionMinimumIdleSize(int subscriptionConnectionMinimumIdleSize) {
		this.subscriptionConnectionMinimumIdleSize = subscriptionConnectionMinimumIdleSize;
	}

	public int getSubscriptionConnectionPoolSize() {
		return subscriptionConnectionPoolSize;
	}

	public void setSubscriptionConnectionPoolSize(int subscriptionConnectionPoolSize) {
		this.subscriptionConnectionPoolSize = subscriptionConnectionPoolSize;
	}

	public long getDnsMonitoringInterval() {
		return dnsMonitoringInterval;
	}

	public void setDnsMonitoringInterval(long dnsMonitoringInterval) {
		this.dnsMonitoringInterval = dnsMonitoringInterval;
	}

	@Override
	public String toString() {
		return "BaseMasterSlave{" + "slaveConnectionMinimumIdleSize=" + slaveConnectionMinimumIdleSize
				+ ", slaveConnectionPoolSize=" + slaveConnectionPoolSize + ", masterConnectionMinimumIdleSize="
				+ masterConnectionMinimumIdleSize + ", masterConnectionPoolSize=" + masterConnectionPoolSize
				+ ", readMode='" + readMode + '\'' + ", subscriptionMode='" + subscriptionMode + '\''
				+ ", subscriptionConnectionMinimumIdleSize=" + subscriptionConnectionMinimumIdleSize
				+ ", subscriptionConnectionPoolSize=" + subscriptionConnectionPoolSize + ", dnsMonitoringInterval="
				+ dnsMonitoringInterval + "} " + super.toString();
	}
}
