# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table child (
  id                        bigint not null,
  parent_id                 bigint,
  name                      varchar(255) not null,
  create_date               timestamp not null,
  update_date               timestamp not null,
  constraint pk_child primary key (id))
;

create table parent (
  id                        bigint not null,
  name                      varchar(255) not null,
  create_date               timestamp not null,
  update_date               timestamp not null,
  constraint pk_parent primary key (id))
;

create sequence child_seq;

create sequence parent_seq;

alter table child add constraint fk_child_parent_1 foreign key (parent_id) references parent (id) on delete restrict on update restrict;
create index ix_child_parent_1 on child (parent_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists child;

drop table if exists parent;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists child_seq;

drop sequence if exists parent_seq;

