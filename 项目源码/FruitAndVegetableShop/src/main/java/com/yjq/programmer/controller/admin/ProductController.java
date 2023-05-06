package com.yjq.programmer.controller.admin;

import com.yjq.programmer.constant.SessionConstant;
import com.yjq.programmer.dao.admin.AdminMapper;
import com.yjq.programmer.dao.admin.AuthorityMapper;
import com.yjq.programmer.dao.admin.MenuMapper;
import com.yjq.programmer.dao.common.ProductCategoryMapper;
import com.yjq.programmer.dao.common.ProductMapper;
import com.yjq.programmer.enums.MenuStateEnum;
import com.yjq.programmer.pojo.admin.Admin;
import com.yjq.programmer.pojo.admin.Authority;
import com.yjq.programmer.pojo.admin.Menu;
import com.yjq.programmer.pojo.common.Product;
import com.yjq.programmer.service.admin.IMenuService;
import com.yjq.programmer.service.common.IProductService;
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
 * @create 2020-11-12 15:51
 */

/**
 * 后台管理系统商品控制器
 * @author 82320
 *
 */
@RequestMapping("/admin/product")
@Controller
public class ProductController {
    @Autowired
    private IMenuService menuService;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private IProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * 商品列表页面
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
            model.addAttribute("PageInfo", productService.getProductByPage(pageNum, pageSize).getData());
        }else {
            model.addAttribute("PageInfo", productService.getProductByPageAndContent(pageNum, pageSize, content).getData());
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
        return "admin/product/index";
    }

    /**
     * 商品添加页面
     * @param model
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("productCategoryList", productCategoryMapper.selectAll());
        return "admin/product/add";
    }

    /**
     * 商品编辑页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.GET)
    public String edit(Model model,Long id) {
        Product selectByPrimaryKey = productMapper.selectByPrimaryKey(id);
        if(selectByPrimaryKey == null) {
            return "error/404";
        }
        model.addAttribute("productCategoryList", productCategoryMapper.selectAll());
        model.addAttribute("Product", selectByPrimaryKey);
        return "admin/product/edit";
    }


    /**
     * 添加商品操作处理
     * @param product
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> add(Product product){
        return productService.add(product);
    }


    /**
     * 编辑商品操作处理
     * @param product
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> edit(Product product){
        return productService.edit(product);
    }

    /**
     * 删除商品操作处理
     * @param id
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> delete(Long id){
        return productService.delete(id);
    }
}
