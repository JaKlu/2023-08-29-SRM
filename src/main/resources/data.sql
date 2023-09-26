-- Addresses --
INSERT INTO taddress (city, street, zip_code)
VALUES ('Rzeszów', 'Rynek 1', '35-061');

INSERT INTO taddress (city, street, zip_code)
VALUES ('Kraków', 'Kamienna 2', '30-001');

INSERT INTO taddress (city, street, zip_code)
VALUES ('Kraków', 'Żołędna 69', '33-021');

INSERT INTO taddress (city, street, zip_code)
VALUES ('Wrocław', 'Macedońska 17', '51-113');

-- Users --
INSERT INTO tuser (address_id, login, password, name, surname, birthdate, email, phone_number, gun_license, role)
VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'Jakub', 'Jakubczyk', '1985-05-12', 'kuba@srm.pl', '666-333-999', 'DCF777', 'ADMIN');

INSERT INTO tuser (address_id, login, password, name, surname, birthdate, email, phone_number, gun_license, role)
VALUES (2, 'janusz', '087d9c5e13bdd64a82bef8e013625c32', 'Janusz', 'Januszkiewicz', '1988-08-18', 'janusz@srm.pl', '555-777-333', 'DCF555', 'EMPLOYEE');

INSERT INTO tuser (address_id, login, password, name, surname, birthdate, email, phone_number, gun_license, role)
VALUES (3, 'pawel', 'a741cdf4d61e1083064d813a5a1ec8aa', 'Paweł', 'Pawłowski', '1989-07-11', 'pawel@srm.pl', '111-777-333', 'DCF123', 'EMPLOYEE');

INSERT INTO tuser (address_id, login, password, name, surname, birthdate, email, phone_number, gun_license, role)
VALUES (4, 'wojtek', '0d333f240498cfd51eb8bd1d74ee0f6e', 'Wojciech', 'Wojciechowski', '1987-03-22', 'wojtek@onet.pl', '123-456-789', '', 'USER');

-- Ammunition --
INSERT INTO tammo(gauge, quantity)
VALUES ('.22LR', 220);

INSERT INTO tammo(gauge, quantity)
VALUES ('9x19mm', 150);

INSERT INTO tammo(gauge, quantity)
VALUES ('5,56x45mm/.223REM', 125);

INSERT INTO tammo(gauge, quantity)
VALUES ('7,62x39mm', 235);

INSERT INTO tammo(gauge, quantity)
VALUES ('7,62x25mm', 230);

INSERT INTO tammo(gauge, quantity)
VALUES ('9x18mm', 115);

INSERT INTO tammo(gauge, quantity)
VALUES ('12', 95);

INSERT INTO tammo(gauge, quantity)
VALUES ('5,45x39mm', 115);

INSERT INTO tammo(gauge, quantity)
VALUES ('7,62x54mmR', 70);

-- Guns --
INSERT INTO tgun(common_name, brand, type, gauge, year_of_prod, serial_no, certificate_no, is_available, description)
VALUES ('Glock 17', 'Glock 17', 'pistolet', '9x19mm', 2020, 'BNWC063', 'WR3861', true,
'Siedemnasty patent firmy Gastona Glocka. Plastikowy, brzydki i nudny, a zarazem najpopularniejszy oraz najbardziej rozpoznawalny i uniwersalny pistolet na świecie. Produkowany ciągle od 1982 roku jest obecnie na wyposażeniu formacji mundurowych w co najmniej 48 krajach świata. Wykorzystywany jest nie tylko przez policję, wojsko, ochroniarzy i konwojentów, ale także przez przestępców, bandytów, terrorystów i gangsterów.');

INSERT INTO tgun(common_name, brand, type, gauge, year_of_prod, serial_no, certificate_no, is_available, description)
VALUES ('AK47', 'Grey Ammo GN60', 'karabinek samopowtarzalny', '7,62x39mm', 2020, 'EG1747', 'WR3857', false,
'Niezawodna, ociosana z drewna i stali prosta konstrukcja to klucz do sukcesu tego karabinku produkowanego nieprzerwanie w wielu różnych wariantach od ponad siedmiu dekad. Automat Kałasznikowa zaprojektowany dla Armii Radzieckiej stał się na przestrzeni kilkudziesięciu lat podstawowym wyposażeniem wielu innych - bardziej lub mniej legalnych - formacji w każdym zakątku świata oraz ikoną popkultury, obok której nie można przejść obojętnie.');

INSERT INTO tgun(common_name, brand, type, gauge, year_of_prod, serial_no, certificate_no, is_available, description)
VALUES ('Huglu Atrox', 'Huglu Atrox', 'strzelba', '12', 2021, '21X0332', 'WR3855', true,
'Strzelba jaka jest - każdy widzi. Klasyczna pompka w nieklasycznym rozmiarze z lufą długości 28 cali i magazynkiem na 9 naboi. Jako broń uniwersalna, w zależności od zastosowanej amunicji, sprawdza się zarówno na krótkim, jak i trochę dłuższym dystansie, a także świetnie spisuje się podczas strzelania do rzutków.');

INSERT INTO tgun(common_name, brand, type, gauge, year_of_prod, serial_no, certificate_no, is_available, description)
VALUES ('PAC15', 'PAC15', 'karabinek samopowtarzalny', '5,56x45mm/.223REM', 2019, 'PAC1519758', 'WR3859', false,
'Produkowany współcześnie w Polsce, inspirowany najlepszymi amerykańskimi wzorcami karabinek z rodziny AR-15 jest cywilną odmianą legendarnej broni sił zbrojnych Stanów Zjednoczonych - karabinu M16. Solidna konstrukcja i szesnastocalowa lufa w połączeniu z zaawansowaną optyką zapewniają precyzyjne strzelanie również na większe odległości.');

-- Reservations --
INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-09-30', '10:00', 2);

INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-09-30', '11:00', 3);

INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-09-30', '13:00', 4);

INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-09-30', '15:00', 1);

INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-10-01', '12:00', 4);

INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-10-01', '14:00', 2);

INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-10-01', '16:00', 1);

INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-10-01', '17:00', 3);

INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-10-02', '10:00', 3);

INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-10-02', '11:00', 3);

INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-10-02', '12:00', 3);

INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-10-02', '13:00', 3);

INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-10-02', '14:00', 3);

INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-10-02', '15:00', 3);

INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-10-02', '16:00', 3);

INSERT INTO treservation(reservation_date, reservation_time, user_id)
VALUES ('2023-10-02', '17:00', 3);