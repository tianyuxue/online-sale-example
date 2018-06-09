package com.netease.exam.myshop.dao;


import com.netease.exam.myshop.meta.Content;
import com.netease.exam.myshop.meta.Trx;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrxDao extends GenericDao<Trx, Integer> {
    /**
     * 返回某一个用户购买的所有商品
     * @param personId 用户id
     * @return 所购买了的所有商品列表
     */
    List<Content> getContentsOfTargetPerson(int personId);

    /**
     * 返回特定用户涉及到的所有交易
     * @param personId
     * @return
     */
    List<Trx> getTrxsOfTargetPerson(int personId);

    /**
     * 返回特定商品涉及到的所有交易
     * @param contentId
     * @return
     */
    List<Trx> getTrxsOfTargetContent(int contentId);

    /**
     * 返回特定用户和商品涉及到的所有交易
     * @param personId
     * @param contentId
     * @return
     */
    List<Trx> getTrxsOfTargetPersonAndContent(int personId, int contentId);
}
