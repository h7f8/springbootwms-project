package com.wms.entity;

import lombok.Data;

@Data
public class RecordRes extends Record{
    //返回联表查询结果
    private String username;
    private String adminname;
    private String goodsname;
    private String storagename;
    private String goodstype;
}
