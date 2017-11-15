create table message (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  sender varchar(256) NOT NULL,
  content varchar(2000) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table backlog_item (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  year_id VARCHAR(36) NOT NULL,
  summary VARCHAR(256) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  type VARCHAR(36) NOT NULL check (type in ('IDEA', 'ISSUE')),
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table epoch (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  name VARCHAR(256) NOT NULL,
  totem VARCHAR(256) NOT NULL,
  question VARCHAR(256) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table year (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  epoch_id VARCHAR(36) NOT NULL,
  start_date DATE NOT NULL,
  finish_date DATE NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table theme (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  year_id VARCHAR(36) NOT NULL,
  name VARCHAR(256) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table goal (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  theme_id VARCHAR(36) NOT NULL,
  summary VARCHAR(256) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  level INT(20) NOT NULL,
  priority TINYINT(1) NOT NULL,
  status VARCHAR(36) NOT NULL check (status in ('NOT_ACHIEVED', 'EMPLOYED', 'UNEMPLOYED')),
  graduation VARCHAR(36) NOT NULL check (graduation in ('ABANDONED', 'THREAD', 'WEAVE', 'HOBBY', 'GOAL')),
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table goal_backlog_item (
  id BIGINT NOT NULL AUTO_INCREMENT,
  goal_id BIGINT NOT NULL,
  backlog_item_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT goal_fk FOREIGN KEY (goal_id) REFERENCES goal (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT backlog_item_fk FOREIGN KEY (backlog_item_id) REFERENCES backlog_item (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

create table thread (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  goal_id VARCHAR(36),
  summary VARCHAR(256) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  status VARCHAR(36) NOT NULL check (status in ('NOT_ACHIEVED', 'EMPLOYED', 'UNEMPLOYED')),
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table weave (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  goal_id VARCHAR(36),
  summary VARCHAR(256) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  status VARCHAR(36) NOT NULL check (status in ('UNKNOWN', 'NOT_REACHED', 'NOT_STARTED', 'INCOMPLETE', 'COMPLETE')),
  type VARCHAR(36) NOT NULL check (type in ('PRIORITY', 'PDR', 'BAU')),
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table laser_donut (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  goal_id VARCHAR(36),
  summary VARCHAR(256) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  milestone VARCHAR(256) NOT NULL,
  `order` INT(20) NOT NULL,
  status VARCHAR(36) NOT NULL check (status in ('UNKNOWN', 'NOT_REACHED', 'NOT_STARTED', 'INCOMPLETE', 'COMPLETE')),
  type VARCHAR(36) NOT NULL check (type in ('PROJECT_FOCUSED', 'SKILL_FOCUSED')),
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table portion (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  laser_donut_id VARCHAR(36) NOT NULL,
  summary VARCHAR(256) NOT NULL,
  `order` INT(20) NOT NULL,
  status VARCHAR(36) NOT NULL check (status in ('UNKNOWN', 'NOT_REACHED', 'NOT_STARTED', 'INCOMPLETE', 'COMPLETE')),
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table todo (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  portion_id VARCHAR(36) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  `order` INT(20) NOT NULL,
  status VARCHAR(36) NOT NULL check (status in ('UNKNOWN', 'NOT_REACHED', 'NOT_STARTED', 'INCOMPLETE', 'COMPLETE')),
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table hobby (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  goal_id VARCHAR(36),
  summary VARCHAR(256) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  frequency VARCHAR(36) NOT NULL check (frequency in ('ONE_OFF', 'CONTINUOUS')),
  status VARCHAR(36) NOT NULL check (status in ('UNKNOWN', 'NOT_REACHED', 'NOT_STARTED', 'INCOMPLETE', 'COMPLETE')),
  type VARCHAR(36) NOT NULL check (type in ('ACTIVE', 'PASSIVE')),
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;
