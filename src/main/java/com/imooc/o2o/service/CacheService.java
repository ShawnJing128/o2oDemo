package com.imooc.o2o.service;

public interface CacheService {
	/**
	 * 依据key前缀删除匹配该模式下的所有key-value，如传入 shopcategory，则shopcategory_allfirstlevel等以
	 * shopcategory打头的key_value都会被清空
	 * @param keyPrefix
	 */
	void removeFromCache(String keyPrefix);
}
