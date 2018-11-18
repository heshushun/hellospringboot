package springboot.hello.hellospringboot.common.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 *  MD5加密 工具类（普通）
 * </p>
 *
 * @author hss
 * @since 2018-11-17
 */
public final class MD5Utils
{
    private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static final String md5(String content)
            throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(content.getBytes());
        return encodeToString(digest.digest());
    }

    private static String encodeToString(byte[] bytes)
    {
        return new String(byteArrayToHexString(bytes));
    }

    private static String byteArrayToHexString(byte[] b)
    {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b)
    {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static void main(String args[]) {
        try {
            String inputStr = "1001qudao1PkPjxRAkedLrAvyn2tTRAnLorN7592BA54-86F2-3E3F-A8AE-3D68CEA23FFE1541485310981";//加密原值
            System.out.println("加密前原值： " + inputStr);
            MD5Utils md5Utils = new MD5Utils();
            String outputStr = md5Utils.md5(inputStr).toUpperCase();//生成加密密值
            System.out.println("加密后的值： " + outputStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
