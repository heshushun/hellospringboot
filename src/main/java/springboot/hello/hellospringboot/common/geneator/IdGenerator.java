package springboot.hello.hellospringboot.common.geneator;

import com.baomidou.mybatisplus.toolkit.IdWorker;

/**
 * 唯一id生成器
 * 可用来 生成表的id 主键
 * @author hss
 * @date 2018/10/30
 */
public class IdGenerator {

    public static String getId() {
        return String.valueOf(IdWorker.getId());
    }

    public static long getIdLong() {
        return IdWorker.getId();
    }
}
