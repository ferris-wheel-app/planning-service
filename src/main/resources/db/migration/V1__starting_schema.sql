create table backlog_item (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  summary VARCHAR(256) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  type VARCHAR(36) NOT NULL check (type in ('IDEA', 'ISSUE')),
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table epoch (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  name VARCHAR(256) NOT NULL,
  totem VARCHAR(256) NOT NULL,
  question VARCHAR(256) NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table year (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  epoch_id VARCHAR(36) NOT NULL,
  start_date DATE NOT NULL,
  finish_date DATE NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table theme (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  year_id VARCHAR(36) NOT NULL,
  name VARCHAR(256) NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table goal (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  theme_id VARCHAR(36) NOT NULL,
  summary VARCHAR(256) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  status VARCHAR(36) NOT NULL check (status in ('NOT_ACHIEVED', 'EMPLOYED', 'UNEMPLOYED')),
  graduation VARCHAR(36) NOT NULL check (graduation in ('ABANDONED', 'THREAD', 'WEAVE', 'HOBBY', 'GOAL')),
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table goal_backlog_item (
  id BIGINT NOT NULL AUTO_INCREMENT,
  goal_id BIGINT NOT NULL,
  backlog_item_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT goal_fk1 FOREIGN KEY (goal_id) REFERENCES goal (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT backlog_item_fk FOREIGN KEY (backlog_item_id) REFERENCES backlog_item (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

create table thread (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  goal_id VARCHAR(36),
  summary VARCHAR(256) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  performance VARCHAR(36) NOT NULL check (performance in ('POOR', 'SLIPPING', 'IMPROVING', 'ON_TRACK')),
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  last_performed TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table weave (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  goal_id VARCHAR(36),
  summary VARCHAR(256) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  status VARCHAR(36) NOT NULL check (status in ('PLANNED', 'IN_PROGRESS', 'COMPLETE')),
  type VARCHAR(36) NOT NULL check (type in ('PRIORITY', 'PDR', 'BAU')),
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  last_performed TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table laser_donut (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  goal_id VARCHAR(36) NOT NULL,
  summary VARCHAR(256) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  milestone VARCHAR(256) NOT NULL,
  `order` INT(20) NOT NULL,
  status VARCHAR(36) NOT NULL check (status in ('PLANNED', 'IN_PROGRESS', 'COMPLETE')),
  type VARCHAR(36) NOT NULL check (type in ('PROJECT_FOCUSED', 'SKILL_FOCUSED')),
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  last_performed TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table portion (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  laser_donut_id VARCHAR(36) NOT NULL,
  summary VARCHAR(256) NOT NULL,
  `order` INT(20) NOT NULL,
  status VARCHAR(36) NOT NULL check (status in ('PLANNED', 'IN_PROGRESS', 'COMPLETE')),
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  last_performed TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table todo (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  parent_id VARCHAR(36) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  `order` INT(20) NOT NULL,
  is_done TINYINT(1) NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  last_performed TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table hobby (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  goal_id VARCHAR(36),
  summary VARCHAR(256) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  frequency VARCHAR(36) NOT NULL check (frequency in ('FREQUENT', 'SCATTERED', 'RARE', 'UNEXPLORED')),
  type VARCHAR(36) NOT NULL check (type in ('ACTIVE', 'PASSIVE')),
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  last_performed TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table one_off (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  goal_id VARCHAR(36),
  description VARCHAR(2000) NOT NULL,
  estimate BIGINT NOT NULL,
  `order` INT(20) NOT NULL,
  status VARCHAR(36) NOT NULL check (status in ('PLANNED', 'IN_PROGRESS', 'COMPLETE')),
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  last_performed TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table scheduled_one_off (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  occurs_on TIMESTAMP NOT NULL,
  goal_id VARCHAR(36),
  description VARCHAR(2000) NOT NULL,
  estimate BIGINT NOT NULL,
  status VARCHAR(36) NOT NULL check (status in ('PLANNED', 'IN_PROGRESS', 'COMPLETE')),
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  last_performed TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table scheduled_laser_donut (
  id BIGINT NOT NULL AUTO_INCREMENT,
  laser_donut_id BIGINT NOT NULL,
  tier INT(20) NOT NULL,
  is_current TINYINT(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (laser_donut_id),
  CONSTRAINT laser_donut_fk1 FOREIGN KEY (laser_donut_id) REFERENCES laser_donut (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

create table current_activity (
  id BIGINT NOT NULL AUTO_INCREMENT,
  current_laser_donut BIGINT NOT NULL,
  current_portion BIGINT NOT NULL,
  last_daily_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_weekly_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (current_laser_donut, current_portion),
  CONSTRAINT current_laser_donut_fk FOREIGN KEY (current_laser_donut) REFERENCES laser_donut (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT current_portion_fk FOREIGN KEY (current_portion) REFERENCES portion (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

create table skill_category (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  name VARCHAR(256) NOT NULL,
  parent_category BIGINT,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid),
  CONSTRAINT category_fk1 FOREIGN KEY (parent_category) REFERENCES skill_category (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

create table skill (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  name VARCHAR(256) NOT NULL,
  parent_category BIGINT NOT NULL,
  proficiency VARCHAR(36) NOT NULL check (proficiency in ('ZERO', 'BASIC', 'NOVICE', 'INTERMEDIATE', 'ADVANCED', 'EXPERT')),
  practised_hours BIGINT NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  last_applied TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid),
  CONSTRAINT category_fk2 FOREIGN KEY (parent_category) REFERENCES skill_category (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

create table goal_skill (
  goal_id BIGINT NOT NULL,
  skill_id BIGINT NOT NULL,
  relevance VARCHAR(36) NOT NULL check (relevance in ('NEEDED', 'TO_BE_ACQUIRED', 'MAINTENANCE')),
  level VARCHAR(36) NOT NULL check (level in ('BASIC', 'NOVICE', 'INTERMEDIATE', 'ADVANCED', 'EXPERT')),
  UNIQUE KEY (goal_id, skill_id),
  CONSTRAINT goal_fk2 FOREIGN KEY (goal_id) REFERENCES goal (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT skill_fk1 FOREIGN KEY (skill_id) REFERENCES skill (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

create table thread_skill (
  thread_id BIGINT NOT NULL,
  skill_id BIGINT NOT NULL,
  relevance VARCHAR(36) NOT NULL check (relevance in ('NEEDED', 'TO_BE_ACQUIRED', 'MAINTENANCE')),
  level VARCHAR(36) NOT NULL check (level in ('BASIC', 'NOVICE', 'INTERMEDIATE', 'ADVANCED', 'EXPERT')),
  UNIQUE KEY (thread_id, skill_id),
  CONSTRAINT thread_fk FOREIGN KEY (thread_id) REFERENCES thread (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT skill_fk2 FOREIGN KEY (skill_id) REFERENCES skill (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

create table weave_skill (
  weave_id BIGINT NOT NULL,
  skill_id BIGINT NOT NULL,
  relevance VARCHAR(36) NOT NULL check (relevance in ('NEEDED', 'TO_BE_ACQUIRED', 'MAINTENANCE')),
  level VARCHAR(36) NOT NULL check (level in ('BASIC', 'NOVICE', 'INTERMEDIATE', 'ADVANCED', 'EXPERT')),
  UNIQUE KEY (weave_id, skill_id),
  CONSTRAINT weave_fk FOREIGN KEY (weave_id) REFERENCES weave (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT skill_fk3 FOREIGN KEY (skill_id) REFERENCES skill (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

create table laser_donut_skill (
  laser_donut_id BIGINT NOT NULL,
  skill_id BIGINT NOT NULL,
  relevance VARCHAR(36) NOT NULL check (relevance in ('NEEDED', 'TO_BE_ACQUIRED', 'MAINTENANCE')),
  level VARCHAR(36) NOT NULL check (level in ('BASIC', 'NOVICE', 'INTERMEDIATE', 'ADVANCED', 'EXPERT')),
  UNIQUE KEY (laser_donut_id, skill_id),
  CONSTRAINT laser_donut_fk2 FOREIGN KEY (laser_donut_id) REFERENCES laser_donut (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT skill_fk4 FOREIGN KEY (skill_id) REFERENCES skill (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

create table portion_skill (
  portion_id BIGINT NOT NULL,
  skill_id BIGINT NOT NULL,
  relevance VARCHAR(36) NOT NULL check (relevance in ('NEEDED', 'TO_BE_ACQUIRED', 'MAINTENANCE')),
  level VARCHAR(36) NOT NULL check (level in ('BASIC', 'NOVICE', 'INTERMEDIATE', 'ADVANCED', 'EXPERT')),
  UNIQUE KEY (portion_id, skill_id),
  CONSTRAINT portion_fk FOREIGN KEY (portion_id) REFERENCES portion (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT skill_fk5 FOREIGN KEY (skill_id) REFERENCES skill (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

create table hobby_skill (
  hobby_id BIGINT NOT NULL,
  skill_id BIGINT NOT NULL,
  relevance VARCHAR(36) NOT NULL check (relevance in ('NEEDED', 'TO_BE_ACQUIRED', 'MAINTENANCE')),
  level VARCHAR(36) NOT NULL check (level in ('BASIC', 'NOVICE', 'INTERMEDIATE', 'ADVANCED', 'EXPERT')),
  UNIQUE KEY (hobby_id, skill_id),
  CONSTRAINT hobby_fk FOREIGN KEY (hobby_id) REFERENCES hobby (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT skill_fk6 FOREIGN KEY (skill_id) REFERENCES skill (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

create table one_off_skill (
  one_off_id BIGINT NOT NULL,
  skill_id BIGINT NOT NULL,
  relevance VARCHAR(36) NOT NULL check (relevance in ('NEEDED', 'TO_BE_ACQUIRED', 'MAINTENANCE')),
  level VARCHAR(36) NOT NULL check (level in ('BASIC', 'NOVICE', 'INTERMEDIATE', 'ADVANCED', 'EXPERT')),
  UNIQUE KEY (one_off_id, skill_id),
  CONSTRAINT one_off_fk FOREIGN KEY (one_off_id) REFERENCES one_off (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT skill_fk7 FOREIGN KEY (skill_id) REFERENCES skill (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

create table scheduled_one_off_skill (
  scheduled_one_off_id BIGINT NOT NULL,
  skill_id BIGINT NOT NULL,
  relevance VARCHAR(36) NOT NULL check (relevance in ('NEEDED', 'TO_BE_ACQUIRED', 'MAINTENANCE')),
  level VARCHAR(36) NOT NULL check (level in ('BASIC', 'NOVICE', 'INTERMEDIATE', 'ADVANCED', 'EXPERT')),
  UNIQUE KEY (scheduled_one_off_id, skill_id),
  CONSTRAINT scheduled_one_off_fk FOREIGN KEY (scheduled_one_off_id) REFERENCES scheduled_one_off (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT skill_fk8 FOREIGN KEY (skill_id) REFERENCES skill (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

create table relationship (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  name VARCHAR(256) NOT NULL,
  category VARCHAR(36) NOT NULL check (category in ('FAMILY', 'FRIENDS', 'WORK', 'ROMANTIC', 'MENTORSHIP')),
  traits VARCHAR(2000) NOT NULL,
  likes VARCHAR(2000) NOT NULL,
  dislikes VARCHAR(2000) NOT NULL,
  hobbies VARCHAR(2000) NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified TIMESTAMP,
  last_meet TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
) ENGINE=InnoDB;

create table goal_relationship (
  goal_id BIGINT NOT NULL,
  relationship_id BIGINT NOT NULL,
  UNIQUE KEY (goal_id, relationship_id),
  CONSTRAINT goal_fk3 FOREIGN KEY (goal_id) REFERENCES goal (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT relationship_fk FOREIGN KEY (relationship_id) REFERENCES relationship (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;
