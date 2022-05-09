package com.ilit.ssm.service;

import com.github.pagehelper.PageInfo;
import com.ilit.ssm.pojo.ProductInfo;
import com.ilit.ssm.pojo.vo.ProductInfoVo;

import java.util.List;

/**
 * @Description:
 * @author: jjw
 * @create: 2022-04-06-10:15
 */
public interface ProductInfoService {
    /**
     * 显示所有商品
     * 不分页
     *
     *
     */
    List<ProductInfo> getAll();

    /**
     *
     * 分页功能
     *
     *
     */
     PageInfo splitPage(int pageNum,int PageSize);



    /**
     *
     * 保存商品信息
     *
     *
     */
    int save(ProductInfo info);

    /**
     *
     * 按主键ID查询商品
     *
     */
    ProductInfo getByID(int pid);
    /**
     *
     *
     * 更新商品
     */
    int update(ProductInfo info);

    /**
     *
     * 根据商品ID删除商品
     */
    int delete(Integer pid);

    /**
     *
     *
     * 批量删除商品
     */
    int deleteBatch(String[] ids);

    /**
     *
     *
     * 多条件查询
     */
    List<ProductInfo> selectMore(ProductInfoVo productInfoVo);

    /**
     *
     *
     * 多条件查询分页
     */
    PageInfo spiltPageVo(ProductInfoVo vo,int pageSize);

}
