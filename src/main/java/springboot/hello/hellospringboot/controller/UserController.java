package springboot.hello.hellospringboot.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.hello.hellospringboot.common.orika.OrikaBeanMapper;
import springboot.hello.hellospringboot.entity.UserEntity;
import springboot.hello.hellospringboot.request.Req700001;
import springboot.hello.hellospringboot.request.Req700002;
import springboot.hello.hellospringboot.request.Req700003;
import springboot.hello.hellospringboot.request.Req700004;
import springboot.hello.hellospringboot.response.BaseResp;
import springboot.hello.hellospringboot.service.UserService;

import javax.validation.Valid;
import java.text.ParseException;
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
@RequestMapping("/hellospringboot")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrikaBeanMapper orikaBeanMapper;

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
        userService.addUser(orikaBeanMapper.map(req,UserEntity.class));
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


}

