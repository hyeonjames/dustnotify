CREATE DATABASE dustnotify DEFAULT CHARACTER SET utf8mb4;
 
USE dustnotify;

CREATE TABLE `daily_stat` (
  `info_no` bigint(20) NOT NULL,
  `busan` int(11) NOT NULL,
  `chungbuk` int(11) NOT NULL,
  `chungnam` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `daegu` int(11) NOT NULL,
  `daejeon` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `gangwon` int(11) NOT NULL,
  `gwangju` int(11) NOT NULL,
  `gyeongbuk` int(11) NOT NULL,
  `gyeonggi` int(11) NOT NULL,
  `gyeongnam` int(11) NOT NULL,
  `incheon` int(11) NOT NULL,
  `jeju` int(11) NOT NULL,
  `jeonbuk` int(11) NOT NULL,
  `jeonnam` int(11) NOT NULL,
  `sejong` int(11) NOT NULL,
  `seoul` int(11) NOT NULL,
  `ulsan` int(11) NOT NULL,
  PRIMARY KEY (`info_no`),
  UNIQUE KEY `UK_jeapha13cb9rgkx0b1rot2rj0` (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `hour_stat` (
  `info_no` bigint(20) NOT NULL,
  `busan` int(11) NOT NULL,
  `chungbuk` int(11) NOT NULL,
  `chungnam` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `daegu` int(11) NOT NULL,
  `daejeon` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `gangwon` int(11) NOT NULL,
  `gwangju` int(11) NOT NULL,
  `gyeongbuk` int(11) NOT NULL,
  `gyeonggi` int(11) NOT NULL,
  `gyeongnam` int(11) NOT NULL,
  `incheon` int(11) NOT NULL,
  `jeju` int(11) NOT NULL,
  `jeonbuk` int(11) NOT NULL,
  `jeonnam` int(11) NOT NULL,
  `sejong` int(11) NOT NULL,
  `seoul` int(11) NOT NULL,
  `ulsan` int(11) NOT NULL,
  PRIMARY KEY (`info_no`),
  UNIQUE KEY `UK_1qlmjbogfegr92q3gmohj4wq7` (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `station` (
  `stat_no` bigint(20) NOT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `dmx` double NOT NULL,
  `dmy` double NOT NULL,
  `item` varchar(255) DEFAULT NULL,
  `mang_name` varchar(255) DEFAULT NULL,
  `map` varchar(255) DEFAULT NULL,
  `oper` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `station_name` varchar(255) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`stat_no`),
  UNIQUE KEY `UK_smxm5nxgwu6akahf8gp7lfmx` (`station_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `hibernate_sequence` (next_val) VALUES (1);

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