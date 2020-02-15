package com.fsoft.core.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * F-Soft 拼音工具类
 * @package com.fsoft.core.utils
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2020-02-15
 * @CopyRight © F-Soft
 **/
public class PinYinUtils {

	/**
	 * F-Soft 汉字转成拼音并返回首字母
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param src
	 * @return
	 */
	public static String getPingYin(String src) {
		char[] t1 = (char[]) null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();

		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					t4 = t4 + t2[0];
				} else {
					t4 = t4 + Character.toString(t1[i]);
				}
			}
			return t4;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		return t4;
	}

	/**
	 * F-Soft 汉字转成拼音并返回首字母
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param str
	 * @return
	 */
	public static String getPinYinHeadChar(String str) {
		String convert = "";
		String first = (String) str.subSequence(0, 1);
		for (int j = 0; j < first.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null)
				convert = convert + pinyinArray[0].charAt(0);
			else {
				convert = convert + word;
			}
		}
		return convert.toUpperCase();
	}
}
