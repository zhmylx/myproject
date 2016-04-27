package ins.framework.io;

import ins.framework.Lang;
import ins.framework.log.Logs;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public abstract class FilePool {
	private static final String fileNamesuffix = ".poolfile";
	private static FileFilter fileFilter = new FileFilter() {
		public boolean accept(File pathname) {
			return (pathname != null) && (pathname.isFile())
					&& (pathname.getName().endsWith(".poolfile"));
		}
	};
	File baseDir;
	int maxFiles = -1;

	public static FilePool newHashFilePool(File dir, int max) {
		return new FilePool(dir, max) {
			protected String generateKey(String key) {
				return String.valueOf(10000000000L + key.hashCode());
			}
		};
	}

	public static FilePool newHashFilePool(File dir) {
		return newHashFilePool(dir, -1);
	}

	public int getMaxFiles() {
		return this.maxFiles;
	}

	public void setMaxFiles(int maxFiles) {
		this.maxFiles = maxFiles;
	}

	public File getBaseDir() {
		return this.baseDir;
	}

	private FilePool(File dir, int max) {
		this.baseDir = dir;
		this.maxFiles = max;
		if (this.baseDir == null) {
			throw Lang.newCause("The baseDir must not be null", new Object[0]);
		}
		if (this.baseDir.isFile()) {
			throw Lang.newCause("The baseDir(%s) must not be a file",
					new Object[] { this.baseDir.getAbsolutePath() });
		}

		this.baseDir.mkdirs();
		if (!this.baseDir.isDirectory())
			throw Lang.newCause("The baseDir(%s) must be a directory",
					new Object[] { this.baseDir.getAbsolutePath() });
	}

	protected abstract String generateKey(String paramString);

	private final File name(String key) {
		File file = new File(this.baseDir, generateKey(key).concat(".poolfile"));
		return file;
	}

	public File fetch(String key) {
		checkMaxAndRemove(0);
		File file = name(key);
		if (!file.isFile()) {
			return null;
		}

		file.setLastModified(System.currentTimeMillis());
		return file;
	}

	public File make(String key) {
		checkMaxAndRemove(0);
		File file = name(key);
		if (!file.isFile()) {
			if (checkMaxAndRemove(1))
				try {
					file.createNewFile();
				} catch (IOException e) {
					throw Lang.wrapCause(e);
				}
			else {
				Logs.error("无法新增文件，请稍后再试");
			}
		} else {
			file.setLastModified(System.currentTimeMillis());
		}
		return file;
	}

	private boolean checkMaxAndRemove(int news) {
		if (this.maxFiles > -1) {
			File[] files = this.baseDir.listFiles(fileFilter);
			int deletes = news + files.length - this.maxFiles;
			if (deletes > 0) {
				Arrays.sort(files, new Comparator() {
					@Override
					public int compare(Object o1, Object o2) {
						return (int) (((File) o1).lastModified() - ((File) o2)
								.lastModified());
					}
				});
				for (File file : files) {
					if ((deletes <= 0) || (!file.delete()))
						break;
					--deletes;
				}

				if (deletes > 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean remove(String key) {
		checkMaxAndRemove(0);
		File file = name(key);
		if (file.isFile()) {
			return file.delete();
		}
		return false;
	}

	public boolean clear() {
		for (File file : this.baseDir.listFiles(fileFilter)) {
			if (!file.delete()) {
				return false;
			}
		}
		return true;
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.io.FilePool JD-Core Version: 0.5.4
 */