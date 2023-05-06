package com.yjq.programmer.controller.admin;

import com.yjq.programmer.constant.SessionConstant;
import com.yjq.programmer.dao.admin.AdminMapper;
import com.yjq.programmer.dao.admin.AuthorityMapper;
import com.yjq.programmer.dao.admin.MenuMapper;
import com.yjq.programmer.dao.common.ProductCategoryMapper;
import com.yjq.programmer.enums.MenuStateEnum;
import com.yjq.programmer.pojo.admin.Admin;
import com.yjq.programmer.pojo.admin.Authority;
import com.yjq.programmer.pojo.admin.Menu;
import com.yjq.programmer.pojo.common.ProductCategory;
import com.yjq.programmer.service.admin.IMenuService;
import com.yjq.programmer.service.common.IProductCategoryService;
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
 * @create 2020-11-12 9:46
 */

/**
 * 后台管理系统商品种类控制器
 * @author 82320
 *
 */
@RequestMapping("/admin/product_category")
@Controller
public class ProductCategoryController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private IProductCategoryService productCategoryService;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * 商品种类列表页面
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
            model.addAttribute("PageInfo", productCategoryService.getProductCategoryByPage(pageNum, pageSize).getData());
        }else {
            model.addAttribute("PageInfo", productCategoryService.getProductCategoryByPageAndContent(pageNum, pageSize, content).getData());
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
        return "admin/product_category/index";
    }

    /**
     * 商品种类添加页面
     * @param model
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public String add(Model model) {
        return "admin/product_category/add";
    }

    /**
     * 商品种类编辑页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.GET)
    public String edit(Model model,Long id) {
        ProductCategory selectByPrimaryKey = productCategoryMapper.selectByPrimaryKey(id);
        if(selectByPrimaryKey == null) {
            return "error/404";
        }
        model.addAttribute("ProductCategory", selectByPrimaryKey);
        return "admin/product_category/edit";
    }



    /**
     * 添加商品种类操作处理
     * @param productCategory
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> add(ProductCategory productCategory){
        return productCategoryService.add(productCategory);
    }

    /**
     * 修改商品种类操作处理
     * @param productCategory
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> edit(ProductCategory productCategory){
        return productCategoryService.edit(productCategory);
    }

    /**
     * 删除商品种类操作处理
     * @param id
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> delete(Long id){
        return productCategoryService.delete(id);
    }
}
