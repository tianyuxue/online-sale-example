package com.netease.exam.myshop.service;

import com.netease.exam.myshop.web.model.Product;

import java.util.List;

public interface ProductCRUDService
{
    /**
     * 通过商品id查找商品
     * @param productId 商品的id
     * @return 如果特定id表示的product不在数据库中，返回null
     */
    Product getProductById(int productId);

    /**
     * 查找到所有商品
     * @return 返回商品列表
     */
    List<Product> getAllProducts();

    /**
     *
     * 更新商品信息
     * @param product 要更新的商品
     * @return 0表示失败
     */
    int updateProduct(Product product);

    /**
     * 增加新商品
     * @param product 要增加的商品
     * @return 返回新增商品的id
     */
    int addProduct(Product product);

    /**
     * 删除product表示的商品
     * @param product 要删除的商品
     * @return 0表示删除失败
     */
    int deleteProduct(Product product);


    /**
     * 查找某一个用户已经购买了的所有商品
     * @param userId 用户id
     * @return 用户购买了的商品列表
     */
    List<Product> getBoughtedProducts(int userId);


    /**
     * 查找当前用户已经购买了的所有商品
     * @return 商品列表
     */
    List<Product> getBoughtedProductsOfCurrentUser();

    /**
     * 为product增加销售信息
     * @param product 商品
     * @return 更新后的商品
     */
    Product fillProductWithSellInfo(Product product);

    /**
     * 为product增加购买信息
     * @param product 商品
     * @return 更新后的商品
     */
    Product fillProductWithPurchaseInfo(Product product);




}
