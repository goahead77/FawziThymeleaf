package cn.fawzi.thymeleaf.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author wenqi
 */
public class DES {

    private static byte[] iv ={'a',2,'m',4,'c',6,'x',8};

    private static final String encryptKey="fawzi77.";

    /**
     * 加密
     * @param encryptString 需要加密的文本
     * @return
     * @throws Exception
     */
    public static String encryptDES(String encryptString) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());

        return Base64.encode(encryptedData);
    }

    /**
     * 解密
     * @param decryptString 加密了的文本
     * @return
     * @throws Exception
     */
    public static String decryptDES(String decryptString) throws Exception {
        byte[] byteMi = new Base64().decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);

        return new String(decryptedData);
    }

    public static void main(String[] args) throws Exception {
        String a=encryptDES("aaaaa,fawzi77");
        System.out.println(a);
        String b=decryptDES(a);
        System.out.println(b);
    }
}
