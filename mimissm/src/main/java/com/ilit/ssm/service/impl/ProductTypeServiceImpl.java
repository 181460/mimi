package com.ilit.ssm.service.impl;

import com.ilit.ssm.mapper.ProductTypeMapper;
import com.ilit.ssm.pojo.ProductType;
import com.ilit.ssm.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: jjw
 * @create: 2022-04-06-21:10
 */
@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getAll() {
        return productTypeMapper.selectByExample(null);
    }
}
