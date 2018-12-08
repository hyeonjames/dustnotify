CREATE DATABASE dustnotify DEFAULT CHARACTER SET utf8mb4;
 
USE dustnotify;

CREATE TABLE `region` (
  `region_code` varchar(10) NOT NULL,
  `region_kor_name` varchar(255) DEFAULT '',
  `region_eng_name` varchar(255) DEFAULT '',
  PRIMARY KEY (`region_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `region` (region_code, region_kor_name, region_eng_name) VALUES
  ('39', '제주','jeju'),
  ('38', '경남','gyeongnam'),
  ('37', '경북','gyeongbuk'),
  ('36', '전남','jeonnam'),
  ('35', '전북','jeonbuk'),
  ('34', '충남','chungnam'),
  ('33', '충북','chungbuk'),
  ('32', '강원','gangwon'),
  ('31', '경기','gyeonggi'),
  ('29', '세종','sejong'),
  ('26', '울산','ulsan'),
  ('25', '대전','daejeon'),
  ('24', '광주','gwangju'),
  ('23', '인천','incheon'),
  ('22', '대구','daegu'),
  ('21', '부산','busan'),
  ('11', '서울','seoul');
