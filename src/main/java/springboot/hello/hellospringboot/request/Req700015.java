package springboot.hello.hellospringboot.request;

import com.baomidou.mybatisplus.annotations.TableField;
import org.hibernate.validator.constraints.NotBlank;
import springboot.hello.hellospringboot.common.annotation.ExceVo;

public class Req700015 {

    /**
     * 文件名称
     */
    private String fileName;


    /**
     * 分组
     */
    private String group1;
    /**
     * 项目名称
     */
    private String project;
    /**
     * 响应代码
     */
    private String responceCode;
    /**
     * 断言状态
     */
    private String status;




    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getGroup1() {
        return group1;
    }

    public void setGroup1(String group1) {
        this.group1 = group1;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getResponceCode() {
        return responceCode;
    }

    public void setResponceCode(String responceCode) {
        this.responceCode = responceCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
