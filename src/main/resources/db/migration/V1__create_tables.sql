create table message (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  sender varchar(256) NOT NULL,
  content varchar(2000) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
);

create table backlog_item (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  year_id VARCHAR(36) NOT NULL,
  summary VARCHAR(256) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  type ENUM('IDEA', 'ISSUE') NOT NULL
)

create table epoch (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  name VARCHAR(256) NOT NULL,
  totem VARCHAR(256) NOT NULL,
  question VARCHAR(256) NOT NULL
)

create table year (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  epoch_id VARCHAR(36) NOT NULL,
  start_date DATE NOT NULL,
  finish_date DATE NOT NULL
)

create table theme (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  year_id VARCHAR(36) NOT NULL,
  name VARCHAR(256) NOT NULL
)

create table goal (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  theme_id VARCHAR(36) NOT NULL,
  summary VARCHAR(256) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  level INT(20) NOT NULL,
  priority TINYINT(1) NOT NULL,
  status ENUM('NOT_ACHIEVED', 'EMPLOYED', 'UNEMPLOYED') NOT NULL,
  graduation ENUM('ABANDONED', 'THREAD', 'WEAVE', 'HOBBY', 'GOAL') NOT NULL
)

create table goal_backlog_item (
  id BIGINT NOT NULL AUTO_INCREMENT,
  goal_id VARCHAR(36) NOT NULL,
  backlog_item_id VARCHAR(36) NOT NULL
)

create table thread (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL,
  summary VARCHAR(256) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  goal_id VARCHAR(36),
  status ENUM('NOT_ACHIEVED', 'EMPLOYED', 'UNEMPLOYED') NOT NULL
)

case class Thread (
    uuid: UUID,
    summary: String,
    description: String,
    goalId: Option[UUID],
    status: Statuses.Status
  )
