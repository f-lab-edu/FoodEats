CREATE TABLE `shop_essential_info` (
  `shop_id` bigint(8) NOT NULL,
  `category` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `brand` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `location` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `phone` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `shop_business_hour_info` (
  `shop_id` bigint(11) NOT NULL,
  `open_time` varchar(45) DEFAULT NULL,
  `close_time` varchar(45) DEFAULT NULL,
  `shop_open_status` varchar(45) DEFAULT 'CLOSE',
  `closing_day` varchar(45) DEFAULT '0',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `shop_delivery_info` (
  `shop_id` bigint(11) NOT NULL,
  `grade` varchar(45) DEFAULT NULL,
  `delivery_method` varchar(45) DEFAULT NULL,
  `shop_payment_method` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;