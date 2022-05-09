package com.ilit.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ilit.ssm.pojo.ProductInfo;
import com.ilit.ssm.pojo.vo.ProductInfoVo;
import com.ilit.ssm.service.ProductInfoService;
import com.ilit.ssm.utils.FileNameUtil;
import org.apache.ibatis.annotations.Param;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: jjw
 * @create: 2022-04-06-10:25
 */
@Controller
@RequestMapping("/prod")
public class ProductInfoAction {

    public static final int PAGE_SIZE = 5;

    String saveFileName = "";

    @Autowired
    ProductInfoService productInfoService;

    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request){
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list",list);
        return "product";

    }

    /**
     *
     * 显示第一页的五条记录
     */
    @RequestMapping("/split")
    public String split(HttpServletRequest request){
        PageInfo info = null;
        Object vo = request.getSession().getAttribute("prodvo");
        if(vo != null){
            info =  productInfoService.spiltPageVo((ProductInfoVo)vo,PAGE_SIZE);
            request.getSession().removeAttribute("prodvo");
        }else {
            info = productInfoService.splitPage(1, PAGE_SIZE);
        }
        request.setAttribute("info",info);
        return "product";
    }

    /**
     *
     * ajax分页翻页处理
     *
     */
    @ResponseBody
    @RequestMapping("/ajaxsplit")
    public void ajaxSplit(ProductInfoVo vo, HttpSession session){
        PageInfo info = productInfoService.spiltPageVo(vo,PAGE_SIZE);
        session.setAttribute("info",info);
    }

    @ResponseBody
    @RequestMapping("/selectmore")
    public void selectmore(ProductInfoVo productInfoVo, HttpSession session) {
        List<ProductInfo> list = productInfoService.selectMore(productInfoVo);
        session.setAttribute("list", list);
    }

    /**
     * 异步ajax上传
     *
     *
     */
    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request){
        saveFileName = FileNameUtil.getUUIDFileName()+FileNameUtil.getFileType(pimage.getOriginalFilename());
        String path = request.getServletContext().getRealPath("/image_big");
        try {
            pimage.transferTo(new File(path + File.separator+saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject object = new JSONObject();
        try {
            object.put("imgurl",saveFileName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object.toString();
    }
    @RequestMapping("/save")
    public String save(ProductInfo info,HttpServletRequest request){
        info.setpImage(saveFileName);
        info.setpDate(new Date());
        //info对象中有表单提交上来的五个数据
        int num = -1;
        try {
            num = productInfoService.save(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num > 0){
            request.setAttribute("msg","添加成功");
        }else{
            request.setAttribute("msg","增加失败");
        }

        saveFileName = "";


        return "forward:/prod/split.action";
    }
    @RequestMapping("/one")
    public String one(int pid,ProductInfoVo vo, Model model,HttpSession session){
        session.setAttribute("prodvo",vo);
        ProductInfo info = productInfoService.getByID(pid);
        model.addAttribute("prod",info);
        return "update";
    }

    @RequestMapping("/update")
    public String update(ProductInfo info,HttpServletRequest request){
        if(!saveFileName.equals("")){
            info.setpImage(saveFileName);
        }
        int num = -1;
        try {
            num = productInfoService.update(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num > 0){
            request.setAttribute("msg","更新成功");
        }else{
            request.setAttribute("msg","更新失败");
        }
        saveFileName = "";
        return "forward:/prod/split.action";
    }
    @RequestMapping("delete")
    public String delete(Integer pid, ProductInfoVo vo,HttpServletRequest request,HttpSession session){
        session.setAttribute("prodvo", vo);
        int num = -1;
        try {
            num = productInfoService.delete(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num > 0){
            request.setAttribute("msg","删除成功");
        }else{
            request.setAttribute("msg","删除失败");
        }
        return "forward:/prod/split.action";

    }
    @RequestMapping("/deletebatch")
    public String deleteBatch(@RequestParam("str") String pids, HttpServletRequest request){
        String[] ps = pids.split(",");
        int num = 0;
        try {
            num = productInfoService.deleteBatch(ps);
        } catch (Exception e) {
            request.setAttribute("msg","商品不可删除");
        }
        if(num > 0){
            request.setAttribute("msg","批量删除成功");

        }else {
            request.setAttribute("msg","批量删除失败");
        }
        return "forward:/prod/split.action";
    }




}
