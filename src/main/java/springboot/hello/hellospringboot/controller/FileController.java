package springboot.hello.hellospringboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springboot.hello.hellospringboot.common.utils.FileUtils;
import springboot.hello.hellospringboot.request.Req700014;

import javax.validation.Valid;
import java.io.IOException;

/**
 * <p>
 *  文件控制器
 * </p>
 *
 * @author hss
 * @since 2018-11-07
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private static  final Logger logger = LoggerFactory.getLogger(FileController.class);

    private FileUtils fileUtils = new FileUtils();

    /**
     * 文件 转 字节流
     * @return
     */
    @RequestMapping(value = "/toByteArray",method = RequestMethod.POST)
    public String deleteNewsById(@Valid Req700014 req) throws IOException {
        Assert.notNull(req.getFilePath(),"文件不能为空");
        byte[] file_bytes = fileUtils.getContent(req.getFilePath());
        StringBuilder sb = new StringBuilder(file_bytes.length * 2);
        for (int i=0;i<file_bytes.length-1; i++) {
            sb.append(file_bytes[i]);
            sb.append(", ");
        }
        sb.append(file_bytes[file_bytes.length-1]);
        String file_String = sb.toString();

        return file_String;
    }


}
