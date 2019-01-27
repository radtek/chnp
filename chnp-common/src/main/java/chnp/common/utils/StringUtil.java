package chnp.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {

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