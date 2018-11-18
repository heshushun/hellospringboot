package springboot.hello.hellospringboot.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springboot.hello.hellospringboot.common.orika.OrikaBeanMapper;
import springboot.hello.hellospringboot.common.utils.ExcelExportUtil;
import springboot.hello.hellospringboot.common.utils.ExcelReadUtil;
import springboot.hello.hellospringboot.entity.UserEntity;
import springboot.hello.hellospringboot.request.*;
import springboot.hello.hellospringboot.response.BaseResp;
import springboot.hello.hellospringboot.response.Resp80001;
import springboot.hello.hellospringboot.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户控制层
 * </p>
 *
 * @author hss
 * @since 2018-10-09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private  final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private OrikaBeanMapper orikaBeanMapper;

    /**
     * 用户登录接口
     * @param res 登录请求参数
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResp login(@RequestBody Req700005 res){
        String loginToken = userService.login(res);
        HashMap<String,Object> result = new HashMap<>();
        result.put("msg","登录成功！");
        result.put("Token",loginToken);
        return new BaseResp<>(Boolean.TRUE,result);
    }

    /**
     * 查询用户列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserEntity> list() {
        return userService.list();
    }

    /**
     * 用户列表（分页）
     *
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public BaseResp page(@Valid Req700001 req) throws ParseException {

        Page<UserEntity> page = new Page<>();

        page.setCurrent(req.getCurrent());
        page.setSize(req.getSize());
        page.setOrderByField("t_id");
        page.setAsc(false);

        Page<UserEntity> userPage = userService.selectUserPage(page,orikaBeanMapper.map(req,UserEntity.class));

        return new BaseResp(Boolean.TRUE,userPage);
    }

    /**
     * 删除用户列表
     * @return
     */
    @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
    public BaseResp<String> deleteById(@Valid  Req700002 req) {
        userService.delUser(req.getId());
        return new BaseResp("删除成功");
    }

    /**
     * 添加用户
     * @return
     */
    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    public BaseResp<String> save(@RequestBody @Valid Req700003 req) {
        try {
            userService.addUser(orikaBeanMapper.map(req,UserEntity.class));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new BaseResp("新增失败");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new BaseResp("新增失败");
        }
        return new BaseResp("新增成功");
    }


    /**
     * 批量添加用户
     * @return
     */
    @RequestMapping(value = "/saveList" ,method = RequestMethod.POST)
    public BaseResp<String> saveUserList(@RequestBody @Valid List<UserEntity> userList) {
        userService.saveUserList(userList);
        return new BaseResp("批量添加成功");
    }


    /**
     * 根据Id 查询用户
     * @return
     */
    @RequestMapping(value = "/get" ,method = RequestMethod.POST)
    public BaseResp<UserEntity> getUserById(@Valid Req700004 req) {
        UserEntity userEntity = userService.getUserById(req.getId());
        return new BaseResp(Boolean.TRUE,userEntity);
    }

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public BaseResp<Resp80001> info(){
        Resp80001 resp80001 = new Resp80001();
        resp80001.getRoles().add("admin");
        return new BaseResp<>(resp80001);
    }

    /**
     * 导入用户信息
     * @return
     */
    @RequestMapping(value = "/excelReadUser",method = RequestMethod.POST)
    public BaseResp excelReadUser(@Valid Req700014 req){
        ExcelReadUtil<UserEntity> excelReadUtil = new ExcelReadUtil<UserEntity>(UserEntity.class);
        List<UserEntity> users = null;
        try {
            users = excelReadUtil.readExcel(req.getFilePath(), 1);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>(Boolean.FALSE,"导入用户失败");
        }

        if(users.size()!=0||users!=null) {
            for (UserEntity user : users) {
                logger.info("导入读取的数据：" + user.toString());
            }
            userService.saveUserList(users);//批量插入数据库
        }else {
            return new BaseResp<>(Boolean.FALSE,"导入表格无数据");
        }

        return new BaseResp<>(Boolean.TRUE,"导入用户成功");
    }

    /**
     * 导出用户信息
     * @return
     */
    @RequestMapping(value = "/excelExportUser",method = RequestMethod.POST)
    public BaseResp excelExportUser(HttpServletResponse response, String fileName){
        ExcelExportUtil<UserEntity> excelExportUtil = new ExcelExportUtil<UserEntity>(UserEntity.class);
        List<UserEntity> userlist = null;
        String excelName = "userList";
        if(!StringUtils.isEmpty(fileName)){
            excelName = fileName;
        }
        try {
            userlist = userService.list();
            if(userlist.size()!=0||userlist!=null) {

                // 告诉浏览器用什么软件可以打开此文件
                response.setHeader("content-Type", "application/vnd.ms-excel");
                // 下载文件的默认名称 浏览器会弹窗另存为
                response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + URLEncoder.encode( excelName, "UTF-8") + ".xls");
                //编码
                response.setCharacterEncoding("UTF-8");

                // 基于注解导出
                excelExportUtil.exportAsAop(excelName,"templet.xls", userlist, 4);
            }else {
                return new BaseResp<>(Boolean.FALSE,"导出表格无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>(Boolean.FALSE,"导出用户失败");
        }

        return new BaseResp<>(Boolean.TRUE,"导出用户成功");
    }


}

