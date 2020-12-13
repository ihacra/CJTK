-- 创建序列表
DROP TABLE IF EXISTS `sys_sequence`;
CREATE TABLE `sys_sequence` (
    `name` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_bin',
    `current_value` VARCHAR(10) NULL DEFAULT '100' COLLATE 'utf8mb4_bin',
    PRIMARY KEY (`name`)
)
COMMENT='序列表'
COLLATE='utf8mb4_bin'
ENGINE=InnoDB;


-- 获取当前序列
DROP FUNCTION IF EXISTS currval; 
DELIMITER $ 
CREATE FUNCTION currval (seq_name VARCHAR(50)) 
     RETURNS VARCHAR(10)
     LANGUAGE SQL 
     DETERMINISTIC 
     CONTAINS SQL 
     SQL SECURITY DEFINER 
     COMMENT ''
BEGIN
     DECLARE value VARCHAR(10); 
     SET VALUE = NULL; 
     SELECT current_value INTO value 
          FROM sys_sequence
          WHERE name = seq_name; 
     RETURN value; 
END
$ 
DELIMITER ; 


-- 获取下一序列
DROP FUNCTION IF EXISTS nextval; 
DELIMITER $ 
CREATE FUNCTION nextval (seq_name VARCHAR(50)) 
     RETURNS VARCHAR(10)
     LANGUAGE SQL 
     DETERMINISTIC 
     CONTAINS SQL 
     SQL SECURITY DEFINER 
     COMMENT ''
BEGIN
     UPDATE sys_sequence
          SET current_value = current_value + '1' 
          WHERE name = seq_name; 
     RETURN currval(seq_name); 
END
$ 
DELIMITER ;


-- 创建Question表
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
	`id` VARCHAR(64) NOT NULL COMMENT '主键',
	`subject` VARCHAR(8) NULL DEFAULT NULL COMMENT '科目',
	`title` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '题目',
	`option_a` VARCHAR(512) NULL DEFAULT NULL COMMENT '选项A',
	`option_b` VARCHAR(512) NULL DEFAULT NULL COMMENT '选项B',
	`option_c` VARCHAR(512) NULL DEFAULT NULL COMMENT '选项C',
	`option_d` VARCHAR(512) NULL DEFAULT NULL COMMENT '选项D',
	`answer` VARCHAR(512) NULL DEFAULT NULL COMMENT '答案',
	`analysis` VARCHAR(1024) NOT NULL DEFAULT '无' COMMENT '解析',
	`label` VARCHAR(128) NULL DEFAULT NULL COMMENT '标签分类',
	`type` VARCHAR(8) NULL DEFAULT NULL COMMENT '题目类型（0：填空；1：选择）',
	`create_date` TIMESTAMP NOT NULL COMMENT '创建日期',
	`update_date` TIMESTAMP NOT NULL COMMENT '更新日期',
	`del_flag` VARCHAR(8) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (`id`)
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB;

