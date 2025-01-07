INSERT INTO squadra_model (id_squadra, nome, colori_sociali) VALUES
(1, 'Juventus', 'Bianco e Nero'),
(2, 'Inter', 'Nero e Azzurro'),
(3, 'Milan', 'Rosso e Nero'),
(4, 'Roma', 'Giallo e Rosso'),
(5, 'Napoli', 'Azzurro e Bianco');

INSERT INTO tifoseria_model (id_tifoseria, nome_tifoseria, squadra_id_squadra) VALUES
(1, 'Bianconera', 1),   -- Juventus
(2, 'Nerazzurri', 2),   -- Inter
(3, 'Rossonera', 3),    -- Milan
(4, 'Giallorossi', 4),  -- Roma
(5, 'Partenopei', 5);   -- Napoli

INSERT INTO giocatori_model (id_giocatore, nome_cognome, numero_ammonizioni, squadra_id_squadra) VALUES
(1, 'Cristiano Ronaldo', 1, 1),   -- Juventus
(2, 'Paulo Dybala', 2, 1),         -- Juventus
(3, 'Lautaro Martinez', 1, 2),     -- Inter
(4, 'Romelu Lukaku', 3, 2),        -- Inter
(5, 'Zlatan Ibrahimovic', 0, 3),   -- Milan
(6, 'Kessie', 1, 3),               -- Milan
(7, 'Edin Dzeko', 3, 4),           -- Roma
(8, 'Henrikh Mkhitaryan', 1, 4),   -- Roma
(9, 'Victor Osimhen', 0, 5),       -- Napoli
(10, 'Lorenzo Insigne', 1, 5);     -- Napoli

INSERT INTO torneo_model (id_torneo, nome_torneo) VALUES
(1, 'Serie A'),
(2, 'Coppa Italia'),
(3, 'Champions League'),
(4, 'Europa League');

INSERT INTO torneo_model_squadre (tornei_id_torneo, squadre_id_squadra) VALUES
(1, 1),  -- Juventus partecipa alla Serie A
(1, 2),  -- Inter partecipa alla Serie A
(1, 3),  -- Milan partecipa alla Serie A
(1, 4),  -- Roma partecipa alla Serie A
(1, 5),  -- Napoli partecipa alla Serie A
(3, 1),  -- Juventus partecipa alla Champions League
(3, 2),  -- Inter partecipa alla Champions League
(3, 3),  -- Milan partecipa alla Champions League
(3, 4),  -- Roma partecipa alla Champions League
(3, 5);  -- Napoli partecipa alla Champions League
