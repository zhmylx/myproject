package com.cpersicum.modules.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;

public class TestMain {
	public static void main(String[] args) {
		try {
			Runtime.getRuntime().exec("/u01/tst/test.sh");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main4(String[] args) {
		try {
			String[] cmd = new String[3];
			cmd[0] = "cmd dir";
			cmd[1] = "D:";
			cmd[2] = "mkdir test";
			Process process = Runtime.getRuntime().exec("cmd d:/ mkdir test");

			InputStreamReader ir = new InputStreamReader(
					process.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line;
			while ((line = input.readLine()) != null)
				System.out.println(line);
		} catch (IOException e) {
			System.err.println("IOException " + e.getMessage());
		}
	}

	public static void main2(String[] args) {
		try {
			String osName = System.getProperty("os.name");
			String osip = System.getProperty("os.ip");
			System.out.println(osip);
			String[] cmd = new String[3];
			System.out.println(osName);
			if (osName.equals("Windows XP")) {
				cmd[0] = "cmd.exe";
				cmd[1] = "D:/";
				cmd[2] = "mkdir test";
				Runtime rt = Runtime.getRuntime();
				System.out.println("Execing " + cmd[0] + " " + cmd[1] + " "
						+ cmd[2]);
				Process proc = rt.exec(cmd);
				int exitVal = proc.exitValue();
				System.out.println("ExitValue: " + exitVal);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main1(String[] args) {
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec("javac");
			int exitVal = proc.exitValue();
			System.out.println(exitVal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
