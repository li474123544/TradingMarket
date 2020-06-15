package com.web.utils;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;


public class CryptoUtils {
    //加密key
    public static final String ENCODE_KEY="Netty实战2.0";


    /**
     * 加密函数
     * @param content   加密的内容
     * @param strKey    密钥
     * @return          返回二进制字符数组
     * @throws Exception
     */
    public static byte[] enCrypt(String content,String strKey) throws Exception{
        KeyGenerator keygen;
        SecretKey desKey;
        Cipher c;
        byte[] cByte;
        String str = content;

        keygen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(strKey.getBytes());
        keygen.init(128, secureRandom);

        SecretKeySpec securekey = new SecretKeySpec(strKey.getBytes(), "AES");

        desKey = keygen.generateKey();
        c = Cipher.getInstance("AES");

        c.init(Cipher.ENCRYPT_MODE, desKey);

        cByte = c.doFinal(str.getBytes("UTF-8"));

        return cByte;
    }

    /** 解密函数
     * @param src   加密过的二进制字符数组
     * @param strKey  密钥
     * @return
     * @throws Exception
     */
    public static String deCrypt (byte[] src,String strKey) throws Exception{
        KeyGenerator keygen;
        SecretKey desKey;
        Cipher c;
        byte[] cByte;

        keygen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(strKey.getBytes());
        keygen.init(128, secureRandom);

        SecretKeySpec securekey = new SecretKeySpec(strKey.getBytes(), "AES");

        desKey = keygen.generateKey();
        c = Cipher.getInstance("AES");

        c.init(Cipher.DECRYPT_MODE, desKey);


        cByte = c.doFinal(src);

        return new String(cByte,"UTF-8");
    }


    /**2进制转化成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /*
     * 加密url参数
     * */
    public static String encodeParams(String content,String key){
        String encodeParams = "";
        try {
            byte[] encodeByte = enCrypt(content,key);
            encodeParams = parseByte2HexStr(encodeByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeParams;
    }

    public static String encodeParams(String content){
        String encodeParams = "";
        String key= ENCODE_KEY;
        try {
            byte[] encodeByte = enCrypt(content,key);
            encodeParams = parseByte2HexStr(encodeByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeParams;
    }

    /*
     * 解密加密的url参数
     * */
    public static String decodeParam(String content,String key){
        String decodeParams = "";
        byte[] decodeByte =  parseHexStr2Byte(content);
        try {
            decodeParams = deCrypt(decodeByte,key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodeParams;
    }

    public static String decodeParam(String content){
        String key=ENCODE_KEY;
        String decodeParams = "";
        byte[] decodeByte =  parseHexStr2Byte(content);
        try {
            decodeParams = deCrypt(decodeByte,key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodeParams;
    }

    /*
     * 将传过来的参数值名和值转换成Map形式
     * */
    public static Map<String, String> getParamsMap(String encodeParams,String paramkey) {
        Map<String,String> paramsMap = new HashMap<String, String>();
        String decodeParams = decodeParam(encodeParams, paramkey);
        String[] paramsArray = decodeParams.split("&");
        if(paramsArray != null){
            for(int i = 0;i < paramsArray.length;i++){
                String[] paramArray = paramsArray[i].split("=");
                if(paramArray != null && paramArray.length >= 2){
                    for(int j = 0;j < paramArray.length;j++){
                        paramsMap.put(paramArray[0], paramArray[1]);
                    }
                }
            }
        }

        return paramsMap;
    }

    public static String getUserIdFromLoginKey(String loginKey){
        try{

            if(StringUtils.isBlank(loginKey)){
                return null;
            }
            loginKey=decodeParam(loginKey,ENCODE_KEY);
            if(loginKey.split("_").length!=3){
                return null;
            }
            loginKey=loginKey.split("_")[0];
            return loginKey;
        }catch (Exception ex){
            return null;
        }

    }
    public static String getUserPhoneFromLoginKey(String loginKey){
        try{

            if(StringUtils.isBlank(loginKey)){
                return null;
            }
            loginKey=decodeParam(loginKey,ENCODE_KEY);
            if(loginKey.split("_").length!=3){
                return null;
            }
            loginKey=loginKey.split("_")[1];
            return loginKey;
        }catch (Exception ex){
            return null;
        }

    }
    public static void main(String[] args) {
        System.out.println(CryptoUtils.encodeParams("admin_15010053301_2", ENCODE_KEY));
        System.out.println(CryptoUtils.decodeParam("F6AA970FBF05FF864731D56AB26F68C71CFA3B5ABF5B70E6623AECB50B432D13", ENCODE_KEY));
    }
}
