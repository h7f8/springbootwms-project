package com.wms.common;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;
/*
* 后端返回结果封装
* */
@Data
public class Result {
    private int code; //编码 200/400
    private String msg; //  成功Or失败
    private Long total; //总条数
    private Object data;//数据

    public static Result fail(){
        return result(400, "失败", 0L, null);
    }
    public static Result suc(){
        return result(200, "成功", 0L, null);
    }
    public static Result suc(Object data){
        return result(200,"成功",0L,data);
    }
    public static Result suc(Object data, Long total){
        return result(200, "成功", total, data);
    }

    private static Result result(int code, String msg, Long total, Object data){
        Result res = new Result();
        res.setCode(code); res.setMsg(msg); res.setData(data); res.setTotal(total);
        return res;
    }
}
