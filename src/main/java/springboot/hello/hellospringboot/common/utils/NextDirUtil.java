package springboot.hello.hellospringboot.common.utils;

import java.io.File;

/**
 * 文件夹管理
 * @author hss
 * @since 2018-11-14
 */
public class NextDirUtil {

    /**
     * 文件夹管理，看是否存在该文件夹，如果存在那么就进入这个层级，如果不存在就创建该目录
     *
     * @param dir
     * @param file
     * @return
     */
    public File nextDir(String dir, File file) {
        File[] files = file.listFiles();
        for (File subFile : files) {
            if (subFile.getName().equals(dir) && subFile.isDirectory()) {
                return subFile;
            }
        }
        File $var1 = new File(file, dir);
        $var1.mkdir();
        $var1.setExecutable(Boolean.TRUE);
        $var1.setReadable(Boolean.TRUE);
        $var1.setExecutable(Boolean.TRUE);
        return $var1;
    }

}
