create table USER
(
  ID           int AUTO_INCREMENT primary key NOT NULL ,
  ACCOUNT_ID   VARCHAR(100),
  NAME         VARCHAR(50),
  TOKEN        CHAR(36),
  GMT_CREAT    BIGINT,
  GMT_MODIFIED BIGINT
);
