package com.yjq.programmer.controller.admin;

import com.yjq.programmer.constant.SessionConstant;
import com.yjq.programmer.dao.admin.AdminMapper;
import com.yjq.programmer.dao.admin.AuthorityMapper;
import com.yjq.programmer.dao.admin.MenuMapper;
import com.yjq.programmer.enums.MenuStateEnum;
import com.yjq.programmer.pojo.admin.Admin;
import com.yjq.programmer.pojo.admin.Authority;
import com.yjq.programmer.pojo.admin.Menu;
import com.yjq.programmer.pojo.common.User;
import com.yjq.programmer.service.admin.IMenuService;
import com.yjq.programmer.service.common.ICommentService;
import com.yjq.programmer.service.common.IUserService;
import com.yjq.programmer.util.StringUtil;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-18 20:30
 */

/**
 * 后台管理系统用户控制器
 * @author 82320
 *
 */
@RequestMapping("/admin/user")
@Controller
public class UserController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICommentService commentService;

    /**
     * 用户列表页面
     * @param model
     * @param id
     * @param request
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/index",method= RequestMethod.GET)
    public String index(Model model, Integer id, HttpServletRequest request, String content,
                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(required = false, defaultValue = "5") Integer pageSize //每页5个数据
    )  {
        //获取列表展示有关信息
        if(StringUtil.isEmpty(content)) {
            //如果查询信息为空
            model.addAttribute("PageInfo", userService.getUserByPage(pageNum, pageSize).getData());
        }else {
            model.addAttribute("PageInfo", userService.getUserByPageAndContent(pageNum, pageSize, content).getData());
            model.addAttribute("content",content);
        }
        //获取路径上有关信息
        Menu selectByPrimaryKey = menuMapper.selectByPrimaryKey(id);
        if(selectByPrimaryKey == null) {
            return "error/404";
        }
        Admin loginedAdmin = (Admin) request.getSession().getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
        List<Authority> selectByRoleId = authorityMapper.selectByRoleId(loginedAdmin.getRoleId()); //获取当前用户所有权限
        Set<Integer> menuIdSet = selectByRoleId.stream().map(Authority :: getMenuId).collect(Collectors.toSet());//把权限中所有菜单id取出来
        List<Menu> allMenusByStateAndPrimaryKeys = menuMapper.selectByStateAndPrimaryKeys(MenuStateEnum.OPEN.getCode(), menuIdSet);
        model.addAttribute("allAdmins", adminMapper.selectAll());
        model.addAttribute("onThirdMenus", menuService.getThirdMenus(allMenusByStateAndPrimaryKeys).getData());
        model.addAttribute("parentMenu", menuMapper.selectByPrimaryKey(selectByPrimaryKey.getParentId()));
        model.addAttribute("currentMenu", selectByPrimaryKey);
        return "admin/user/index";
    }


    /**
     * 用户评论列表页面
     * @param model
     * @param id
     * @param request
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/comment",method= RequestMethod.GET)
    public String comment(Model model, Integer id, HttpServletRequest request, String content,
                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(required = false, defaultValue = "5") Integer pageSize //每页5个数据
    )  {
        //获取列表展示有关信息
        if(StringUtil.isEmpty(content)) {
            //如果查询信息为空
            model.addAttribute("PageInfo", commentService.selectByPage(pageNum, pageSize).getData());
        }else {
            model.addAttribute("PageInfo", commentService.selectByPageAndSearchContent(content, pageNum, pageSize).getData());
            model.addAttribute("content",content);
        }
        //获取路径上有关信息
        Menu selectByPrimaryKey = menuMapper.selectByPrimaryKey(id);
        if(selectByPrimaryKey == null) {
            return "error/404";
        }
        Admin loginedAdmin = (Admin) request.getSession().getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
        List<Authority> selectByRoleId = authorityMapper.selectByRoleId(loginedAdmin.getRoleId()); //获取当前用户所有权限
        Set<Integer> menuIdSet = selectByRoleId.stream().map(Authority :: getMenuId).collect(Collectors.toSet());//把权限中所有菜单id取出来
        List<Menu> allMenusByStateAndPrimaryKeys = menuMapper.selectByStateAndPrimaryKeys(MenuStateEnum.OPEN.getCode(), menuIdSet);
        model.addAttribute("allAdmins", adminMapper.selectAll());
        model.addAttribute("onThirdMenus", menuService.getThirdMenus(allMenusByStateAndPrimaryKeys).getData());
        model.addAttribute("parentMenu", menuMapper.selectByPrimaryKey(selectByPrimaryKey.getParentId()));
        model.addAttribute("currentMenu", selectByPrimaryKey);
        return "admin/user/comment";
    }



    /**
     * 修改用户密码页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value="/edit_passwd",method=RequestMethod.GET)
    public String editPasswd(Model model,Long id) {
        User user = userService.selectByPrimaryKey(id);
        if(user == null) {
            return "error/404";
        }
        model.addAttribute("User", user);
        return "admin/user/edit_passwd";
    }


    /**
     * 修改用户密码操作处理
     * @param passwd
     * @param userId
     * @return
     */
    @RequestMapping(value="/edit_passwd",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> editPasswd(String passwd, Long userId){
        return userService.updateUserPasswd(passwd, userId);
    }

    /**
     * 删除用户操作处理
     * @param userId
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> delete(Long userId){
        return userService.deleteUser(userId);
    }

}
