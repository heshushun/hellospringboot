package springboot.hello.hellospringboot.request;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class Req700014 implements Serializable {

    private static final long serialVersionUID = -7323868806548711092L;

    /**
     * 文件
     */
    @NotBlank(message = "文件不能为空")
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
