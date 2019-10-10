package com.imooc.o2o.service;

import java.util.List;

import com.imooc.o2o.entity.Area;

public interface AreaService {
	public static final String AREALISTKEY = "arealist";
	/**
	 * 获取区域列表，有限从缓存获取
	 * @return
	 */
	List<Area> getAreaList();
}
