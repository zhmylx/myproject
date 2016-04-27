package com.cpersicum.modules.entity;

import com.cpersicum.modules.utils.DateConvertUtils;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class SingleCreateEntity extends BaseEntity {
	protected String id;
	private Boolean valid;
	private String createAcct;
	private String updateAcct;
	private Date crtdatetime;
	private Date uptdatetime;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		if (StringUtils.isEmpty(this.id)) {
			return null;
		}
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public void setCrtdatetime(String value) {
		setCrtdatetime(DateConvertUtils.parse(value, "yyyy-MM-dd HH:mm:ss",
				Date.class));
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

	public void setUptdatetimeString(String value) {
		setUptdatetime(DateConvertUtils.parse(value, "yyyy-MM-dd HH:mm:ss",
				Date.class));
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof SingleCreateEntity))
			return false;
		if (this == obj)
			return true;
		SingleCreateEntity other = (SingleCreateEntity) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}
}

