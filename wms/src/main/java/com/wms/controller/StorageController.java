package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Storage;
import com.wms.entity.User;
import com.wms.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hefa
 * @since 2024-03-05
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Storage storage) {
        return storageService.save(storage) ? Result.suc() : Result.fail();
    }

    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Storage storage) {
        return storageService.saveOrUpdate(storage);
    }

    //删除---new
    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return storageService.removeById(id) ? Result.suc() : Result.fail();
    }

    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Storage storage) {
        return storageService.updateById(storage) ? Result.suc() : Result.fail();
    }

    //查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");

        Page<Storage> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper();

        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Storage::getName, name);
        }

        IPage result = storageService.page(page, lambdaQueryWrapper);
        return Result.suc(result.getRecords(), result.getTotal());
    }

    @GetMapping("/list")
    public Result list() {
        List list = storageService.list();
        return Result.suc(list);
    }
}
