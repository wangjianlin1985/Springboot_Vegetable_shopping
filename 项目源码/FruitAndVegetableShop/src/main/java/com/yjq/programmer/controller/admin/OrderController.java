package com.yjq.programmer.controller.admin;

import com.yjq.programmer.constant.SessionConstant;
import com.yjq.programmer.dao.admin.AdminMapper;
import com.yjq.programmer.dao.admin.AuthorityMapper;
import com.yjq.programmer.dao.admin.MenuMapper;
import com.yjq.programmer.enums.MenuStateEnum;
import com.yjq.programmer.pojo.admin.Admin;
import com.yjq.programmer.pojo.admin.Authority;
import com.yjq.programmer.pojo.admin.Menu;
import com.yjq.programmer.service.admin.IMenuService;
import com.yjq.programmer.service.common.IOrderService;
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
 * @create 2020-11-19 10:20
 */

/**
 * 后台管理系统订单控制器
 * @author 82320
 *
 */
@RequestMapping("/admin/order")
@Controller
public class OrderController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private IOrderService orderService;


    /**
     * 订单列表页面
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
            model.addAttribute("PageInfo", orderService.selectByPage(pageNum, pageSize).getData());
        }else {
            model.addAttribute("PageInfo", orderService.selectByPageAndContent(Long.valueOf(content), pageNum, pageSize).getData());
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
        return "admin/order/index";
    }

    /**
     * 订单详情查看页面
     * @param model
     * @param orderId
     * @return
     */
    @RequestMapping(value="/view",method=RequestMethod.GET)
    public String view(Model model, Long orderId) {
        model.addAttribute("OrderItemList", orderService.getOrderItemByOrderId(orderId));
        model.addAttribute("Order", orderService.selectByPrimaryKey(orderId));
        return "admin/order/view";
    }

    /**
     * 修改订单状态页面
     * @param model
     * @param orderId
     * @return
     */
    @RequestMapping(value="/edit_state",method=RequestMethod.GET)
    public String editState(Model model, Long orderId) {
        model.addAttribute("Order", orderService.selectByPrimaryKey(orderId));
        return "admin/order/edit_state";
    }

    /**
     * 修改订单状态操作处理
     * @param state
     * @param orderId
     * @return
     */
    @RequestMapping(value="/edit_state",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> editState(Integer state, Long orderId){
        return orderService.updateOrderState(orderId, state);
    }

    /**
     * 删除订单操作处理
     * @param orderId
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> delete(Long orderId){
        return orderService.deleteOrder(orderId);
    }



}
