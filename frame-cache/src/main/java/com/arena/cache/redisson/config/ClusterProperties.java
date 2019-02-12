package com.arena.cache.redisson.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 集群模式配置
 *
 * @author guofazhan
 * @version [版本号, 2019/2/12 0012 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ClusterProperties extends BaseMasterSlave implements Validator {

	/**
	 * 节点地址
	 */
	private List<String> nodeAddresses = new ArrayList();

	/**
	 * scanInterval（主节点变化扫描间隔时间）
	 * 默认值： 1000
	 * 对主节点变化节点状态扫描的时间间隔。单位是毫秒。
	 */
	private int scanInterval = 1000;

	public List<String> getNodeAddresses() {
		return nodeAddresses;
	}

	public void setNodeAddresses(List<String> nodeAddresses) {
		this.nodeAddresses = nodeAddresses;
	}

	public int getScanInterval() {
		return scanInterval;
	}

	public void setScanInterval(int scanInterval) {
		this.scanInterval = scanInterval;
	}

	@Override
	public String toString() {
		return "ClusterProperties{" + "nodeAddresses=" + nodeAddresses + ", scanInterval=" + scanInterval + "} " + super
				.toString();
	}

	@Override
	public boolean verify() {
		return false;
	}
}
