package com.arena.cache.redisson.config;

/**
 * 底层通讯参数配置
 *
 * @author guofazhan
 * @version [版本号, 2019/2/12 0012 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Transport {

	/**
	 * threads（线程池数量）
	 * 默认值: 当前处理核数量 * 2
	 * 这个线程池数量被所有RTopic对象监听器，RRemoteService调用者和RExecutorService任务共同共享。
	 */
	private int threads = 0;
	/**
	 * nettyThreads （Netty线程池数量）
	 * 默认值: 当前处理核数量 * 2
	 * 这个线程池数量是在一个Redisson实例内，被其创建的所有分布式数据类型和服务，以及底层客户端所一同共享的线程池里保存的线程数量。
	 */
	private int nettyThreads = 0;
	/**
	 * codec（编码）
	 * 默认值: org.redisson.codec.JsonJacksonCodec
	 * Redisson的对象编码类是用于将对象进行序列化和反序列化，以实现对该对象在Redis里的读取和存储。Redisson提供了以下几种的对象编码应用，以供大家选择
	 */
	private String codec = "org.redisson.client.codec.StringCodec";

	/**
	 *
	 */
	private boolean referenceEnabled = true;
	/**
	 *
	 */
	private boolean useLinuxNativeEpoll = false;
	/**
	 *
	 */
	private long lockWatchdogTimeout = 30000L;
	/**
	 *
	 */
	private boolean keepPubSubOrder = true;

	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public int getNettyThreads() {
		return nettyThreads;
	}

	public void setNettyThreads(int nettyThreads) {
		this.nettyThreads = nettyThreads;
	}

	public String getCodec() {
		return codec;
	}

	public void setCodec(String codec) {
		this.codec = codec;
	}

	public boolean isReferenceEnabled() {
		return referenceEnabled;
	}

	public void setReferenceEnabled(boolean referenceEnabled) {
		this.referenceEnabled = referenceEnabled;
	}

	public boolean isUseLinuxNativeEpoll() {
		return useLinuxNativeEpoll;
	}

	public void setUseLinuxNativeEpoll(boolean useLinuxNativeEpoll) {
		this.useLinuxNativeEpoll = useLinuxNativeEpoll;
	}

	public long getLockWatchdogTimeout() {
		return lockWatchdogTimeout;
	}

	public void setLockWatchdogTimeout(long lockWatchdogTimeout) {
		this.lockWatchdogTimeout = lockWatchdogTimeout;
	}

	public boolean isKeepPubSubOrder() {
		return keepPubSubOrder;
	}

	public void setKeepPubSubOrder(boolean keepPubSubOrder) {
		this.keepPubSubOrder = keepPubSubOrder;
	}

	@Override
	public String toString() {
		return "Transport{" + "threads=" + threads + ", nettyThreads=" + nettyThreads + ", codec='" + codec + '\''
				+ ", referenceEnabled=" + referenceEnabled + ", useLinuxNativeEpoll=" + useLinuxNativeEpoll
				+ ", lockWatchdogTimeout=" + lockWatchdogTimeout + ", keepPubSubOrder=" + keepPubSubOrder + '}';
	}
}
