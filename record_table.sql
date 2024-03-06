create table wmsdb.record
(
    id         int auto_increment
        primary key,
    goods      int           null comment '货品id',
    userId     int           null comment '取货人/补货人',
    admin_id   int           null comment '操作人id',
    count      int           null comment '数量',
    createtime datetime      null comment '操作时间',
    remark     varchar(1000) null comment '备注'
)
    charset = utf8mb3;