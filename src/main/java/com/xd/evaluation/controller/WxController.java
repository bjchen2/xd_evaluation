package com.xd.evaluation.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.xd.evaluation.VO.ResultVO;
import com.xd.evaluation.VO.UserVO;
import com.xd.evaluation.domain.User;
import com.xd.evaluation.dto.WxInfo;
import com.xd.evaluation.service.UserService;
import com.xd.evaluation.utils.ResultUtil;
import com.xd.evaluation.utils.UniqueKeyUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 微信小程序有关
 * Created By Cx On 2018/10/4 22:15
 */
@RestController
@RequestMapping("/wechat")
@Slf4j
public class WxController {

    @Autowired
    private WxMaService wxService;
    @Autowired
    private UserService userService;

    /**
     * 登录接口
     */
    @PostMapping("/login")
    public ResultVO login(@RequestBody WxInfo wxInfo) {
        if (StringUtils.isBlank(wxInfo.getCode())) {
            return ResultUtil.error("empty code");
        }

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(wxInfo.getCode());
            log.info(session.getSessionKey());
            log.info(session.getOpenid());
            //存储用户信息
            User user = userService.findByOpenid(session.getOpenid());
            if (user == null) {
                //若用户第一次登录,获取并存储用户信息
                WxMaUserInfo wxUserInfo = wxService.getUserService().getUserInfo(session.getSessionKey(), wxInfo.getEncryptedData(), wxInfo.getIv());
                user = userService.save(new User(UniqueKeyUtil.getNickName(), wxUserInfo.getOpenId(), wxUserInfo.getAvatarUrl()));
                log.info("[用户登录]用户：{}，首次登录，注册成功",session.getOpenid());
            }
            else {
                log.info("[用户登录]用户：{}，登录成功",session.getOpenid());
            }
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return ResultUtil.success(userVO);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return ResultUtil.error(e.getMessage());
        }
    }
}
