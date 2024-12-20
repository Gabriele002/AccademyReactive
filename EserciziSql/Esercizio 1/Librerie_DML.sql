INSERT INTO LIBRERIE (nome, citta) VALUES
('Libreria Feltrinelli', 'Torino'),
('Libreria Mondadori', 'Milano'),
('Libreria Giunti', 'Roma'),
('Libreria Hoepli', 'Milano'),
('Libreria Rizzoli', 'Napoli');


INSERT INTO autori (nome, cognome) VALUES
('Giovanni', 'Verga'),
('Dante', 'Alighieri'),
('Umberto', 'Eco'),
('Italo', 'Calvino'),
('Luigi', 'Pirandello');


INSERT INTO generi (nome_genere) VALUES
('Romanzo'),
('Giallo'),
('Fantascienza'),
('Storico'),
('Fantasy');


INSERT INTO libri (titolo, genere, autore_id, data, idCasaEditrice) VALUES
('I Malavoglia', 'Romanzo', 1, '1881-01-01', 1),
('Divina Commedia', 'Storico', 2, '1320-01-01', 2),
('Il Nome della Rosa', 'Giallo', 3, '1980-01-01', 3),
('Le Cosmicomiche', 'Fantascienza', 4, '1965-01-01', 4),
('Sei Personaggi in Cerca di', 'Romanzo', 5, '1921-01-01', 5);


INSERT INTO librerie_libri (librerie_nome, librerie_citta, libro_id) VALUES
('Libreria Feltrinelli', 'Torino', 1),
('Libreria Mondadori', 'Milano', 2),
('Libreria Giunti', 'Roma', 3),
('Libreria Hoepli', 'Milano', 4),
('Libreria Rizzoli', 'Napoli', 5);
