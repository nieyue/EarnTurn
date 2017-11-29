# 数据库 
#创建数据库
DROP DATABASE IF EXISTS earn_turn_db;
CREATE DATABASE earn_turn_db;

#使用数据库 
use earn_turn_db;

#创建角色表
CREATE TABLE role_tb(
role_id int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
name varchar(255) COMMENT '角色名',
duty varchar(255) COMMENT '角色职责',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (role_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='角色表';

#创建账户表 
CREATE TABLE acount_tb(
acount_id int(11) NOT NULL AUTO_INCREMENT COMMENT '账户id',
nickname varchar(255) COMMENT '昵称',
icon varchar(255) COMMENT '图像',
scale decimal(11,2) DEFAULT 0 COMMENT '合伙人收益比例',
openid varchar(255) COMMENT 'openid',
uuid varchar(255) COMMENT 'uuid',
sex tinyint(4) DEFAULT 0 COMMENT '性别,默认为0未知，为1男性，为2女性',
country varchar(255) COMMENT '国家',
province varchar(255) COMMENT '省',
city varchar(255) COMMENT '城市',
realname varchar(255) COMMENT '真实姓名',
phone varchar(255) COMMENT '电话',
email varchar(255) COMMENT 'email',
password varchar(255) COMMENT '密码',
identity_cards varchar(255) COMMENT '身份证',
qq varchar(255) COMMENT 'QQ',
wechat varchar(255) COMMENT '微信号',
microblog varchar(255) COMMENT '微博',
alipay varchar(255) COMMENT '支付宝账号',
create_date datetime COMMENT '创建时间',
login_date datetime COMMENT '登陆时间',
status tinyint DEFAULT 0 COMMENT '状态，默认0正常，1锁定，2，异常',
spread_id int(11) COMMENT '推广id',
master_id int(11) COMMENT '师傅id',
role_id int(11) COMMENT '角色id外键',
PRIMARY KEY (acount_id),
INDEX INDEX_SPREADID (spread_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='账户表';

#创建财务表 
CREATE TABLE finance_tb(
finance_id int(11) NOT NULL AUTO_INCREMENT COMMENT '财务id',
money decimal(11,2) DEFAULT 0 COMMENT '余额',
recharge decimal(11,2) DEFAULT 0 COMMENT '充值金额',
consume decimal(11,2) DEFAULT 0 COMMENT '消费金额',
withdrawals decimal(11,2) DEFAULT 0 COMMENT '提现金额',
partner_profit decimal(11,2) DEFAULT 0 COMMENT '合伙人总收益',
base_profit decimal(11,2) DEFAULT 0 COMMENT '基准收益',
bank_user_name varchar(255) COMMENT '开户人',
bank_name varchar(255) COMMENT '开户银行',
bank_account varchar(255) COMMENT '银行账号',
bank_address varchar(255) COMMENT '开户银行地址',
update_date datetime COMMENT '更新时间',
acount_id int(11) COMMENT '账户id外键',
PRIMARY KEY (finance_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='财务表';

#创建财务明细表 
CREATE TABLE finance_details_tb(
finance_details_id int(11) NOT NULL AUTO_INCREMENT COMMENT '财务明细id',
type tinyint(4) DEFAULT 0 COMMENT '类型,0提现/1充值',
money decimal(11,2) DEFAULT 0 COMMENT '每次提现/充值金额',
status varchar(255) COMMENT '状态',
update_date datetime COMMENT '更新时间',
finance_id int(11) COMMENT '财务id外键',
PRIMARY KEY (finance_details_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='财务明细表';

#创建公告表 
CREATE TABLE notice_tb(
notice_id int(11) NOT NULL AUTO_INCREMENT COMMENT '公告id',
title varchar(255) COMMENT '标题',
content varchar(255) COMMENT '内容',
create_date datetime COMMENT '创建时间',
PRIMARY KEY (notice_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='公告表';

#创建学堂表 
CREATE TABLE school_tb(
school_id int(11) NOT NULL AUTO_INCREMENT COMMENT '学堂id',
title varchar(255) COMMENT '标题',
content longtext COMMENT '内容',
create_date datetime COMMENT '创建时间',
PRIMARY KEY (school_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='学堂表';

#创建意见反馈表 
CREATE TABLE feedback_tb(
feedback_id int(11) NOT NULL AUTO_INCREMENT COMMENT '意见反馈id',
content varchar(255) COMMENT '内容',
create_date datetime COMMENT '创建时间',
acount_id int(11) COMMENT '提交人账户id外键',
PRIMARY KEY (feedback_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='意见反馈表';

#创建文章表 
CREATE TABLE article_tb(
article_id int(11) NOT NULL AUTO_INCREMENT COMMENT '文章id',
type varchar(255) COMMENT '类型',
title varchar(255) COMMENT '标题',
is_recommend tinyint(4) DEFAULT 0 COMMENT '是否推荐 默认0否',
fixed_recommend tinyint(4) DEFAULT 0 COMMENT '是否置顶 默认0否',
redirect_url varchar(255)  COMMENT '跳转url',
content longtext  COMMENT '内容',
model varchar(255) COMMENT '计费模式',
user_unit_price  decimal(11,2) DEFAULT 0 COMMENT '用户单价',
unit_price decimal(11,2) DEFAULT 0 COMMENT '单价',
total_price decimal(11,2) DEFAULT 0 COMMENT '总价',
turn_number bigint(20) DEFAULT 0 COMMENT '转发数',
reading_number bigint(20) DEFAULT 0 COMMENT '阅读数',
now_total_price decimal(11,2) DEFAULT 0 COMMENT '已消耗金额',
user_now_total_price decimal(11,2) DEFAULT 0 COMMENT '用户收益',
scale decimal(11,2) DEFAULT 0 COMMENT '扣量比例默认为0',
pvs bigint(20) DEFAULT 0 COMMENT '总pv数',
uvs bigint(20) DEFAULT 0 COMMENT '总uv数',
ips bigint(20) DEFAULT 0 COMMENT '总ip数',
status varchar(255) COMMENT '状态',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
acount_id int(11) COMMENT '账户id外键',
PRIMARY KEY (article_id),
INDEX INDEX_TYPE (type) USING BTREE,
INDEX INDEX_ISRECOMMEND (is_recommend) USING BTREE,
INDEX INDEX_FIXEDRECOMMEND (fixed_recommend) USING BTREE,
INDEX INDEX_STATUS (status) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='文章表';

#创建图片表 
CREATE TABLE img_tb(
img_id int(11) NOT NULL AUTO_INCREMENT COMMENT '图片id',
img_address varchar(255) COMMENT '图片地址',
number int(11) COMMENT '图片顺序',
article_id int(11) COMMENT '文章id外键',
PRIMARY KEY (img_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='图片表';

#创建数据表 
CREATE TABLE data_tb(
data_id int(11) NOT NULL AUTO_INCREMENT COMMENT '数据id',
pvs bigint(20) COMMENT 'pvs',
uvs bigint(20) COMMENT 'uvs',
ips bigint(20) COMMENT 'ips',
create_date datetime COMMENT '创建时间',
article_id int(11) COMMENT '文章id外键',
acount_id int(11) COMMENT '账户id外键',
PRIMARY KEY (data_id),
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_ARTICLEID (article_id) USING BTREE,
INDEX INDEX_ACOUNTID (acount_id) USING BTREE,
UNIQUE INDEX DAY_DATA (create_date,article_id,acount_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='数据表';

#创建用户收益表 
CREATE TABLE profit_tb(
profit_id int(11) NOT NULL AUTO_INCREMENT COMMENT '收益id',
type tinyint(4) DEFAULT 0 COMMENT '收益类型，0自身，1合伙人',
money decimal(11,2) DEFAULT 0 COMMENT '收益金额',
number bigint(20) DEFAULT 0 COMMENT '阅读数',
create_date datetime COMMENT '创建时间',
article_id int(11) COMMENT '文章id外键',
acount_id int(11) COMMENT '账户id外键',
PRIMARY KEY (profit_id),
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_ARTICLEID (article_id) USING BTREE,
INDEX INDEX_ACOUNTID (acount_id) USING BTREE,
INDEX INDEX_TYPE (type) USING BTREE,
UNIQUE INDEX ARTICLE_PROFIT (create_date,article_id,acount_id,type) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='收益表';

#创建app版本表 
CREATE TABLE app_version_tb(
app_version_id int(11) NOT NULL AUTO_INCREMENT COMMENT 'app版本id',
platform tinyint(4)  COMMENT 'app平台，默认0安卓，1为IOS',
name varchar(255)  COMMENT 'app版本名',
type tinyint(4) DEFAULT 0 COMMENT 'app类型，默认0普通，1为强制',
content varchar(255)  COMMENT 'app更新内容',
link varchar(255)  COMMENT 'app版本链接',
update_date datetime COMMENT '更新时间',
status tinyint(4)  COMMENT 'app状态，默认0上线，1为未上线',
PRIMARY KEY (app_version_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='app版本表';

#创建推广表 
CREATE TABLE spread_tb(
spread_id int(11) NOT NULL AUTO_INCREMENT COMMENT '推广id',
platform tinyint(4) DEFAULT 0  COMMENT '推广平台，默认0安卓，1为IOS',
name varchar(255)  COMMENT '推广名',
method varchar(255) COMMENT '推广方式',
link varchar(255)  COMMENT 'app链接',
model tinyint(4) DEFAULT 0 COMMENT '计费方式，0注册，1下载',
unit_price decimal(11,2) DEFAULT 0 COMMENT '单价',
total_price decimal(11,2) DEFAULT 0 COMMENT '总价',
download_number bigint(20) DEFAULT 0 COMMENT '下载次数',
register_number bigint(20) DEFAULT 0 COMMENT '注册次数',
now_total_price decimal(11,2) DEFAULT 0 COMMENT '消耗金额',
create_date datetime COMMENT '创建时间',
end_date datetime COMMENT '结束时间',
status varchar(255) DEFAULT '正常' COMMENT '状态',
PRIMARY KEY (spread_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='推广表';

#设置初始角色
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("超级管理员","超级管理员",now());
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("广告主管理员","广告主管理员",now());
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("广告主","广告主",now()); 
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("用户","用户",now()); 

#设置初始管理员密码MD5加密123456
INSERT IGNORE INTO acount_tb (nickname,scale,phone,email,password,create_date,login_date,role_id) 
VALUES ("聂跃",0,"15111336587","278076304@qq.com","11874bb6149dd45428da628c9766b252",now(),now(),"1000"); 
#财务
INSERT IGNORE INTO finance_tb (money,recharge,consume,withdrawals,update_date,acount_id) 
VALUES (0,0,0,0,now(),1000); 

#ALTER TABLE data_tb ADD UNIQUE INDEX DAY_DATA (create_date, article_id, acount_id) USING BTREE ;
#ALTER TABLE profit_tb ADD UNIQUE INDEX ARTICLE_PROFIT (create_date, article_id, acount_id,type) USING BTREE ;