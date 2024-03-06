package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hefa
 * @since 2024-03-06
 */
public interface RecordService extends IService<Record> {
    IPage pageCC(IPage<Record> page, Wrapper wrapper);
}
