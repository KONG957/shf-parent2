package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.HouseImage;
import com.atguigu.result.Result;
import com.atguigu.service.HouseImageService;
import com.atguigu.util.QiniuUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.controller
 * @className: HouseImageController
 * @author: kong
 * @description: TODO
 * @date: 2023/5/25 10:58
 * @version: 1.0
 */
@Controller
@RequestMapping("/houseImage")
public class HouseImageController extends BaseController {

    @Reference
    private HouseImageService houseImageService;

    private final static String LIST_ACTION = "redirect:/house/";
    private final static String PAGE_UPLOED_SHOW = "house/upload";

    @GetMapping("/uploadShow/{houseId}/{type}")
    public String uploadShow(ModelMap model, @PathVariable("houseId") Long houseId, @PathVariable("type") Long type) {
        model.addAttribute("houseId", houseId);
        model.addAttribute("type", type);
        return PAGE_UPLOED_SHOW;
    }

    @PostMapping("/upload/{houseId}/{type}")
    @ResponseBody
    public Result upload(@PathVariable Long houseId, @PathVariable Integer type, @RequestParam(value = "file") MultipartFile[] files) throws Exception {
        if (files.length>0){
            for (MultipartFile file : files) {
                String newFileName = UUID.randomUUID().toString();
                QiniuUtils.upload2Qiniu(file.getBytes(),newFileName);
                String url = "http://rv6zcix7s.hd-bkt.clouddn.com/"+newFileName;
                HouseImage houseImage = new HouseImage();
                houseImage.setImageName(newFileName);
                houseImage.setHouseId(houseId);
                houseImage.setImageUrl(url);
                houseImage.setType(type);
                houseImageService.insert(houseImage);
            }
        }

        return Result.ok();
    }

    @RequestMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable("houseId") Long houseId,@PathVariable("id") Long id){
        HouseImage houseImage = houseImageService.getById(id);
        QiniuUtils.deleteFileFromQiniu(houseImage.getImageName());
        houseImageService.delete(id);

        return LIST_ACTION+houseId;
    }
}