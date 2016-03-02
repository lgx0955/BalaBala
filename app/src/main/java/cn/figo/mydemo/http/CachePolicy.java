package cn.figo.mydemo.http;

public enum CachePolicy {

	/**
	 * 不使用缓存
	 */
	POLICY_NOCACHE,

	/**
	 * 只要有缓存就使用缓存 ,不更新缓存
	 */
	POLICY_CACHE_ONLY,

	/**
	 * 只要有缓存就使用缓存 ,同时更新缓存 ,适用于数据后台处理时间长的操作 <br/>
	 * ondo 可能会被调用两次一次是缓存中读数据时,一次是网络数据处理时<br/>
	 * 但inInUI 只会调用一次
	 */
	POLICY_CACHE_AndRefresh,
	
	/**
	 * 当网络访问失败时使用缓存
	 */
	POLICY_ON_NET_ERROR,

	/**
	 * 当网络访问失败时使用缓存<br/>
	 * 这种模式会被会回调两次
	 */
	POLICY_BEFORE_AND_AFTER_NET;
}
