package com.tencent.mars.xlog;

import android.util.Log;

public class Xlog {

	public static final int LEVEL_ALL = 0;
	public static final int LEVEL_VERBOSE = 0;
	public static final int LEVEL_DEBUG = 1;
	public static final int LEVEL_INFO = 2;
	public static final int LEVEL_WARNING = 3;
	public static final int LEVEL_ERROR = 4;
	public static final int LEVEL_FATAL = 5;
	public static final int LEVEL_NONE = 6;

	public static final int AppednerModeAsync = 0;
	public static final int AppednerModeSync = 1;

	public static class XLoggerInfo {
		public int level;
		public String tag;
		public String filename;
		public String funcname;
		public int line;
		public long pid;
		public long tid;
		public long maintid;
	}

	private static String decryptTag(String tag) {
		return tag;
	}

	public static native void logWrite(XLoggerInfo logInfo, String log);

	public static native void logWrite2(int level, String tag, String filename, String funcname, int line, int pid, long tid, long maintid, String log);

	public native int getLogLevel();

	public static native void setLogLevel(int logLevel);

	public static native void setAppenderMode(int mode);

	public static native void setConsoleLogOpen(boolean isOpen);	//set whether the console prints log

	public static native void setErrLogOpen(boolean isOpen);	//set whether the  prints err log into a separate file

	public static native void appenderOpen(int level, int mode, String cacheDir, String logDir, String nameprefix, String pubkey);

	public static native void appenderClose();

	public static native void appenderFlush(boolean isSync);

}
