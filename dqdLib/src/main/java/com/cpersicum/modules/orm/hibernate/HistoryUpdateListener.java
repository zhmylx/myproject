package com.cpersicum.modules.orm.hibernate;

import com.cpersicum.modules.entity.SingleCreateEntity;
import com.cpersicum.modules.utils.security.SystemUserInfo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.def.DefaultSaveOrUpdateEventListener;

public class HistoryUpdateListener extends DefaultSaveOrUpdateEventListener {
	public void onSaveOrUpdate(SaveOrUpdateEvent event) {
		chenEntity(event.getObject());
		super.onSaveOrUpdate(event);
	}

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

