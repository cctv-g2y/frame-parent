package com.arena.cache.redisson.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 哨兵模式配置
 *
 * @author guofazhan
 * @version [版本号, 2019/2/12 0012 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SentinelProperties extends BaseMasterSlave implements Validator {

	/**
	 * 节点地址
	 */
	private List<String> nodeAddresses = new ArrayList();
	/**
	 * masterName（主服务器的名称）
	 * 主服务器的名称是哨兵进程中用来监测主从服务切换情况的。
	 */
	private String masterName;

	private int database = 0;

	@Override
	public boolean verify() {
		return false;
	}

	public List<String> getNodeAddresses() {
		return nodeAddresses;
	}

	public void setNodeAddresses(List<String> nodeAddresses) {
		this.nodeAddresses = nodeAddresses;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	@Override
	public String toString() {
		return "SentinelProperties{" + "nodeAddresses=" + nodeAddresses + ", masterName='" + masterName + '\''
				+ ", database=" + database + "} " + super.toString();
	}
}
