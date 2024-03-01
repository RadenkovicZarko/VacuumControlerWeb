INSERT INTO USER (first_name, last_name, email, password) VALUES ('John', 'Doe', 'user1@raf.rs', '$2a$12$jJLc7Zc0C/A03M2jjksX1ODfU9ZlJmeMbQhmj30ymZcQZQJKx3Kqa');
INSERT INTO USER (first_name, last_name, email, password) VALUES('Alice', 'Smith', 'user2@raf.rs', '$2a$12$jJLc7Zc0C/A03M2jjksX1ODfU9ZlJmeMbQhmj30ymZcQZQJKx3Kqa');
INSERT INTO USER (first_name, last_name, email, password) VALUES('Bob', 'Johnson', 'user3@raf.rs', '$2a$12$jJLc7Zc0C/A03M2jjksX1ODfU9ZlJmeMbQhmj30ymZcQZQJKx3Kqa');
INSERT INTO USER (first_name, last_name, email, password) VALUES('Emma', 'Davis', 'user4@raf.rs', '$2a$12$jJLc7Zc0C/A03M2jjksX1ODfU9ZlJmeMbQhmj30ymZcQZQJKx3Kqa');
INSERT INTO USER (first_name, last_name, email, password) VALUES('Petar', 'Petrovic', 'user5@raf.rs', '$2a$12$jJLc7Zc0C/A03M2jjksX1ODfU9ZlJmeMbQhmj30ymZcQZQJKx3Kqa');




INSERT INTO VacuumCleaner (name, status, createDate, active, addedBy_id,version,busy,used)
VALUES ('Vacuum1', 0, '2023-07-01', true, 1,0,false,0);

INSERT INTO VacuumCleaner (name, status, createDate, active, addedBy_id,version,busy,used)
VALUES ('Zare', 1, '2023-12-15', true, 1,0,false,0);

INSERT INTO VacuumCleaner (name, status, createDate, active, addedBy_id,version,busy,used)
VALUES ('Pera', 2, '2024-01-15', true, 1,0,false,0);


INSERT INTO VacuumCleaner (name, status, createDate, active, addedBy_id,version,busy,used)
VALUES ('Vacuum2', 1, '2023-12-27', true, 2,0,false,0);


INSERT INTO VacuumCleaner (name, status, createDate, active, addedBy_id,version,busy,used)
VALUES ('Vacuum3', 2, '2023-12-28', true, 3,0,false,0);

INSERT INTO permision (name) VALUES ('can_read_users');
INSERT INTO permision (name) VALUES ('can_create_users');
INSERT INTO permision (name) VALUES  ('can_update_users');
INSERT INTO permision (name) VALUES ('can_delete_users');

INSERT INTO permision (name) VALUES ('can_search_vacuum');
INSERT INTO permision (name) VALUES ('can_start_vacuum');
INSERT INTO permision (name) VALUES  ('can_stop_vacuum');
INSERT INTO permision (name) VALUES ('can_discharge_vacuum');
INSERT INTO permision (name) VALUES ('can_add_vacuum');
INSERT INTO permision (name) VALUES ('can_remove_vacuums');


INSERT INTO user_permissions (user_id, permission_id) VALUES(1, 1);
INSERT INTO User_permissions (user_id, permission_id) VALUES(1, 2);
INSERT INTO User_permissions (user_id, permission_id) VALUES(1, 3);
INSERT INTO User_permissions (user_id, permission_id) VALUES(1, 4);
INSERT INTO user_permissions (user_id, permission_id) VALUES(1, 5);
INSERT INTO User_permissions (user_id, permission_id) VALUES(1, 6);
INSERT INTO User_permissions (user_id, permission_id) VALUES(1, 7);
INSERT INTO User_permissions (user_id, permission_id) VALUES(1, 8);
INSERT INTO user_permissions (user_id, permission_id) VALUES(1, 9);
INSERT INTO User_permissions (user_id, permission_id) VALUES(1, 10);

INSERT INTO User_permissions (user_id, permission_id) VALUES(2, 1);
INSERT INTO User_permissions (user_id, permission_id) VALUES(2, 2);
INSERT INTO User_permissions (user_id, permission_id) VALUES(2, 3);
INSERT INTO User_permissions (user_id, permission_id) VALUES(2, 4);
INSERT INTO User_permissions (user_id, permission_id) VALUES(2, 5);
INSERT INTO User_permissions (user_id, permission_id) VALUES(2, 6);

INSERT INTO User_permissions (user_id, permission_id) VALUES(3, 2);
INSERT INTO User_permissions (user_id, permission_id) VALUES(3, 1);

INSERT INTO User_permissions (user_id, permission_id) VALUES(4, 1);



INSERT INTO ErrorMessage (action, date, vacuumCleanerId, owner, message)
VALUES ('STOPPED', '2023-12-31 10:30:00', 1, 1, 'VacuumCleaner was not in RUNNING mode');

INSERT INTO ErrorMessage (action, date, vacuumCleanerId, owner, message)
VALUES ('RUNNING', '2023-12-30 14:45:00', 2, 1, 'Vacuum cleaner was busy doing another action');

INSERT INTO ErrorMessage (action, date, vacuumCleanerId, owner, message)
VALUES ('DISCHARGIN', '2023-12-29 09:15:00', 3, 1, 'VacuumCleaner was not in STOPPED mode');

