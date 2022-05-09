package com.ilit.ssm.service;

import com.ilit.ssm.pojo.ProductType;

import java.util.List;

/**
 * @Description:
 * @author: jjw
 * @create: 2022-04-06-21:08
 */
public interface ProductTypeService {
    /**
     *查询商品类别
     *
     */
    List<ProductType> getAll();

}
