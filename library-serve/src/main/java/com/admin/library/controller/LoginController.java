package com.admin.library.controller;

import com.admin.library.common.base.BaseController;
import com.admin.library.common.base.R;
import com.admin.library.domain.LoginBody;
import com.admin.library.domain.LoginUser;
import com.admin.library.domain.User;
import com.admin.library.domain.entity.Admin;
import com.admin.library.domain.entity.Reader;
import com.admin.library.service.AdminService;
import com.admin.library.service.ReaderService;
import com.admin.library.utils.UUIDUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录管理
 *
 * @author SouthMaple
 * @since 2021-04-09 09:17:58
 */
@RestController
public class LoginController extends BaseController {
    @Resource
    private AdminService adminService;
    @Resource
    private ReaderService readerService;

    /**
     * 登录
     *
     * @param loginBody 登录信息
     * @return 随机字符串uuid
     */
    @PostMapping("/login")
    public R login(@RequestBody LoginBody loginBody) {
        String username = loginBody.getUsername();
        String password = loginBody.getPassword();
        String role = loginBody.getRole();
        Object user = null;

        if (!StringUtils.isEmpty(role)) {
            if ("reader".equals(role)) {
                // 读者登录
                QueryWrapper<Reader> wrapper = new QueryWrapper<>();
                wrapper.lambda().eq(Reader::getUsername, username).eq(Reader::getPassword, password);
                user = readerService.getOne(wrapper);
            } else if ("admin".equals(role)) {
                // 管理员登录
                QueryWrapper<Admin> wrapper = new QueryWrapper<>();
                wrapper.lambda().eq(Admin::getUsername, username).eq(Admin::getPassword, password);
                user = adminService.getOne(wrapper);
            }
        }

        if (user != null) { // 登录成功
            String uuid = UUIDUtils.getUUID();
            Long userId = "admin".equals(role) ? ((Admin) user).getId() : ((Reader) user).getReaderId();
            LoginUser.addUser(uuid, role, userId);
            LoginUser.addVisitCount(); // 访问数量加1
            return result(uuid, "登录成功!");
        } else {
            return result(R.ERROR, "登录失败! 账号或密码错误!");
        }
    }

    /**
     * 获取用户信息
     *
     * @param token 令牌
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public R getInfo(String token) {
        if (!StringUtils.isEmpty(token)) {
            User u = LoginUser.getUserByToken(token);
            if (u != null) {
                // token验证成功
                String role = u.getRole();
                Long userId = u.getUserId();
                Object user = null;

                if ("reader".equals(role)) {
                    // 获取读者信息
                    QueryWrapper<Reader> wrapper = new QueryWrapper<>();
                    wrapper.lambda().eq(Reader::getReaderId, userId);
                    user = readerService.getOne(wrapper);
                } else if ("admin".equals(role)) {
                    // 获取管理员信息
                    QueryWrapper<Admin> wrapper = new QueryWrapper<>();
                    wrapper.lambda().eq(Admin::getId, userId);
                    user = adminService.getOne(wrapper);
                }
                return result(user);
            }
        }
        return result(R.SUCCESS, "暂未登录!");
    }

    /**
     * 获取访问数量
     *
     * @return 访问数量
     */
    @GetMapping("/visitCount")
    public R visitCount() {
        return result(LoginUser.getVisitCount());
    }
}
