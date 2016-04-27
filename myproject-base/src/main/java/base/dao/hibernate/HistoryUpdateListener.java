package base.dao.hibernate;


import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.def.DefaultSaveOrUpdateEventListener;

import base.entity.SingleCreateEntity;
import base.entity.SystemUserInfo;

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

