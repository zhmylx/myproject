package com.cpersicum.modules.utils.mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonMapper {
	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		return JSON.parseObject(jsonString, clazz);
	}

	public static JSONArray toJSONArray(String arrayString) {
		JSONArray array = JSON.parseArray(arrayString);
		return array;
	}

	public static String toJson(Object object) {
		return JSON.toJSONString(object);
	}
}
