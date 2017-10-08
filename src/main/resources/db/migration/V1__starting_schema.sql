create table message (
  id BIGINT NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36),
  sender varchar(256) NOT NULL,
  content varchar(2000) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid)
);
