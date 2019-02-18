package chnp.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {

    private static final String[] hexDigIts = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"
    };

    public static void main(String[] args) {
//        try {
            System.out.println(areNotEmpty("123456", "1"));
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }

	/**<p>大驼峰式命名格式转化</p>
	 *
	 * 大驼峰式命名规范：
	 * <ul>
	 * 		<li>首字母大写</li>
	 * 		<li>如果存在多个单词，每个单词的首字母大写，其余字母小写</li>
	 * 		<li>不包含除英文字母外的其他字符</li>
	 * </ul>
	 * @param str 待转化的字符串
	 * @return 转化后的字符
	 */
    public static String upperCamelCase(String str) {
        String[] words = str.split("[^A-Za-z$]");
        if (0 == words.length) throw new RuntimeException("字符串必须包含字母");

        String newStr = "";
        for (String word : words) {
            if (0 == word.length()) continue;
            newStr += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
        }
        return newStr;
    }

	/**<p>小驼峰式命名格式转化</p>
	 *
	 * 小驼峰式命名规范：
	 * <ul>
	 * 		<li>首字母小写</li>
	 * 		<li>如果存在多个单词，每个单词的首字母大写，其余字母小写</li>
	 * 		<li>不包含除英文字母外的其他字符</li>
	 * </ul>
	 * @param str 待转化的字符串
	 * @return 转化后的字符
	 */
    public static String lowerCamelCase(String str) {
        String[] words = str.split("[^A-Za-z$]");
        if (0 == words.length) throw new RuntimeException("字符串必须包含字母");

        String newStr = words[0].toLowerCase();
        for (int i=1; i< words.length; i++) {
            if (0 == words[i].length()) continue;
            newStr += words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        return newStr;
    }

	/**<p>下划线命名格式转化</p>
	 *
	 * 下划线命名规范：
	 * <ul>
	 * 		<li>所有字母小写</li>
	 * 		<li>如果存在多个单词，每个单词用下划线分隔</li>
	 * </ul>
	 * @param str 待转化的字符串
	 * @return 转化后的字符
	 */
    public static String underScoreCase(String str) {
        if (0 == str.length()) throw new RuntimeException("字符串不能为空");

        String newStr = "";
        for (int i=0; i<str.length(); i++) {
            String ch = str.substring(i, i+1);
            if (0 == i) {
                newStr += ch.toLowerCase();
            } else if (i > 0 && 0 <= ch.compareTo("A") && 0 >= ch.compareTo("a")) {
                newStr += "_" + ch.toLowerCase();
            } else {
                newStr += ch;
            }
        }
        return newStr;
    }

    public static String toAuthName(String str) {
        String[] words = str.split("[^A-Za-z$]");
        if (0 == words.length) throw new RuntimeException("字符串必须包含字母");

        String newStr = "";
        for (String word : words) {
            if (0 == word.length()) continue;
            newStr += word.toLowerCase();
        }
        return newStr;
    }

    public static String toAliasName(String name) {
        return "ALIAS_"+name.toUpperCase();
    }

    public static boolean isEmpty(String str) {
        return null == str || str.trim().isEmpty();
    }

    public static boolean areNotEmpty(String... strs) {
        boolean areNotEmpty = true;
        for (int i=0; areNotEmpty && i<strs.length; i++) areNotEmpty = !isEmpty(strs[i]);
        return areNotEmpty;
    }

    public static String MD5Encode(String origin) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return MD5Encode(origin, null);
    }

    public static String MD5Encode(String origin, String charsetname) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String resultString = origin;
        MessageDigest md = MessageDigest.getInstance("MD5");
        if(isEmpty(charsetname)){
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        }else {
            resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        }
        return resultString;
    }

    private static String byteArrayToHexString(byte b[]){
        StringBuilder resultSb = new StringBuilder();
        for (byte b1 : b) {
            resultSb.append(byteToHexString(b1));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b){
        int n = b;
        if(n < 0){
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }

}