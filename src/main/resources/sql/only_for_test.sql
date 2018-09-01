create table only_for_test (
  id integer primary key autoincrement,
  name text not null,
  age integer not null,
  address char(50),
  salary real,
  image blob
);