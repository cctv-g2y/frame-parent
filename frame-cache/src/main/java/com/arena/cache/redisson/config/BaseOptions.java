package com.arena.cache.redisson.config;

/**
 * 公共配置信息
 *
 * @author guofazhan
 * @version [版本号, 2019/2/12 0012 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BaseOptions extends Transport {

	/**
	 * idleConnectionTimeout（连接空闲超时，单位：毫秒）
	 * 默认值：10000
	 * 如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒。
	 */
	private int idleConnectionTimeout = 10000;
	/**
	 *
	 */
	private int pingTimeout = 1000;
	/**
	 * connectTimeout（连接超时，单位：毫秒）
	 * 默认值：10000
	 * 同任何节点建立连接时的等待超时。时间单位是毫秒
	 */
	private int connectTimeout = 10000;
	/**
	 * timeout（命令等待超时，单位：毫秒）
	 * 默认值：3000
	 * 等待节点回复命令的时间。该时间从命令发送成功时开始计时
	 */
	private int timeout = 3000;
	/**
	 * retryAttempts（命令失败重试次数）
	 * 默认值：3
	 * 如果尝试达到 retryAttempts（命令失败重试次数） 仍然不能将命令发送至某个指定的节点时，将抛出错误。如果尝试在此限制之内发送成功，则开始启用 timeout（命令等待超时） 计时。
	 */
	private int retryAttempts = 3;
	/**
	 * retryInterval（命令重试发送时间间隔，单位：毫秒）
	 * 默认值：1500
	 * 在一条命令发送失败以后，等待重试发送的时间间隔。时间单位是毫秒。
	 */
	private int retryInterval = 1500;
	/**
	 * reconnectionTimeout（重新连接时间间隔，单位：毫秒）
	 * 默认值：3000
	 * 当与某个节点的连接断开时，等待与其重新建立连接的时间间隔。时间单位是毫秒
	 */
	private int reconnectionTimeout = 3000;
	/**
	 * failedAttempts（执行失败最大次数）
	 * 默认值：3
	 * 在某个节点执行相同或不同命令时，连续 失败 failedAttempts（执行失败最大次数） 时，该节点将被从可用节点列表里清除，直到 reconnectionTimeout（重新连接时间间隔） 超时以后再次尝试
	 */
	private int failedAttempts = 3;
	/**
	 * 用于节点身份验证的密码
	 * 默认值：null
	 */
	private String password;
	/**
	 * subscriptionsPerConnection（单个连接最大订阅数量）
	 * 默认值：5
	 * 每个连接的最大订阅数量
	 */
	private int subscriptionsPerConnection = 5;
	/**
	 * clientName（客户端名称）
	 * 默认值：null
	 */
	private String clientName;

	public int getIdleConnectionTimeout() {
		return idleConnectionTimeout;
	}

	public void setIdleConnectionTimeout(int idleConnectionTimeout) {
		this.idleConnectionTimeout = idleConnectionTimeout;
	}

	public int getPingTimeout() {
		return pingTimeout;
	}

	public void setPingTimeout(int pingTimeout) {
		this.pingTimeout = pingTimeout;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getRetryAttempts() {
		return retryAttempts;
	}

	public void setRetryAttempts(int retryAttempts) {
		this.retryAttempts = retryAttempts;
	}

	public int getRetryInterval() {
		return retryInterval;
	}

	public void setRetryInterval(int retryInterval) {
		this.retryInterval = retryInterval;
	}

	public int getReconnectionTimeout() {
		return reconnectionTimeout;
	}

	public void setReconnectionTimeout(int reconnectionTimeout) {
		this.reconnectionTimeout = reconnectionTimeout;
	}

	public int getFailedAttempts() {
		return failedAttempts;
	}

	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSubscriptionsPerConnection() {
		return subscriptionsPerConnection;
	}

	public void setSubscriptionsPerConnection(int subscriptionsPerConnection) {
		this.subscriptionsPerConnection = subscriptionsPerConnection;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Override
	public String toString() {
		return "BaseOptions{" + "idleConnectionTimeout=" + idleConnectionTimeout + ", pingTimeout=" + pingTimeout
				+ ", connectTimeout=" + connectTimeout + ", timeout=" + timeout + ", retryAttempts=" + retryAttempts
				+ ", retryInterval=" + retryInterval + ", reconnectionTimeout=" + reconnectionTimeout
				+ ", failedAttempts=" + failedAttempts + ", password='" + password + '\''
				+ ", subscriptionsPerConnection=" + subscriptionsPerConnection + ", clientName='" + clientName + '\''
				+ "} " + super.toString();
	}
}
