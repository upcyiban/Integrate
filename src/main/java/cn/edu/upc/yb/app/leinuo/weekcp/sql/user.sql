CREATE TABLE week_cp_user(
  `user_id` INT AUTO_INCREMENT COMMENT '自增主键',
  `name` VARCHAR(10) NOT NULL ,
  `qq` VARCHAR(32) ,
  `wei_xin` VARCHAR(32) ,
  `phone_number` VARCHAR(32) ,
  `email` VARCHAR(32) NOT NULL ,
  `major_class` VARCHAR(40) COMMENT '专业班级',
  `sex` INT NOT NULL COMMENT '0表示女性,1表示男性' ,
  `sexual_orientation` INT DEFAULT 0 COMMENT '性取向，同上',
  `hobby` VARCHAR(32) COMMENT '爱好' ,
  `create_time` TIMESTAMP DEFAULT current_timestamp,
  `update_time` TIMESTAMP NOT NULL ,
  `header_image` VARCHAR(200)
    DEFAULT 'http://yb.upc.edu.cn/static/media/college-logo.98b06d35.png'
  COMMENT '头像图片的链接，默认中国石油大学logo',
  `cp` INT DEFAULT 0 COMMENT '是否被匹配上，0表示否',
  `deleted` INT DEFAULT 0 COMMENT '用户是否被删除，0表示否',

  PRIMARY KEY (user_id),
  KEY (email,phone_number,qq,wei_xin,major_class)
)ENGINE = innoDB DEFAULT CHARSET = utf8;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE week_cp_user;
SET FOREIGN_KEY_CHECKS = 1;
SELECT *
FROM week_cp_user;
SELECT *
FROM week_cp_match;

TRUNCATE TABLE week_cp_match;
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE week_cp_user;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO week_cp_user (name, email, sex, sexual_orientation, hobby, update_time)
VALUES ('213','123',0,1,'123',now());