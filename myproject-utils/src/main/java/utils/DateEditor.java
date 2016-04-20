package utils;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  时间转换器  可以用于spring mvc 的controler中 
 * @author Homing Tsang
 *
 * 2015年11月3日
 */
public class DateEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.length() == 0) {
			// setValue(new Date());
			setValue(null);
		} else {
			try {
				if (text.length() == 10) {
					setValue(new SimpleDateFormat("yyyy-MM-dd").parse(text));
				} else if (text.length() == 13) {
					setValue(new Date(Long.parseLong(text)));
				} else if (text.length() == 16) {
					setValue(new SimpleDateFormat("yy-MM-dd HH:mm").parseObject(text));
				} else if (text.length() == 19) {
					setValue(new SimpleDateFormat("yy-MM-dd HH:mm:ss").parseObject(text));
				} else {
					throw new IllegalArgumentException("转换日期失败: 日期长度不符合要求!");
				}
			} catch (Exception ex) {
				throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);
			}
		}
	}

}
