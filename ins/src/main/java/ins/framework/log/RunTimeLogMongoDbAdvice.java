package ins.framework.log;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RunTimeLogMongoDbAdvice implements MethodInterceptor {
	private static final Log logger = LogFactory
			.getLog(RunTimeLogMongoDbAdvice.class);

	private static final Log loggerBrief = LogFactory
			.getLog("ins.framework.log.RunTimeLogMongoDbAdviceBrief");

	private static Queue<MongoObjectDto> objectDtoQueue = new LinkedList();
	private long minTime;
	private long threadCount;
	private List<ServerAddress> mongoDbServers;
	private String dbName;
	private String dbUser;
	private String dbPassword;
	private boolean enableMongoDb;
	private boolean enableLog4j;
	private DB mainDb = null;
	private boolean mongoDbInited = false;

	private boolean sync = true;

	public void setEnableLog4j(boolean enableLog4j) {
		this.enableLog4j = enableLog4j;
	}

	public void setEnableMongoDb(boolean enableMongoDb) {
		this.enableMongoDb = enableMongoDb;
	}

	public RunTimeLogMongoDbAdvice() {
		this.minTime = 100L;
	}

	public static void setObjectDtoQueue(Queue<MongoObjectDto> objectDtoQueue) {
		objectDtoQueue = objectDtoQueue;
	}

	public void setMainDb(DB mainDb) {
		this.mainDb = mainDb;
	}

	public void setMongoDbInited(boolean mongoDbInited) {
		this.mongoDbInited = mongoDbInited;
	}

	public void setMinTime(long minTime) {
		this.minTime = minTime;
	}

	public void setSync(boolean sync) {
		this.sync = sync;
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = invocation.proceed();
		long time = System.currentTimeMillis() - start;
		if (time <= this.minTime) {
			return result;
		}

		if (this.enableLog4j) {
			StringBuilder builder = new StringBuilder();
			builder.append("ElapsedTime=");
			builder.append(time).append(" Method=");
			builder.append(methodToString(invocation.getMethod()));
			if (loggerBrief.isInfoEnabled()) {
				loggerBrief.info(builder.toString());
			}
			if (logger.isInfoEnabled()) {
				if (logger.isDebugEnabled()) {
					builder.append(" Args={");
					Object[] args = invocation.getArguments();
					if (args != null) {
						for (int i = 0; i < args.length; ++i) {
							Object arg = args[i];
							if (arg == null)
								builder.append("null");
							else if ((arg.getClass().isPrimitive())
									|| (arg instanceof Number)
									|| (arg instanceof String)
									|| (arg instanceof Date)
									|| (arg instanceof Boolean)) {
								builder.append(arg);
							} else
								builder.append(ToStringBuilder
										.reflectionToString(arg));

							if (i < args.length - 1) {
								builder.append(',');
							}
						}
					}
					builder.append('}');
					logger.debug(builder.toString());
				} else {
					logger.info(builder.toString());
				}
			}
		}

		if (this.enableMongoDb) {
			setupMongoDb();
			Map resultMap = new HashMap();

			resultMap.put("ElapsedTime", Long.valueOf(time));
			resultMap.put("Method", methodToString(invocation.getMethod()));
			Object[] args = invocation.getArguments();
			List argsName = new ArrayList();
			if (args != null) {
				for (int i = 0; i < args.length; ++i) {
					Object arg = args[i];
					if (arg == null)
						argsName.add(i, "null");
					else if ((arg.getClass().isPrimitive())
							|| (arg instanceof Number)
							|| (arg instanceof String) || (arg instanceof Date)
							|| (arg instanceof Boolean)) {
						argsName.add(i, arg.toString());
					} else
						argsName.add(i, ToStringBuilder.reflectionToString(arg));
				}
			}

			resultMap.put("args", argsName);
			addObject("RunTime", resultMap);
		}
		return result;
	}

	private static String getTypeName(Class<?> type) {
		if (type.isArray()) {
			try {
				Class cl = type;
				int dimensions = 0;
				while (cl.isArray()) {
					++dimensions;
					cl = cl.getComponentType();
				}
				StringBuilder sb = new StringBuilder();
				sb.append(cl.getName());
				for (int i = 0; i < dimensions; ++i) {
					sb.append("[]");
				}
				return sb.toString();
			} catch (Throwable e) {
				logger.warn(e);
			}
		}
		return type.getName();
	}

	private String methodToString(Method method) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(getTypeName(method.getDeclaringClass())).append('.');
			sb.append(method.getName()).append('(');
			Class[] params = method.getParameterTypes();
			for (int j = 0; j < params.length; ++j) {
				sb.append(getTypeName(params[j]));
				if (j < params.length - 1) {
					sb.append(',');
				}
			}
			sb.append(')');
			return sb.toString();
		} catch (Exception e) {
			return "<".concat(e.toString()).concat(">");
		}
	}

	public void setThreadCount(long threadCount) {
		this.threadCount = threadCount;
	}

	public void setMongoDbServers(List<ServerAddress> mongoDbServers) {
		this.mongoDbServers = mongoDbServers;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public void addObject(String collection, Map<String, Object> value) {
		MongoObjectDto mongoObjectDto = new MongoObjectDto();

		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.putAll(value);
		mongoObjectDto.setBasicDBObject(basicDBObject);
		mongoObjectDto.setCollection(collection);

		if (this.sync) {
			if (objectDtoQueue == null) {
				objectDtoQueue = new LinkedList();
			}
			objectDtoQueue.add(mongoObjectDto);
		} else {
			setupMainDb();
			try {
				DBCollection coll = this.mainDb.getCollection(mongoObjectDto
						.getCollection());

				coll.insert(new DBObject[] { mongoObjectDto.getBasicDBObject() });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setupMainDb() {
		if (this.mainDb != null) {
			return;
		}

		Mongo mongo = new Mongo(this.mongoDbServers);
		this.mainDb = mongo.getDB(this.dbName);
		if (!this.mainDb.authenticate(this.dbUser,
				this.dbPassword.toCharArray()))
			System.out.println("MongoDB authenticate failed.");
	}

	public void setupMongoDb() {
		if (this.mongoDbInited) {
			return;
		}

		Mongo mongo = new Mongo(this.mongoDbServers);
		DB db = mongo.getDB(this.dbName);
		if (!db.authenticate(this.dbUser, this.dbPassword.toCharArray())) {
			System.out.println("MongoDB authenticate failed.");
		}
		for (int i = 0; i < this.threadCount; ++i) {
			MongoLogThread thread = new MongoLogThread(db, i);
			thread.start();
		}

		this.mongoDbInited = true;
	}

	class MongoObjectDto {
		private String collection;
		private BasicDBObject basicDBObject;

		MongoObjectDto() {
		}

		public String getCollection() {
			return this.collection;
		}

		public void setCollection(String collection) {
			this.collection = collection;
		}

		public BasicDBObject getBasicDBObject() {
			return this.basicDBObject;
		}

		public void setBasicDBObject(BasicDBObject basicDBObject) {
			this.basicDBObject = basicDBObject;
		}
	}

	class MongoLogThread extends Thread {
		private DB db;

		public MongoLogThread(DB mongoDb, int threadId) {
			this.db = mongoDb;
		}

		public void run() {
			while (true) {
				if (RunTimeLogMongoDbAdvice.objectDtoQueue == null) {
					RunTimeLogMongoDbAdvice.setObjectDtoQueue(new LinkedList());
				}
				if (RunTimeLogMongoDbAdvice.objectDtoQueue.isEmpty()) {
					try {
						Thread.sleep(2000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				RunTimeLogMongoDbAdvice.MongoObjectDto mongoObjectDto = (RunTimeLogMongoDbAdvice.MongoObjectDto) RunTimeLogMongoDbAdvice.objectDtoQueue
						.poll();
				DBCollection coll = this.db.getCollection(mongoObjectDto
						.getCollection());

				coll.insert(new DBObject[] { mongoObjectDto.getBasicDBObject() });
			}
		}
	}
}
