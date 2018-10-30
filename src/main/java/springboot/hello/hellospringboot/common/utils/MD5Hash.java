package springboot.hello.hellospringboot.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.codec.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: hss
 * @Date: 2018/10/30
 * @Desc: MD5Hash 加密
 */
public class MD5Hash {

    //加密KEY
    private static final String KEY_MD5 = "MD5";
    //编码格式
    private static final String PREFERRED_ENCODING = "UTF-8";
    //加密盐
    private static final String SALT = "hellospringboot";
    //Hash次数
    private static final int DEFAULT_ITERATIONS = 1;

    private byte [] bytes;

    /**
     *  返回MD5之后的值
     * @param source    原始数据
     * @param salt      加密盐
     * @param hashIterations    hash次数
     * @return
     */
    public MD5Hash(String source, String salt, int hashIterations) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte [] sourceBytes = source.getBytes(PREFERRED_ENCODING);
        byte[] saltBytes =  salt.getBytes(PREFERRED_ENCODING);
        if(hashIterations < 1){
            hashIterations = 1;
        }
        setBytes(hash(sourceBytes,saltBytes,hashIterations));
    }

    /**
     * 进行hash加密
     * @param sourceBytes
     * @param saltBytes
     * @param hashIterations
     * @return
     * @throws NoSuchAlgorithmException
     */
    public  byte[] hash(byte [] sourceBytes, byte[] saltBytes, int hashIterations) throws NoSuchAlgorithmException {
            MessageDigest digest = MessageDigest.getInstance(KEY_MD5);
            if (saltBytes != null) {
                digest.reset();
                digest.update(saltBytes);
            }
            byte[] hashed = digest.digest(sourceBytes);
            int iterations = hashIterations - DEFAULT_ITERATIONS; //already hashed once above
            //iterate remaining number:
            for (int i = 0; i < iterations; i++) {
                digest.reset();
                hashed = digest.digest(hashed);
            }
            return hashed;
    }

    public String toString () {
       return Hex.encodeToString(getBytes());
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }


    /**
     * 比较密码是否正确
     * @param source 原密码
     * @param target 输入密码
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static Boolean compare(String source,String target) throws UnsupportedEncodingException,NoSuchAlgorithmException{

        MD5Hash hash = new MD5Hash(target,SALT,2);

        if(StringUtils.isNotBlank(hash.toString()) && hash.toString().equals(source)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    public static void main(String args[]) {
        try {
            String inputStr = "123123";//加密原值
            MD5Hash hash = new MD5Hash(inputStr,"hellospringboot",2);//生成加密密值
            System.out.println(hash.toString());

            String source = "123123";// 输入密码
            System.out.println(compare(hash.toString(),source));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
