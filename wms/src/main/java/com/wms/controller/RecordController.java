package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Goods;
import com.wms.entity.Record;
import com.wms.service.GoodsService;
import com.wms.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hefa
 * @since 2024-03-06
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private GoodsService goodsService;

    //增
    @PostMapping("/save")
    public Result save(@RequestBody Record record) {
        record.setCreatetime(LocalDateTime.now());
        Goods goods = goodsService.getById(record.getGoods());
        int n = record.getCount();
        //出库
        if("2".equals(record.getAction())){
            n = -n;
            record.setCount(n);
        }
        //更改已有物品数量
        int newNum = n + goods.getCount();
        if(newNum < 0){
            return Result.fail("物品库存不足!");
        }
        goods.setCount(newNum);
        goodsService.updateById(goods);
        return recordService.save(record) ? Result.suc() : Result.fail();
    }

    //删
    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return recordService.removeById(id) ? Result.suc() : Result.fail();
    }

    //改
    @PostMapping("/update")
    public Result update(@RequestBody Record record) {
        return recordService.updateById(record) ? Result.suc() : Result.fail();
    }

    //查
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        String storage = (String) param.get("storage");
        String goodstype = (String) param.get("goodstype");
        String roleId = (String) param.get("roleId");
        String userId = (String) param.get("userId");

        Page<Record> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        QueryWrapper<Record> QueryWrapper = new QueryWrapper();
        QueryWrapper.apply("a.goods = b.id and c.id = b.storage and d.id = b.goodsType");


        if("2".equals(roleId)){
            QueryWrapper.apply("a.userId=" + userId);
        }
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            QueryWrapper.like("b.name", name);
        }
        if (StringUtils.isNotBlank(storage) && !"null".equals(storage)) {
            QueryWrapper.eq("c.id", storage);
        }
        if (StringUtils.isNotBlank(goodstype) && !"null".equals(goodstype)) {
            QueryWrapper.eq("d.id", goodstype);
        }

        IPage result = recordService.pageCC(page, QueryWrapper);
        return Result.suc(result.getRecords(), result.getTotal());
    }
}
