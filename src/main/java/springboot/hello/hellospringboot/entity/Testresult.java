package springboot.hello.hellospringboot.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import springboot.hello.hellospringboot.common.annotation.ExceVo;

import java.io.Serializable;

/**
 * <p>
 * 测试结果表
 * </p>
 *
 * @author hss
 * @since 2018-11-23
 */
@TableName("t_testresult")
public class Testresult extends Model<Testresult> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 项目名称
     */
	@ExceVo(sort = 1)
	private String project;
    /**
     * 结果分组
     */
	@ExceVo(sort = 8)
	private String group1;
    /**
     * 功能号
     */
	@ExceVo(sort = 2)
	@TableField("function_id")
	private String functionId;
    /**
     * 请求描述
     */
	@ExceVo(sort = 3)
	@TableField("request_msg")
	private String requestMsg;
    /**
     * 预期结果
     */
	@ExceVo(sort = 4)
	@TableField("expect_result")
	private String expectResult;
    /**
     * 响应结果
     */
	@ExceVo(sort = 5)
	@TableField("responce_result")
	private String responceResult;
    /**
     * 响应代码
     */
	@ExceVo(sort = 6)
	@TableField("responce_code")
	private String responceCode;
    /**
     * 断言状态
     */
	@ExceVo(sort = 7)
	private String status;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private String createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getGroup1() {
		return group1;
	}

	public void setGroup1(String group1) {
		this.group1 = group1;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getRequestMsg() {
		return requestMsg;
	}

	public void setRequestMsg(String requestMsg) {
		this.requestMsg = requestMsg;
	}

	public String getExpectResult() {
		return expectResult;
	}

	public void setExpectResult(String expectResult) {
		this.expectResult = expectResult;
	}

	public String getResponceResult() {
		return responceResult;
	}

	public void setResponceResult(String responceResult) {
		this.responceResult = responceResult;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Testresult{" +
			"id=" + id +
			", project=" + project +
			", group1=" + group1 +
			", functionId=" + functionId +
			", requestMsg=" + requestMsg +
			", expectResult=" + expectResult +
			", responceResult=" + responceResult +
			", responceCode=" + responceCode +
			", status=" + status +
			", createTime=" + createTime +
			"}";
	}
}
