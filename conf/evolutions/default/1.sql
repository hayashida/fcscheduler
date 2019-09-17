# --- !Ups

CREATE TABLE `schedules` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `from_time` varchar(4) NOT NULL,
  `to_time` varchar(4) NOT NULL,
  `place_id` int NOT NULL,
  `memo` varchar(255),
  `created_at` timestamp NOT NULL DEFAULT current_timestamp,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_schedule_date` (`date`)
);

# --- !Downs

DROP TABLE `schedules`;
