package com.netease.exam.myshop.service.impl;

import com.netease.exam.myshop.dao.ContentDao;
import com.netease.exam.myshop.dao.TrxDao;
import com.netease.exam.myshop.meta.Content;
import com.netease.exam.myshop.meta.Person;
import com.netease.exam.myshop.meta.Trx;
import com.netease.exam.myshop.service.ProductCRUDService;
import com.netease.exam.myshop.utils.SecurityUtils;
import com.netease.exam.myshop.web.model.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCRUDServiceImpl implements ProductCRUDService {

    @Resource
    private ContentDao contentDao;

    @Resource
    private TrxDao trxDao;

    @Override
    public Product getProductById(int productId)
    {
        Content content = contentDao.findById(productId);
        if(content == null)
            return null;
        return fillProductWithContentInfo(content);
    }

    @Override
    public List<Product> getAllProducts()
    {
        List<Content> contents = contentDao.findAll();
        List<Product> products = new ArrayList<>();
        for(Content content : contents)
        {
            products.add(fillProductWithContentInfo(content));
        }
        return products;
    }

    @Override
    public int updateProduct(Product product)
    {
        Content content = fillContentWithProduct(product);
        return contentDao.updateEntity(content);
    }

    @Override
    public int addProduct(Product product) {
        Content content = fillContentWithProduct(product);
        contentDao.addEntity(content);
        return content.getContentId();
    }

    @Override
    public int deleteProduct(Product product) {
        Content content = fillContentWithProduct(product);
        return contentDao.deleteEntity(content);
    }

    @Override
    public List<Product> getBoughtedProducts(int userId) {
        List<Content> contents = trxDao.getContentsOfTargetPerson(userId);
        List<Product> products = new ArrayList<>();
        for(Content content : contents)
        {
            products.add(fillProductWithContentInfo(content));
        }
        return products;
    }

    @Override
    public List<Product> getBoughtedProductsOfCurrentUser()
    {
        return getBoughtedProducts(SecurityUtils.getCurrentUser().getUserId());
    }

    @Override
    public Product fillProductWithSellInfo(Product product)
    {
        int numofSold = numOfSold(product.getId());
        product.setIsSell(numofSold!=0);
        product.setSaleNum(numofSold);
        return product;
    }

    //这里存有疑问,是否应该增加购买字段num
    @Override
    public Product fillProductWithPurchaseInfo (Product product)
    {
        Person currentUser = SecurityUtils.getCurrentUser();
        if(currentUser != null && currentUser.getUserType() == 0)
        {
            List<Trx> trxs = trxDao.getTrxsOfTargetPersonAndContent(
                    currentUser.getUserId(),
                    product.getId());
            if(trxs.size() == 0)
                product.setIsBuy(false);
            else
            {
                product.setIsBuy(true);
                product.setBuyNum(trxs.size());
                product.setBuyPrice(trxs.get(0).getPrice()*product.getBuyNum());
                product.setBuyTime(trxs.get(0).getTime().toEpochMilli());
                //product.setTotal(product.getBuyPrice()*product.getBuyNum());
            }
        }
        return product;
    }

//================================================下面是私有方法=========================================//
    /**
     * 特定商品卖出的数量,用于卖家查看, 0表示未卖出
     * @param productId 商品id
     * @return 卖出的商品的数量
     */
    private int numOfSold(int productId) {
        List<Trx> trxs = trxDao.getTrxsOfTargetContent(productId);
        return trxs.size();
    }

    /**
     * 通过content和当前登录的用户构造前端使用product
     * @param content 商品
     * @return 新创建的用户对象
     */
    private Product fillProductWithContentInfo(Content content)
    {
        Product product = new Product();
        product.setId(content.getContentId());
        product.setTitle(content.getTitle());
        product.setSummary(content.getContentAbstract());
        product.setDetail(content.getText());
        product.setImage(content.getIcon());
        product.setPrice(content.getPrice());
        return product;
    }

    /**
     * 根据product填充content,并返回
     * @param product 商品
     * @return 新的content实例，包含product中的相信息
     */
    private Content fillContentWithProduct(Product product)
    {
        Content content = new Content();
        content.setContentId(product.getId());
        content.setTitle(product.getTitle());
        content.setContentAbstract(product.getSummary());
        content.setIcon(product.getImage());
        content.setText(product.getDetail());
        content.setPrice(product.getPrice());
        return content;
    }
}
