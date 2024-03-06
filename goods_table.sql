create table wmsdb.goods
(
    id        int auto_increment
        primary key,
    name      varchar(100)  not null comment '物品名',
    storage   int           not null comment '仓库',
    goodsType int           not null comment '分类',
    count     int           null comment '数量',
    remark    varchar(1000) null comment '备注'
)
    charset = utf8mb3;