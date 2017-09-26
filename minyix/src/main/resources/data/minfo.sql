create table if not exists minfo (
  id int not null auto_increment,
  totalnum smallint,
  doingnum smallint,
  completenum smallint,
  newgetdate timestamp,
  todayadvicenum smallint,
  todaycompletenum smallint,
  primary key (id)
) engine = INNODB;
