-- Addresses --
INSERT INTO taddress (city, street, zip_code)
VALUES ('Rzeszów', 'Rynek 1', '35-061');

INSERT INTO taddress (city, street, zip_code)
VALUES ('Kraków', 'Kamienna 2', '30-001');

INSERT INTO taddress (city, street, zip_code)
VALUES ('Wrocław', 'Macedońska 17', '51-113');

-- Users --
INSERT INTO tuser (address_id, login, password, name, surname, birthdate, email, phone_number, gun_license, role)
VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'Kuba', 'Jakubczyk', '1985-05-12', 'kuba@srm.pl', '666-333-999', 'DCF777', 'ADMIN');

INSERT INTO tuser (address_id, login, password, name, surname, birthdate, email, phone_number, gun_license, role)
VALUES (2, 'janusz', '087d9c5e13bdd64a82bef8e013625c32', 'Janusz', 'Januszkiewicz', '1988-08-18', 'janusz@srm.pl', '555-777-333', 'DCF555', 'EMPLOYEE');

INSERT INTO tuser (address_id, login, password, name, surname, birthdate, email, phone_number, gun_license, role)
VALUES (3, 'wojtek', '0d333f240498cfd51eb8bd1d74ee0f6e', 'Wojciech', 'Wojciechowski', '1987-03-22', 'wojtek@onet.pl', '123-456-789', '', 'USER');

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
INSERT INTO tgun(common_name, brand, type, gauge, year_of_prod, serial_no, certificate_no, is_available)
VALUES ('Glock 17', 'Glock 17', 'pistolet', '9x19mm', 2020, 'BNWC063', 'WR3861', true);

INSERT INTO tgun(common_name, brand, type, gauge, year_of_prod, serial_no, certificate_no, is_available)
VALUES ('AK47', 'Grey Ammo GN60', 'karabinek samopowtarzalny', '7,62x39mm', 2020, 'EG1747', 'WR3857', false);

INSERT INTO tgun(common_name, brand, type, gauge, year_of_prod, serial_no, certificate_no, is_available)
VALUES ('Huglu Atrox', 'Huglu Atrox', 'strzelba', '12', 2021, '21X0332', 'WR3855', true);

INSERT INTO tgun(common_name, brand, type, gauge, year_of_prod, serial_no, certificate_no, is_available)
VALUES ('PAC15', 'PAC15', 'karabinek samopowtarzalny', '5,56x45mm/.223REM', 2019, 'PAC1519758', 'WR3859', false);

