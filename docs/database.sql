drop table if exists belongToGroup;
drop table if exists participateToEvent;
drop table if exists friendWith;
drop table if exists Publications;
drop table if exists Events;
drop table if exists Groups;
drop table if exists Roles;
drop table if exists Users;


-- création de la table utilisateur avec un ID, une adresse mail et un login uniques; un nom, un prénom et un mot de passe obligatoires
create table Users(
	IDUser serial primary key,
	lastName varchar(50) not null,
	firstName varchar(50) not null,
	birthDay date,
	birthPlace varchar(100),
	email varchar(100) not null unique,
	address varchar(200),
	phoneNumber integer,
	login varchar(100) not null unique,
	password varchar(100) not null,
	profilPhoto varchar(200)
);

-- création de la table publication avec un ID, un titre obligatoire et un le nombre de like à 0
create table Publications(
	IDPublication serial primary key,
	titlePublication varchar(20) not null,
	content varchar(200),
	likesNumber integer default 0,
	IDAuthor integer references Users(IDUser)
		on update cascade
		on delete restrict
);

-- création de la table évènement avec un ID et un titre obligatoire
create table Events(
	IDEvent serial primary key,
	titleEvent varchar(20) not null,
	description varchar(200),
	place varchar(200),
	date date,
	IDCreator integer references Users(IDUser)
		on update cascade
		on delete restrict
);

-- création de la table groupe avec un ID et un titre obligatoire
create table Groups(
	IDGroup serial primary key,
	titleGroup varchar(20) not null,
	IDCreator integer references Users(IDUser)
		on update cascade
		on delete restrict
);

-- création de la table d'appartenance à un groupe pour un utilisateur avec l'ID du groupe et de l'utilisateur
create table belongToGroup(
	IDGroup integer references Groups(IDGroup)
		on update cascade
		on delete restrict,
	IDUser integer references Users(IDUser)
		on update cascade
		on delete restrict,
	constraint PKBelongToGroup primary key (IDGroup, IDUser)
);

-- création de la table de participation à un évènement pour un utilisateur avec l'ID de l'évènement et de l'utilisateur
create table participateToEvent(
	IDEvent integer references Events(IDEvent)
		on update cascade
		on delete restrict,
	IDUser integer references Users(IDUser)
		on update cascade
		on delete restrict,
	constraint PKParticipateToEvent primary key (IDEvent, IDUser)
);

-- création de la table de relation entre deux utilisateurs avec leurs IDs
create table friendWith(
	IDUser1 integer references Users(IDUser)
		on update cascade
		on delete restrict,
	IDUser2 integer references Users(IDUser)
		on update cascade
		on delete restrict,
	constraint PKFriendWith primary key (IDUser1, IDUser2)
);

-- création de la table des rôles : 
-- 1 : admin
-- 2 : user
-- 3 : useless

create table Roles(
	IDUser integer references Users(IDUser)
		on update cascade
		on delete restrict,
	role varchar(6) default 'role1'
);