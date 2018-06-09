package com.netease.exam.myshop.web.controller;

import com.netease.exam.myshop.service.AccountService;
import com.netease.exam.myshop.service.ProductCRUDService;
import com.netease.exam.myshop.web.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AsyncController {

    @Autowired
    AccountService accountService;

    @Autowired
    ProductCRUDService productCRUDService;

    @RequestMapping(path="/api/buy",method=RequestMethod.POST)
    public Map<String,Object> buyHandler(@RequestBody List<Map<String,String>> buyList)
    {
        Map<String, Object> result = new HashMap<>();
        for(Map<String,String> map : buyList)
        {
            String id = map.get("id");
            String number = map.get("number");

            if(id != null && number != null)
            {
                if(!accountService.checkOut(Integer.valueOf(id),Integer.valueOf(number)))
                {
                    result.put("code",400);
                    result.put("message","购买失败");
                    result.put("result",false);
                    return result;
                }
            }
        }
        result.put("code",200);
        result.put("message","购买成功");
        result.put("result",true);
        return result;
    }

    @RequestMapping(path="/api/delete", method=RequestMethod.POST)
    public Map<String, Object> deleteHandler(@RequestParam("id") int id)
    {
        Product product = new Product();
        product.setId(id);
        int retVal = productCRUDService.deleteProduct(product);

        Map<String, Object> result = new HashMap<>();

        if(retVal != 0)
        {
            result.put("code",200);
            result.put("message","删除成功");
            result.put("result",true);
        }else
        {
            result.put("code",400);
            result.put("message","删除失败");
            result.put("result",false);
        }
        return result;
    }
}
