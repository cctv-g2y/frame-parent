package com.arena.cache.commands;

import com.arena.cache.R;

/**
 * KEY 相关的命令操作接口
 *
 * @author guofazhan
 * @version [版本号, 2019/2/12 0012 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface KeyCommands extends Commands {

	/**
	 * 检查给定 key 是否存在
	 * 时间复杂度：O(1)
	 * 返回值：若 key 存在，返回 1 ，否则返回 0
	 *
	 * @param key
	 * @return
	 */
	R<Boolean> exists(String key);

	/**
	 * 删除给定的一个或多个 key,不存在的 key 会被忽略
	 * 时间复杂度：O(N)， N 为被删除的 key 的数量
	 * 返回值：被删除 key 的数量
	 *
	 * @param keys
	 */
	R<Long> del(String... keys);
}
