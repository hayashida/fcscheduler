# --- !Ups

CREATE TABLE `teams` (
  `id` int NOT NULL AUTO_INCREMENT,
  `no` int NOT NULL,
  `name` varchar(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
  PRIMARY KEY (`id`)
);

CREATE TABLE `places` (
  `id` int NOT NULL AUTO_INCREMENT,
  `no` int NOT NULL,
  `name` varchar(20) NOT NULL,
  `latlng` varchar(100),
  `created_at` timestamp NOT NULL DEFAULT current_timestamp,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
  PRIMARY KEY (`id`)
);

CREATE TABLE `schedule_teams` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `schedule_id` bigint,
  `team_id` int,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_schedule_team_schedule_id_team_id` (`schedule_id`, `team_id`)
);

# --- !Downs

DROP TABLE `teams`;
DROP TABLE `places`;
DROP TABLE `schedule_teams`;
