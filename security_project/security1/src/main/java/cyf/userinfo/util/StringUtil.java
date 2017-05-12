package cyf.userinfo.util;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * String工具类
 * 
 * @author sujishen
 * @date 2015/26/06
 */
public class StringUtil {

	/**
	 * convert the object to be string
	 * 
	 * @param obj
	 * @return
	 */
	public static String objToString(Object obj) {
		if (obj == null || "NULL".equalsIgnoreCase(String.valueOf(obj).trim())) {
			return "";
		}
		return String.valueOf(obj).trim();
	}

	/**
	 * Returns if this object is empty.
	 * 
	 * @param Obj
	 * @return
	 */
	public static boolean isBlank(Object obj) {
		return obj == null || "".equals(String.valueOf(obj).trim())
				|| "NULL".equalsIgnoreCase(String.valueOf(obj).trim());
	}

	/**
	 * Returns if this object array contains empty elements.
	 * 
	 * @param Obj
	 * @return
	 */
	public static boolean isBlank(Object... objs) {
		boolean yes = false;
		for (Object obj : objs) {
			if (isBlank(obj)) {
				yes = true;
				break;
			}
		}
		return yes;
	}

	/**
	 * Returns if each element of the object array is empty.
	 * 
	 * @param Obj
	 * @return
	 */
	public static boolean ifAllBlank(Object... objs) {
		boolean yes = true;
		for (Object obj : objs) {
			if (!isBlank(obj)) {
				yes = false;
			}
		}
		return yes;
	}

	/**
	 * Returns the Integer value of the String type
	 * 
	 * @author sujishen
	 * @date 2015-6-25 下午2:42:42
	 * @param str
	 * @return
	 */
	public static Integer parseInt(String str) {
		if (!isBlank(str)) {
			return new Integer(str);
		}
		return null;
	}
	
	/**
	 * Returns the Integer value of the String type
	 * 
	 * @author sujishen
	 * @date 2015-6-25 下午2:42:42
	 * @param str
	 * @return
	 */
	public static Float parseFloat(String str) {
		if (!isBlank(str)) {
			return new Float(str);
		}
		return null;
	}

	/**
	 * Returns if the string arrays contains the specified string.
	 * 
	 * @author Su Jishen
	 * @DateTime 2015年8月10日 下午4:15:27
	 */
	public static boolean isInScope(String original, String... scopes) {
		if (!isBlank(original) && !isBlank((Object[]) scopes)) {
			if (Arrays.asList(scopes).contains(original)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns if the para is a number obj
	 * 
	 * @author Su Jishen
	 * @DateTime 2015年9月1日 下午2:15:33
	 */
	public static boolean isNumber(String paramVal) {
		boolean flag = true;
		try {
			Integer.parseInt(paramVal);
		} catch (Exception e) {
			flag = false;
		}
		if (flag) {
			flag = paramVal.matches("[\\d]+");
		}

		return flag;
	}
	
	/**
	 * 
	 * @author Su Jishen
	 * @DateTime 2015年9月1日 下午2:15:33
	 */
	public static boolean isNumber(String... paramVals) {
		boolean flag = true;
		String s = "";
		try {
			for (String paramVal : paramVals) {
				s = paramVal;
				Integer.parseInt(paramVal);
			}
		} catch (Exception e) {
			flag = false;
		}
		if (flag) {
			flag = s.matches("[\\d]+");
		}

		return flag;
	}
	
	/**
	 * Returns if the 2nd and 3rd pargs contain the 1st arg
	 * @author 	  Su Jishen
	 * @DateTime 2015年9月1日 下午2:23:11
	 */
	public static boolean isInBetween(String param, String start, String end) {
		if(isBlank(param, start, end)) {
			return false;
		}
		Integer paramVal = parseInt(param);
		Integer startNum = parseInt(start);
		Integer endNum = parseInt(end);
		if (paramVal >= startNum) {
			if (endNum > 0) {
				return (paramVal <= endNum);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 是否为金融值
	 * @author 	  Su Jishen
	 * @DateTime 2015年9月7日 下午3:52:27
	 */
	public static boolean isNumberExt(String paramVal, boolean dot, boolean negative) {
		boolean flag = true;
		try {
			Double.parseDouble(paramVal);
		} catch (Exception e) {
			flag = false;
		}
		if (flag) {
			String regex = "[\\d]+";
			if (dot)
				regex = regex + "+\\.?\\d*";

			if (negative) {
				regex = "^(-)?" + regex;
			}
			regex = regex + "$";

			flag = paramVal.matches(regex);
		}

		return flag;
	}

	/**
	 * 替换全角括号为半角括号
	 * @param str 需要替换的字符串
	 * @return
	 */
	public static String replaceFullWidthBrackets(String str){
		String[] searchList = new String[]{"（","）"};
		String[] replacementList = new String[]{"(",")"};
		return StringUtils.replaceEach(str,searchList,replacementList);
	}

}
