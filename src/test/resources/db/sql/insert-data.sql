INSERT INTO user (login, password, mail, birthdate, creation_date) SELECT * from (
   SELECT 'login1', '1111', 'mail1', NULL, '2001-01-01' UNION
   SELECT 'login2', '2222', 'mail2', NULL, '2002-01-01' UNION
   SELECT 'login3', '3333', 'mail3', NULL, '2003-01-01') x WHERE NOT EXISTS(SELECT  * FROM user);

INSERT INTO goal (name, description, creation_date, start_date, end_date, state, owner) SELECT * FROM (
   SELECT 'Goal1', 'Description1', '2000-01-01', '2001-01-01', '2002-01-01', 0, 1 UNION
   SELECT 'Goal2', 'Description2', '2000-02-02', '2001-02-02', '2002-03-03', 1, 2 UNION
   SELECT 'Goal3', 'Description3', '2000-03-03', '2001-03-03', '2002-03-03', 0, 3 UNION
   SELECT 'Goal4', 'Description4', '2000-04-04', '2001-04-04', '2002-04-04', 0, 3 ) y WHERE NOT EXISTS(SELECT * FROM goal);