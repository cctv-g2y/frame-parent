package com.arena.cache.commands;

import com.arena.cache.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * 抽象的命令操作接口
 *
 * @author guofazhan
 * @version [版本号, 2019/2/12 0012 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class AbstractCommands<T>
		implements HashCommands, KeyCommands, ListCommands, SetCommands, SortedSetCommands, StringCommands,
		RedisClient<T> {
	private static final Logger logger = LoggerFactory.getLogger(AbstractCommands.class);
	/**
	 * 实现的客户端
	 */
	private T client;

	public AbstractCommands(T client) {
		this.client = client;
	}

	@Override
	public <M extends AbstractCommands> M getCommands() {
		return (M) this;
	}

	@Override
	public T getRealClient() {
		return client;
	}

	/**
	 * 通过真实客户端执行命令
	 *
	 * @param supplier
	 * @return
	 */
	protected <T> R<T> call(Supplier<R<T>> supplier) {
		long s = System.currentTimeMillis();
		R<T> r;
		try {
			r = supplier.get();
			if (logger.isDebugEnabled()) {
				logger.debug("Execute Redis Command R:{}", r);
			}
		} catch (Exception e) {
			logger.error("Execute Redis Command Error:", e);
			r = R.error(e);
		} finally {
			// 释放资源
			close();
			if (logger.isDebugEnabled()) {
				logger.debug("Execute call method use time:{}", System.currentTimeMillis() - s);
			}
		}

		return r;
	}
}
