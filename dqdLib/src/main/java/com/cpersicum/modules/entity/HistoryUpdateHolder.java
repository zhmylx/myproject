package com.cpersicum.modules.entity;

import com.cpersicum.modules.utils.security.SystemUserInfo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class HistoryUpdateHolder {
	public static void chenEntity(Object o) {
		if (o instanceof SingleCreateEntity) {
			SingleCreateEntity entity = (SingleCreateEntity) o;
			if (StringUtils.isEmpty(entity.getId())) {
				entity.setCreateAcct(SystemUserInfo.getloingName());
				entity.setCrtdatetime(new Date());
				entity.setUpdateAcct(SystemUserInfo.getloingName());
				entity.setUptdatetime(new Date());
			} else {
				entity.setUpdateAcct(SystemUserInfo.getloingName());
				entity.setUptdatetime(new Date());
			}
		}
	}
}
