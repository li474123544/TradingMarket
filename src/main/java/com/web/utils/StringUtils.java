package com.web.utils;

import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static Gson jsonUtils = new Gson();
	public static int count = 0;

	// 用星号混淆字符串
	public static String confusion(String content, int startIndex, int replaceCount) {
		return confusion(content, startIndex, replaceCount, "*");
	}

	// 用指定符号混淆字符串
	public static String confusion(String content, int startIndex, int replaceCount, String symbol) {
		if (startIndex + replaceCount > content.length()) {
			return content;
		}

		String confusionStr = "";
		while (confusionStr.length() < replaceCount) {
			confusionStr += symbol;
		}

		return content.substring(0, startIndex) + confusionStr + content.substring(startIndex + 1 + replaceCount);
	}

	// 判断字符串是否是由纯数字组成
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	// 判断字符串是否null或者空字符
	public static boolean isBlank(String... str) {
		for (String _str : str) {
			if (_str != null && !"".equals(_str.trim())) {
				return false;
			}
		}

		return true;
	}

	// 判断字符串是否不为null并且不为空字符串
	public static boolean isNotBlank(String... str) {
		for (String _str : str) {
			if (_str == null || "".equals(_str)) {
				return false;
			}
		}
		return true;
	}

	public static String getPrimaryKey() {
		synchronized (Long.class) {
			count++;
			if (count >= 99999) {
				count = 0;
			}
		}
		String param = count + "";
		while (param.length() < 5) {
			param = "0" + param;
		}
		return (DateUtils.getToday("YYYYMMddHHmmss") + param);
	}

	public static String fill(String str, int length, String character) {
		while (str.length() < length) {
			str = character + str;
		}
		return str;
	}

	public static boolean isContainChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {//匹配
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
	 System.out.println(StringUtils.isContainChinese("小红uo"));
	}
}
