package com.cpersicum.modules.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class IdEntity extends BaseEntity implements Serializable {
	protected String id;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean equals(Object obj) {
		if (obj instanceof IdEntity) {
			IdEntity entity = (IdEntity) obj;
			return entity.getId().equals(this.id);
		}
		return super.equals(obj);
	}

	public int hashCode() {
		return (this.id.hashCode() * 17);
	}
}

