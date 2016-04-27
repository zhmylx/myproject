package com.cpersicum.modules.entity;

import com.cpersicum.modules.utils.DateConvertUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Transient;
import org.apache.commons.lang3.time.DateUtils;

public class CreateEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Boolean valid;
	private String createAcct;
	private String updateAcct;
	private Date crtdatetime;
	private Date uptdatetime;

	public Boolean isValid() {
		return this.valid;
	}

	@Column(name = "VALID")
	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	@Column(name = "CREATEACCT")
	public String getCreateAcct() {
		return this.createAcct;
	}

	public void setCreateAcct(String createAcct) {
		this.createAcct = createAcct;
	}

	@Column(name = "UPDATEACCT")
	public String getUpdateAcct() {
		return this.updateAcct;
	}

	public void setUpdateAcct(String updateAcct) {
		this.updateAcct = updateAcct;
	}

	@Column(name = "CRTDATETIME")
	public Date getCrtdatetime() {
		return this.crtdatetime;
	}

	@Transient
	public String getgetCrtdatetimeString() {
		return DateConvertUtils.format(getCrtdatetime(), "yyyy-MM-dd HH:mm:ss");
	}

	public void setCrtdatetime(Date crtdatetime) {
		this.crtdatetime = crtdatetime;
	}

	public void setCrtdatetime(String str) {
		try {
			this.crtdatetime = DateUtils.parseDate(str, new String[0]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Column(name = "UPTDATETIME")
	public Date getUptdatetime() {
		return this.uptdatetime;
	}

	@Transient
	public String getgetUptdatetimeString() {
		return DateConvertUtils.format(getUptdatetime(), "yyyy-MM-dd HH:mm:ss");
	}

	public void setUptdatetime(Date uptdatetime) {
		this.uptdatetime = uptdatetime;
	}

	public void setUptdatetime(String str) {
		try {
			this.uptdatetime = DateUtils.parseDate(str, new String[0]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

/*
 * Location: E:\BaiduYunDownload\cpersicum-core-4.0.0.RC3.jar Qualified Name:
 * com.cpersicum.modules.entity.CreateEntity JD-Core Version: 0.5.3
 */