drop view if exists librerie_libri_torino;
drop table if exists librerie_libri;
drop table if exists LIBRERIE;
drop table if exists libri;
drop table if exists autori;
drop table if exists generi;



create table LIBRERIE (
 nome varChar(100),
 citta varChar(100)
 -- primary key (name, citta) -- Modo per inniettare la primary key direttamente nella creazione della tabbella 
);
alter table LIBRERIE add primary key (nome, citta); -- Metodo per settare le chiavi primarie dopo la creazione delle tabella 

create table libri (
 id serial primary key,
 titolo varchar(100) not null,
 genere varchar(100) not null,
 autore_id int not null,
 data date,
 idCasaEditrice integer
);

create table librerie_libri(
	librerie_nome varchar(100),
	librerie_citta varchar(100),
	libro_id int,
	foreign key (libro_id) references libri (id),
	foreign key (librerie_nome, librerie_citta) references librerie (nome, citta),
	primary key (librerie_nome,librerie_citta,libro_id)
);

-- ALTER TABLE librerie_libri ADD CONSTRAINT FK_libro_libreria FOREIGN KEY (libro_id) REFERENCES libri (id);
-- Alter table librerie_libri add constraint fk-librerie_libri foreign key (librerie_nome,librerie_citta) references librerie(nome, citta)


create table autori(
 	id serial primary key,
 	nome varchar(100),
 	cognome varchar(100)
 );

CREATE UNIQUE INDEX autoriIndix ON autori (nome, cognome);


create table generi (
 nome_genere varChar(100) primary key
);

alter table libri add constraint fk_genere foreign key (genere) references generi(nome_genere);
alter table libri add constraint fk_autore foreign key (autore_id) references autori(id);


CREATE OR REPLACE VIEW librerie_libri_torino 
as select * from libri l 
join librerie_libri ll on l.id = ll.libro_id 
where ll.librerie_citta = 'Torino';

-- select * from libri l join librerie_libri ll on l.id = ll.libro_id where ll.librerie_citta = 'Torino';
