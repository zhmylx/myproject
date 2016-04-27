package ins.framework.cache.info;

public class CacheManagerInfo {
	private String cacheName;
	private int cacheType;
	private String cacheImplClass;
	private int statisticsAccuracy;
	private long cacheHits;
	private long onDiskHits;
	private long inMemoryHits;
	private long cacheMisses;
	private long size;
	private long memoryStoreSize;
	private long diskStoreSize;
	private float averageGetTime;
	private long sizeInByte;
	private long evictionCount;

	public long getSizeInByte() {
		return this.sizeInByte;
	}

	public void setSizeInByte(long sizeInByte) {
		this.sizeInByte = sizeInByte;
	}

	public int getStatisticsAccuracy() {
		return this.statisticsAccuracy;
	}

	public void setStatisticsAccuracy(int statisticsAccuracy) {
		this.statisticsAccuracy = statisticsAccuracy;
	}

	public long getCacheHits() {
		return this.cacheHits;
	}

	public void setCacheHits(long cacheHits) {
		this.cacheHits = cacheHits;
	}

	public long getOnDiskHits() {
		return this.onDiskHits;
	}

	public void setOnDiskHits(long onDiskHits) {
		this.onDiskHits = onDiskHits;
	}

	public long getInMemoryHits() {
		return this.inMemoryHits;
	}

	public void setInMemoryHits(long inMemoryHits) {
		this.inMemoryHits = inMemoryHits;
	}

	public long getCacheMisses() {
		return this.cacheMisses;
	}

	public void setCacheMisses(long cacheMisses) {
		this.cacheMisses = cacheMisses;
	}

	public long getSize() {
		return this.size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getMemoryStoreSize() {
		return this.memoryStoreSize;
	}

	public void setMemoryStoreSize(long memoryStoreSize) {
		this.memoryStoreSize = memoryStoreSize;
	}

	public long getDiskStoreSize() {
		return this.diskStoreSize;
	}

	public void setDiskStoreSize(long diskStoreSize) {
		this.diskStoreSize = diskStoreSize;
	}

	public float getAverageGetTime() {
		return this.averageGetTime;
	}

	public void setAverageGetTime(float averageGetTime) {
		this.averageGetTime = averageGetTime;
	}

	public long getEvictionCount() {
		return this.evictionCount;
	}

	public void setEvictionCount(long evictionCount) {
		this.evictionCount = evictionCount;
	}

	public String getCacheName() {
		return this.cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public int getCacheType() {
		return this.cacheType;
	}

	public void setCacheType(int cacheType) {
		this.cacheType = cacheType;
	}

	public String getCacheImplClass() {
		return this.cacheImplClass;
	}

	public void setCacheImplClass(String cacheImplClass) {
		this.cacheImplClass = cacheImplClass;
	}

	public String toString() {
		return "CacheManagerInfo [cacheName=" + this.cacheName + ", cacheType="
				+ this.cacheType + ", averageGetTime=" + this.averageGetTime
				+ ", cacheHits=" + this.cacheHits + ", cacheImplClass="
				+ this.cacheImplClass + ", cacheMisses=" + this.cacheMisses
				+ ", diskStoreSize=" + this.diskStoreSize + ", evictionCount="
				+ this.evictionCount + ", inMemoryHits=" + this.inMemoryHits
				+ ", memoryStoreSize=" + this.memoryStoreSize + ", onDiskHits="
				+ this.onDiskHits + ", size=" + this.size
				+ ", statisticsAccuracy=" + this.statisticsAccuracy + "]";
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.cache.info.CacheManagerInfo JD-Core Version:
 * 0.5.4
 */