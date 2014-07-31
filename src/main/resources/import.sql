-- companies
insert into company(id, url) values (1, 'http://www.regis24.de/impressum/')
insert into company(id, url) values (2, 'http://www.savage-wear.com/impressum/index.html')
-- insert into company(id, url) values (3, 'http://www.idealo.de/preisvergleich/AGB.html')
insert into company(id, url) values (4, 'http://www.moebus-gruppe.de/impressum.html')

-- addresses
insert into address(street, number, zip, city, created, company_id) values ('Zehdenicker', '21', '10119', 'Berlin', '2014-01-01', 1)
insert into address(street, number, zip, city, created, company_id) values ('Gubener', '29', '10243', 'Berlin', '2014-01-01', 2)
-- insert into address(street, number zip, city, created, company_id) values ('Zionskirch', '73 A', '10119', 'Berlin', '2014-01-01', 3)
insert into address(street, number, zip, city, created, company_id) values ('Hansa', '202', '13088', 'Berlin', '2014-01-01', 4)