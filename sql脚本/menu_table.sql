create table wmsdb.menu
(
    id             int          not null
        primary key,
    menuCode       varchar(8)   null comment '菜单编号',
    menuName       varchar(16)  null comment '菜单名',
    menuLevel      varchar(2)   null comment '菜单级别',
    menuParentCode varchar(8)   null comment '菜单的父Code',
    menuClick      varchar(16)  null comment '单击触发函数',
    menuRight      varchar(8)   null comment '权限 0:超级管理员, 1:管理员, 2:普通用户 可用逗号组合',
    menuComponent  varchar(200) null,
    menuIcon       varchar(100) null
)
    charset = utf8mb3;