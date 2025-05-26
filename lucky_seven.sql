use lucky_seven;

select * from category_tbl;

drop table category_tbl;
drop table food_tbl;
create table category_tbl (
  category_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO category_tbl VALUES(null,'단품');
INSERT INTO category_tbl VALUES(null,'세트');
INSERT INTO category_tbl VALUES(null,'음료');
INSERT INTO category_tbl VALUES(null,'해피밀');
INSERT INTO category_tbl VALUES(null,'사이드');

create table food_tbl(
   food_id bigint not null AUTO_INCREMENT primary key,
   name varchar(50) not null UNIQUE,
   price int not null,
   category_id bigint not null,
   foreign key(category_id) references category_tbl(category_id)
);
Alter table FOOD_TBL MODIFY NAME VARCHAR(70);
select * from food_tbl;

INSERT INTO food_tbl VALUES(null,'불고기버거',4500,1);
INSERT INTO food_tbl VALUES(null,'1955버거',6000,1);
INSERT INTO food_tbl VALUES(null,'콰트로 맥시멈 미트 포커스드 어메이징 얼티밋 그릴드 패티 오브 더 비기스트 포 슈퍼 미트 프릭 버거',11000,1);

INSERT INTO food_tbl VALUES(null,'불고기버거 세트',6000,2);
INSERT INTO food_tbl VALUES(null,'1955버거 세트',7500,2);
INSERT INTO food_tbl VALUES(null,'콰트로 맥시멈 미트 포커스드 어메이징 얼티밋 그릴드 패티 오브 더 비기스트 포 슈퍼 미트 프릭 버거 세트',12500,2);

INSERT INTO food_tbl VALUES(null,'콜라',2000,3);
INSERT INTO food_tbl VALUES(null,'사이다',2000,3);
INSERT INTO food_tbl VALUES(null,'오렌지 주스',2500,3);

INSERT INTO food_tbl VALUES(null,'베이컨 에그 맥머핀',4900,4);
INSERT INTO food_tbl VALUES(null,'소시지 에그 맥머핀',4900,4);
INSERT INTO food_tbl VALUES(null,'에그 맥머핀',4300,4);
insert into food_tbl values(null, '핫 머핀 2조각', 1900, 4);

INSERT INTO food_tbl VALUES(null,'맥너겟',1800,5);
INSERT INTO food_tbl VALUES(null,'해쉬 브라운',1400,5);
INSERT INTO food_tbl VALUES(null,'맥윙',4400,5);
INSERT INTO food_tbl VALUES(null,'골든 모짜렐라 치즈스틱',3600,5);