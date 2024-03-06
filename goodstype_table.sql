create table wmsdb.goodstype
(
    id     int auto_increment comment '主键'
        primary key,
    name   varchar(100)  not null comment '分类名',
    remark varchar(1000) null comment '备注'
)
    charset = utf8mb3;