package cn.fawzi.thymeleaf.Utils;

import java.security.MessageDigest;

/**
 * @author wenqi
 */
public class MD5Util {
    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    private static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }
    /**
     * 解密
     * @param s
     * @return
     */
    public static String md52String(String s){
        return convertMD5(convertMD5(s));
    }

    public static void main(String[] args) {
        String a=md52String("1111");
        String x=string2MD5(a);
        System.out.println(x);
        String b=convertMD5(x);

        System.out.println(a);
        System.out.println(b);
        System.out.println(convertMD5(x));
    }
}
