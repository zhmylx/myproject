package ins.framework.time;

import java.io.PrintStream;

public class Watch {
	WatchMeta start = new WatchMeta();

	public static Watch start() {
		return new Watch();
	}

	public long count() {
		WatchMeta count = new WatchMeta();
		return count.milliseconds - this.start.milliseconds;
	}

	public void printCount() {
		long l = count();
		StringBuilder sb = new StringBuilder();
		sb.append("Watch:");
		sb.append(l);
		sb.append("ms");
		sb.append("\r\n");
		System.out.print(sb);
	}

	public long reset() {
		WatchMeta reset = new WatchMeta();
		long d = reset.milliseconds - this.start.milliseconds;
		this.start = reset;
		return d;
	}

	public void printReset() {
		long l = reset();
		StringBuilder sb = new StringBuilder();
		sb.append("Watch:");
		sb.append(l);
		sb.append("ms");
		sb.append("\r\n");
		System.out.print(sb);
		reset();
	}

	class WatchMeta {
		long milliseconds = System.currentTimeMillis();

		WatchMeta() {
		}
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.time.Watch JD-Core Version: 0.5.4
 */