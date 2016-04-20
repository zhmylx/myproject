package base;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
/**
 * 
 * @author Homing Tsang
 *
 * 2015年11月3日
 */
@Service
public class MongoBaseService {

	/**
	 * mongodb的操作对象，处理所有对mongodb的增删改查操作 
	 */
	private MongoTemplate mongoTemplate;
	
	/**
	 * mongodb  集合 名
	 */
	private String collectionname;
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public String getCollectionname() {
		return collectionname;
	}

	public void setCollectionname(String collectionname) {
		this.collectionname = collectionname;
	}
	

	/**
	 * 添加数据 
	 * @param t
	 */
	public <T> void InsertOrUpdate(T t){
		mongoTemplate.save(t,collectionname);
	}
	
	/**
	 * 查询单个对象
	 * @param fieldName
	 * @param fieldValue
	 * @param entityClass
	 * @return
	 */
	public <T> T findOne(String fieldName ,String fieldValue,Class<T> entityClass){
		return mongoTemplate.findOne(buildQueryConditions(fieldName,fieldValue), entityClass,collectionname);
	}
	
	
	
	/**
	 * 构建查询条件
	 * @param fieldName 查询字段名
	 * @param fieldValue 查询值
	 * @return
	 */
	protected  Query buildQueryConditions(String fieldName ,String fieldValue){
		Criteria criteria = Criteria.where(fieldName).is(fieldValue);
		return (new Query(criteria));
	}
	
}
