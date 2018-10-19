package com.xd.evaluation.controller;

import com.xd.evaluation.VO.ResultVO;
import com.xd.evaluation.domain.User;
import com.xd.evaluation.form.UserForm;
import com.xd.evaluation.service.UserService;
import com.xd.evaluation.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created By Cx On 2018/10/19 16:59
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //更新用户信息：用户信息包括：用户头像URL，用户名
    @PostMapping("/info/{userId}")
    public ResultVO updateUsernameByUserId(@PathVariable Long userId, @RequestBody User user){
        User oldUser = userService.findByUserId(userId);
        if (oldUser == null){
            log.error("[更改用户信息]更新失败，用户id有误，userId={}",userId);
            ResultUtil.error("更新用户信息失败，用户id不存在");
        }
        user.setUserId(userId);
        userService.updateUserByUserId(user);
        return ResultUtil.success();
    }
}
