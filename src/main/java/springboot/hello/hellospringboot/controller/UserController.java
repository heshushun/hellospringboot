package springboot.hello.hellospringboot.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.hello.hellospringboot.common.orika.OrikaBeanMapper;
import springboot.hello.hellospringboot.entity.UserEntity;
import springboot.hello.hellospringboot.request.Req700001;
import springboot.hello.hellospringboot.response.BaseResp;
import springboot.hello.hellospringboot.service.UserService;

import javax.annotation.Resource;
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
    public BaseResp<Page<UserEntity>> page( Req700001 req) throws ParseException {
        Page<UserEntity> page = new Page<>();

        page.setCurrent(req.getCurrent());
        page.setSize(req.getSize());
        page.setOrderByField("t_id");
        page.setAsc(false);

        Page<UserEntity> userPage = userService.selectUserPage(page,orikaBeanMapper.map(req,UserEntity.class));

        return new BaseResp<>(userPage);
    }

    /**
     * 删除用户列表
     * @return
     */
    @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
    public BaseResp<String> deleteById(@RequestParam Integer id) {
        userService.delUser(id);
        return new BaseResp("删除成功");
    }


    /**
     * 添加用户
     * @return
     */
    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    public BaseResp<String> save(@RequestBody UserEntity user) {
        userService.addUser(user);
        return new BaseResp("新增成功");
    }

    /**
     * 批量添加用户
     * @return
     */
    @RequestMapping(value = "/saveList" ,method = RequestMethod.POST)
    public BaseResp<String> saveUserList(@RequestBody List<UserEntity> userList) {
        userService.saveUserList(userList);
        return new BaseResp("批量添加成功");
    }

    /**
     * 根据Id 查询用户
     * @return
     */
    @RequestMapping(value = "/get" ,method = RequestMethod.POST)
    public UserEntity getUserById(@RequestParam Integer id) {
        return userService.getUserById(id);
    }


}

