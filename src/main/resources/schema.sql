drop table if exists game_instance;

create table game_instance
(
    id             char(36) primary key,
    word           character varying,
    currentLetters character varying,
    deathState     int,
    winFlag        int,
    alreadyTried   character varying
);