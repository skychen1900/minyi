create table if not exists minyi (
    id int not null auto_increment,
    CDate date,
    CTime time,
    Status VARCHAR(20),
    Title VARCHAR(200),
    Author VARCHAR(50),
    Content VARCHAR(500),
    ReplyContent VARCHAR(500),
    ReplyBumen VARCHAR(30),
    ReplyDate date,
    ReplyTime time,
    Href VARCHAR(100),
    MTime time,
    MDate date,
    primary key (id)
) engine = INNODB;