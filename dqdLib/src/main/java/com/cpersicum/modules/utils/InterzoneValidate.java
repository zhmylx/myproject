package com.cpersicum.modules.utils;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class InterzoneValidate {
	public String validateInterzone(Map map, Double minValue, Double maxValue) {
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Double min = Double.valueOf(entry.getKey().toString());
			Double max = Double.valueOf(entry.getValue().toString());
			if ((max.doubleValue() > minValue.doubleValue())
					&& (minValue.doubleValue() >= min.doubleValue())) {
				System.out.println(minValue + "最小值不符合规定" + "系统已存在区间" + min
						+ "至" + max);
				return minValue + "最小值不符合规定" + "系统已存在区间" + min + "至" + max;
			}
			if ((min.doubleValue() <= maxValue.doubleValue())
					&& (maxValue.doubleValue() < max.doubleValue())) {
				System.out.println(maxValue + "最大值不符合规定" + "系统已存在区间" + min
						+ "至" + max);
				return maxValue + "最大值不符合规定" + "系统已存在区间" + min + "至" + max;
			}
			if ((minValue.doubleValue() <= min.doubleValue())
					&& (maxValue.doubleValue() >= max.doubleValue())) {
				System.out
						.println("当前区间已经包含当前的已存在的区间系统已存在区间" + min + "至" + max);

				return "当前区间已经包含当前的已存在的区间系统已存在区间" + min + "至" + max;
			}

			System.out.println("最小值符合规定");
			System.out.println("最大值符合规定");
		}

		return "";
	}

	public static void main(String[] args) {
		Map map = new HashMap();

		map.put(Double.valueOf(1000.0D), Double.valueOf(5000.0D));
		map.put(Double.valueOf(6000.0D), Double.valueOf(7000.0D));
		map.put(Double.valueOf(8000.0D), Double.valueOf(9000.0D));

		Double minValue = Double.valueOf(1.0D);
		Double maxValue = Double.valueOf(95999.0D);

		new InterzoneValidate().validateInterzone(map, minValue, maxValue);
	}
}
