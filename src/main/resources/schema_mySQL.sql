/*
 * 自動創建資料庫表格
 */
CREATE TABLE IF NOT EXISTS `java_demo_test`.`register` (
  `account` VARCHAR(20) NOT NULL,
  `pwd` VARCHAR(45) NOT NULL,
  `reg_time` DATETIME DEFAULT NULL,
  `active` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`account`));
