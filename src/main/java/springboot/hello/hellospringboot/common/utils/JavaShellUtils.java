package springboot.hello.hellospringboot.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 执行 linux 命令
 */
public class JavaShellUtils {

    public static Boolean execute(String command) {
        StringBuffer returnString = new StringBuffer();
        Process pro = null;
        Runtime runTime = Runtime.getRuntime();
        if (runTime == null) {
            System.err.println("Create runtime false!");
        }
        try {
            System.out.println("开始执行");
            pro = runTime.exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(pro.getOutputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println("line: " + line);
                returnString.append(line).append("\n");
            }
            System.out.println("返回值:" + returnString.toString());
            input.close();
            output.close();
            pro.destroy();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        JavaShellUtils javaShellUtils = new JavaShellUtils();
        javaShellUtils.execute("D:\\software\\jmeter-3.3\\apache-jmeter-3.0\\bin\\jmeter.bat -n -t D:\\software\\jmeter-3.3\\mysql\\OSS6_OpenAPI_ad-mysql-wg.jmx ");
    }


}

