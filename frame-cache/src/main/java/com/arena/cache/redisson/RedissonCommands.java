package com.arena.cache.redisson;

import com.arena.cache.commands.AbstractCommands;
import com.arena.cache.R;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * redisson实现redis命令
 *
 * @author guofazhan
 * @version [版本号, 2019/2/12 0012 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RedissonCommands extends AbstractCommands<RedissonClient> {

	private static final Logger logger = LoggerFactory.getLogger(RedissonCommands.class);

	public RedissonCommands(RedissonClient client) {
		super(client);
	}

	/**
	 * RObject.isExists(), RObject.isExistsAsync(), RObjectReactive.isExists();
	 *
	 * @param key
	 * @return
	 */
	@Override
	public R<Boolean> exists(String key) {
		return call(() -> {
			RKeys rKeys = getRealClient().getKeys();
			return R.ok(rKeys.countExists(key) == 1);
		});
	}

	/**
	 * RObject.delete(), RObject.deleteAsync(), RObjectReactive.delete();
	 * RKeys.delete(), RKeys.deleteAsync();
	 *
	 * @param keys
	 * @return
	 */
	@Override
	public R<Long> del(String... keys) {
		return call(() -> {
			RKeys rKeys = getRealClient().getKeys();
			return R.ok(rKeys.delete(keys));
		});
	}

	@Override
	public void close() {
		//redisson no close
	}
}
