package com.arena.cache;

/**
 * 命令返回结果
 *
 * @author guofazhan
 * @version [版本号, 2019/2/12 0012 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public final class R<T> {

	/**
	 * 返回结果类型枚举
	 */
	public enum RType {SUCCESS, FAIL}

	/**
	 * 返回类型
	 */
	private RType type;

	/**
	 * 返回数据
	 */
	private T data;

	/**
	 * 异常信息
	 */
	private Exception exc;

	private R() {
	}

	/**
	 * 构建成功命令返回结果
	 *
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> R<T> ok(T data) {
		R<T> r = new R<>();
		return r.setType(RType.SUCCESS).setData(data);
	}

	/**
	 * 构建失败命令返回结果
	 *
	 * @param exc
	 * @param <T>
	 * @return
	 */
	public static <T> R<T> error(Exception exc) {
		R<T> r = new R<>();
		return r.setType(RType.FAIL).setExc(exc);
	}

	public RType getType() {
		return type;
	}

	public T getData() {
		return data;
	}

	public Exception getExc() {
		return exc;
	}

	protected R<T> setType(RType type) {
		this.type = type;
		return this;
	}

	protected R<T> setData(T data) {
		this.data = data;
		return this;
	}

	protected R<T> setExc(Exception exc) {
		this.exc = exc;
		return this;
	}
}
