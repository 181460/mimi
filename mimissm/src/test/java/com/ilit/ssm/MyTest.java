package com.ilit.ssm;

import com.ilit.ssm.mapper.ProductInfoMapper;
import com.ilit.ssm.pojo.ProductInfo;
import com.ilit.ssm.pojo.vo.ProductInfoVo;
import com.ilit.ssm.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Description:
 * @author: jjw
 * @create: 2022-04-05-21:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_dao.xml", "classpath:applicationContext_service.xml"})
public class MyTest {
    @Autowired
    ProductInfoMapper productInfoMapper;
    public static void swap(int a,int b){
        int temp =a;
        a = b;
        b = temp;
    }
    @Test
    public void testMD5(){
        String mi = MD5Util.getMD5("000000");
        System.out.println("mi = " + mi);
    }
    @Test
    public  void test1() {
        int a = 2;
        int b = 3;
        swap(a, b);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        Integer c = 100;
        Integer d = 100;
        Integer e = 200;
        Integer f = 200;
        System.out.println(c==d);
        System.out.println(e==f);
    }
    @Test
    public void test2(){
        ProductInfoVo vo = new ProductInfoVo();
        vo.setPname("4");
        vo.setTypeid(3);
        vo.setLprice(2000);
        vo.setHprice(4000);
        List<ProductInfo> list = productInfoMapper.selectCondition(vo);
        list.forEach(ProductInfo -> System.out.println(ProductInfo));
    }
}
