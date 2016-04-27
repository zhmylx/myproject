package base.dao.hibernate;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;

public class MyNamingStrategy extends ImprovedNamingStrategy implements
		NamingStrategy {
	public String columnName(String name) {
		return name;
	}
}
