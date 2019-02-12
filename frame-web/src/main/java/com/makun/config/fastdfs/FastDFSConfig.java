package com.makun.config.fastdfs;

import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.cleverframe.fastdfs.client.DefaultStorageClient;
import org.cleverframe.fastdfs.client.DefaultTrackerClient;
import org.cleverframe.fastdfs.conn.DefaultCommandExecutor;
import org.cleverframe.fastdfs.pool.ConnectionPool;
import org.cleverframe.fastdfs.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @说明：FastDFS配置
 * @author makun
 *
 */
@Configuration
@EnableConfigurationProperties(FastDFSProperties.class)
public class FastDFSConfig {

    @Autowired
    private FastDFSProperties fastDFSProperties;

    @Bean(name = "fastDfsConnectionPool", destroyMethod = "close")
    public ConnectionPool connectionPool() {
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(fastDFSProperties.getSoTimeout(),
                fastDFSProperties.getConnectTimeout());
        GenericKeyedObjectPoolConfig conf = new GenericKeyedObjectPoolConfig();
        conf.setMaxTotal(fastDFSProperties.getMaxTotal());
        conf.setMaxTotalPerKey(fastDFSProperties.getMaxTotalPerKey());
        conf.setMaxIdlePerKey(fastDFSProperties.getMaxIdlePerKey());
        return new ConnectionPool(pooledConnectionFactory, conf);
    }

    @Bean("fastDfsCommandExecutor")
    public DefaultCommandExecutor defaultCommandExecutor(ConnectionPool fastDfsConnectionPool) {
        Set<String> trackerSet = fastDFSProperties.getTrackers();
        return new DefaultCommandExecutor(trackerSet, fastDfsConnectionPool);
    }

    @Bean("fastDfsTrackerClient")
    public DefaultTrackerClient defaultTrackerClient(DefaultCommandExecutor commandExecutor) {
        return new DefaultTrackerClient(commandExecutor);
    }

    @Bean("fastDfsStorageClient")
    public DefaultStorageClient fastDfsStorageClient(DefaultCommandExecutor commandExecutor,
            DefaultTrackerClient trackerClient) {
        return new DefaultStorageClient(commandExecutor, trackerClient);
    }

}
