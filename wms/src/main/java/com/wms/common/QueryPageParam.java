package com.wms.common;

import lombok.Data;

import java.util.HashMap;
/*
* 分页查询参数
* */
@Data
public class QueryPageParam {
    //默认 一页20行，起始第一页
    private static int PAGE_SIZE=10;
    private static int PAGE_NUM=1;

    private int pageSize=PAGE_SIZE;
    private int pageNum=PAGE_NUM;

    private HashMap param = new HashMap();
}
