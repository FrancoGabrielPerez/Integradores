insert into staff (admin_id, nombre, apellido, nro_celular, email, password) values (1, 'Lindi', 'Naulty', '7024445922', 'lnaulty0@spotify.com', 'sG3>G2uZKLtPi');
insert into staff (admin_id, nombre, apellido, nro_celular, email, password) values (2, 'Cymbre', 'Jaquest', '6425184989', 'cjaquest1@altervista.org', 'gA4}AmQWB3n');
insert into staff (admin_id, nombre, apellido, nro_celular, email, password) values (3, 'Devin', 'Newlyn', '6621400847', 'dnewlyn2@google.cn', 'xD4''9ZjS#R');
insert into staff (admin_id, nombre, apellido, nro_celular, email, password) values (4, 'Gwenni', 'Lebrun', '8997834545', 'glebrun3@cbslocal.com', 'vQ8{mB{(');
insert into staff (admin_id, nombre, apellido, nro_celular, email, password) values (5, 'Marlin', 'Wattins', '8965694621', 'mwattins4@xrea.com', 'fE4''MVM()y8Wz');

insert into authority(name) values ('Lindi');
insert into authority(name) values ('Cymbri');

insert into rel_admin_staff_authority (admin_id, authority_id) values (1, "Lindi");
insert into rel_admin_staff_authority (admin_id, authority_id) values (2, "Cymbri");
