package com.netease.exam.myshop.web.controller;

import com.netease.exam.myshop.service.AccountService;
import com.netease.exam.myshop.service.ProductCRUDService;
import com.netease.exam.myshop.web.model.Product;
import com.netease.exam.myshop.web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes("user")
public class SyncController
{
    @Resource
    private ProductCRUDService productCRUDService;

    @Resource
    private AccountService accountService;

    @ModelAttribute
    public void getLoginInformation(HttpSession session, Model model)
    {
        // 先尝试从session中获取登录信息, 如果使用缓存，最好从缓存中读取登录信息
        User user = (User) session.getAttribute("user");

        // 如果不能获取，就从数据库中获取
        if(user == null)
            user = accountService.getCurrentUser();

        // 如果获取User失败，就不加入任何数据到model中
        if(user != null)
            model.addAttribute("user", user);
    }

    @RequestMapping(path="/", method= RequestMethod.GET)
    public String index(Model model)
    {
        List<Product> products = productCRUDService.getAllProducts();
        for(Product product : products)
        {
            product = productCRUDService.fillProductWithSellInfo(product);
            product = productCRUDService.fillProductWithPurchaseInfo(product);
        }
        model.addAttribute("productList",products);
        return "index";
    }

    @RequestMapping(path="/show", method= RequestMethod.GET)
    public String getShowPage(@RequestParam("id") int id,
                              Model model)
    {
        Product product = productCRUDService.getProductById(id);
        if(product != null){
            product = productCRUDService.fillProductWithSellInfo(product);
            product = productCRUDService.fillProductWithPurchaseInfo(product);
            model.addAttribute("product", product);
        }
        return "show";
    }

    @RequestMapping(path="/account", method= RequestMethod.GET)
    public String getAccountPage(Model model)
    {
        List<Product> products = productCRUDService.getBoughtedProductsOfCurrentUser();
        for(Product product : products)
        {
            product = productCRUDService.fillProductWithSellInfo(product);
            product = productCRUDService.fillProductWithPurchaseInfo(product);
        }
        model.addAttribute("buyList", products);
        return "account";
    }

    @RequestMapping(path="/public", method= RequestMethod.GET)
    public String getPulicPage()
    {
        return "public";
    }

    @RequestMapping(path="/publicSubmit", method=RequestMethod.POST)
    @Transactional
    public String publicSubmitHandler(@RequestParam("title") String title,
                                      @RequestParam("image") String image,
                                      @RequestParam("detail") String detail,
                                      @RequestParam("price") double price,
                                      @RequestParam("summary") String summary,
                                      Model model){

        //如果直接提供图片地址，需要对url进行过滤, 防止存储型xss
        /*
        if(pic.equals("url")){
            System.out.println("这里需要对image进行url过滤, 防止存储型xss");
        }
        */

        Product product = new Product();
        product.setTitle(title);
        product.setImage(image);
        product.setDetail(detail);
        product.setPrice(price);
        product.setSummary(summary);

        int id = productCRUDService.addProduct(product);
        product.setId(id);
        model.addAttribute("product", product);
        return "publicSubmit";
    }

    @RequestMapping(path="/edit", method=RequestMethod.GET)
    public String editProductHandler(@RequestParam("id") int id,
                                     Model model){
        Product product = productCRUDService.getProductById(id);
        if(product != null)
            model.addAttribute("product", product);
        return "edit";
    }

    @RequestMapping(path="/editSubmit", method=RequestMethod.POST)
    public String editProductSubmitHandler(@RequestParam("id") int id,
                                           @RequestParam("title") String title,
                                           @RequestParam("detail") String detail,
                                           @RequestParam("image") String image,
                                           @RequestParam("price") double price,
                                           @RequestParam("summary") String summary,
                                           Model model){
        //服务端再次检查数据
        if(title.length() > 80 || title.length() < 2 ||
           summary.length() > 140 || summary.length()<2 || detail.length() > 1000 || detail.length()<2)
            return "editSubmit";

        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setImage(image);
        product.setDetail(detail);
        product.setPrice(price);
        product.setSummary(summary);

        int ret = productCRUDService.updateProduct(product);
        if(ret != 0)
            model.addAttribute("product",product);
        return "editSubmit";
    }

    @RequestMapping(path="/settleAccount", method = RequestMethod.GET)
    public String settleAccountHandler(){
        return "settleAccount";
    }
}
