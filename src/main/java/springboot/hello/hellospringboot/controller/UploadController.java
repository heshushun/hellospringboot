package springboot.hello.hellospringboot.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.hello.hellospringboot.common.exception.RRException;
import springboot.hello.hellospringboot.common.utils.DateUtil;
import springboot.hello.hellospringboot.common.utils.NextDirUtil;
import springboot.hello.hellospringboot.response.BaseResp;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: yzhang
 * @Date: 2018/8/17 13:26
 * @Desc: 上传图片处理
 */

@RestController
@CrossOrigin
@RequestMapping("/img")
public class UploadController {

    private static final Logger log = LoggerFactory.getLogger(UploadController.class);

    @Value("${main-dir}")
    private String mainDir;


    @Value("${visit-url}")
    private String visitUrl;

    NextDirUtil nextDirUtil = new NextDirUtil();

    /**
     * 上传图片，然后将图片的访问地址返回
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public BaseResp<String> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        //先获取主目录
        File main = new File(mainDir);
        //获取保存目录
        String yyyyMMdd = DateUtil.format(new Date(), "yyyyMMdd");
        // 是否存在该文件夹，如果存在那么就进入这个层级，如果不存在就创建该目录
        File sub = nextDirUtil.nextDir(yyyyMMdd, nextDirUtil.nextDir("image", main));
        if (log.isDebugEnabled()) {
            log.debug("【上传图片】图片名称为{}", file.getName());
        }
        String imageName = saveFile(file, sub);
        if (StringUtils.isNotBlank(imageName)) {
            return new BaseResp<>(visitUrl + "image/" + yyyyMMdd +"/"+ imageName);
        } else {
            throw new RRException("上传文件失败");
        }
    }

    /**
     * 保存图片
     * @param multipartFile
     * @param app
     */
    private String saveFile(MultipartFile multipartFile, File app) {
        try {
            String suffer = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            String imgName = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase().substring(17) + suffer;
            if (log.isDebugEnabled()) {
                log.debug("【上传图片】图片的名称为{}", imgName);
            }
            File $var1 = new File(app, imgName);
            $var1.setExecutable(Boolean.TRUE);
            $var1.setReadable(Boolean.TRUE);
            $var1.setExecutable(Boolean.TRUE);

            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), $var1);
            return imgName;
        } catch (IOException ex) {
            log.error("【保存文件】出错{}", ex.getMessage());
        }
        return null;
    }

    static class Parent {
        public int age = 0;
    }

    static class Target extends Parent{
       public volatile int value=10;
    }


}
