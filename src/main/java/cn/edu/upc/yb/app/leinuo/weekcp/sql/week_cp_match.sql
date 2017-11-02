DROP TABLE week_cp_match;
SHOW CREATE TABLE week_cp_match;

CREATE TABLE `week_cp_match` (
  `match_id` int(11) NOT NULL AUTO_INCREMENT,
  `cp_id0` int(11) NOT NULL,
  `cp_id1` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL ,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`match_id`),
  FOREIGN KEY (cp_id0) REFERENCES week_cp_user(user_id)
    ON DELETE CASCADE ON UPDATE CASCADE ,
  FOREIGN KEY (cp_id1) REFERENCES week_cp_user(user_id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='匹配成功过的表';

INSERT INTO week_cp_match
(cp_id0, cp_id1, update_time)
VALUES (10001,10000,now() );

ALTER TABLE week_cp_match DROP FOREIGN KEY week_cp_match_ibfk_1;
ALTER TABLE week_cp_match DROP FOREIGN KEY week_cp_match_ibfk_2;

SELECT *
FROM week_cp_match;
TRUNCATE TABLE week_cp_match;

DROP TABLE week_cp_match;

SHOW CREATE TABLE week_cp_match;