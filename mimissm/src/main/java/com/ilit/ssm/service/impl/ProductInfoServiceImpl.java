package com.ilit.ssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ilit.ssm.mapper.ProductInfoMapper;
import com.ilit.ssm.pojo.ProductInfo;
import com.ilit.ssm.pojo.ProductInfoExample;
import com.ilit.ssm.pojo.vo.ProductInfoVo;
import com.ilit.ssm.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: jjw
 * @create: 2022-04-06-10:17
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAll() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    @Override
    public PageInfo splitPage(int pageNum, int PageSize) {
        PageHelper.startPage(pageNum, PageSize);
        ProductInfoExample example = new ProductInfoExample();
        example.setOrderByClause("p_id desc");
        //设置完排序后，取集合
        List<ProductInfo> list = productInfoMapper.selectByExample(example);
        //将查到的集合封装
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public int save(ProductInfo info) {
        return productInfoMapper.insert(info);
    }

    @Override
    public ProductInfo getByID(int pid) {
        return productInfoMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int update(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKey(info);
    }

    @Override
    public int delete(Integer pid) {
        return productInfoMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return productInfoMapper.deleteBatch(ids);

    }

    @Override
    public List<ProductInfo> selectMore(ProductInfoVo productInfoVo) {

        return productInfoMapper.selectCondition(productInfoVo);
    }

    @Override
    public PageInfo<ProductInfo> spiltPageVo(ProductInfoVo vo, int pagSize) {
        PageHelper.startPage(vo.getPage(), pagSize);
        List<ProductInfo> list = productInfoMapper.selectCondition(vo);
        return new PageInfo<>(list);
    }

}
