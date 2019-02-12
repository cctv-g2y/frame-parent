package com.makun.config.fastdfs;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @说明:fastdfs配置
 * @author makun
 */
@Configuration
@ConfigurationProperties(prefix = "fileupload.FastDFS")
public class FastDFSProperties {
	private Set<String> trackers;
	private int soTimeout;
	private int connectTimeout;
	private int maxTotal;
	private int maxTotalPerKey;
	private int maxIdlePerKey;

	public Set<String> getTrackers() {
		return trackers;
	}

	public void setTrackers(Set<String> trackers) {
		this.trackers = trackers;
	}

	public int getSoTimeout() {
		return soTimeout;
	}

	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public int getMaxTotalPerKey() {
		return maxTotalPerKey;
	}

	public void setMaxTotalPerKey(int maxTotalPerKey) {
		this.maxTotalPerKey = maxTotalPerKey;
	}

	public int getMaxIdlePerKey() {
		return maxIdlePerKey;
	}

	public void setMaxIdlePerKey(int maxIdlePerKey) {
		this.maxIdlePerKey = maxIdlePerKey;
	}
}
