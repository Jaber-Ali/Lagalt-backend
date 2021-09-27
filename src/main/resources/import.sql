--Category
INSERT INTO categories (title) VALUES ('Music');
INSERT INTO categories (title) VALUES ('Film');
INSERT INTO categories (title) VALUES ('Game Development');
INSERT INTO categories (title) VALUES ('Web Development');
--Project_Status
INSERT INTO project_status (title) VALUES ('Founding');
INSERT INTO project_status (title) VALUES ('In progress');
INSERT INTO project_status (title) VALUES ('Stalled');
INSERT INTO project_status (title) VALUES ('Completed');
--Projects

----Roles
INSERT INTO roles (title) VALUES ('Administrator');
INSERT INTO roles (title) VALUES ('User');

--Skills
INSERT INTO skills (title, category_id) VALUES ('Piano', 1);
INSERT INTO skills (title, category_id) VALUES ('Drums', 1);
INSERT INTO skills (title, category_id) VALUES ('Guitar', 1);
INSERT INTO skills (title, category_id) VALUES ('Flute', 1);
INSERT INTO skills (title, category_id) VALUES ('Violin', 1);
INSERT INTO skills (title, category_id) VALUES ('Clarinet', 1);
INSERT INTO skills (title, category_id) VALUES ('Lighting', 2);
INSERT INTO skills (title, category_id) VALUES ('Recording', 2);
INSERT INTO skills (title, category_id) VALUES ('Sounds', 2);
INSERT INTO skills (title, category_id) VALUES ('SFX', 2);
INSERT INTO skills (title, category_id) VALUES ('Costumes', 2);
INSERT INTO skills (title, category_id) VALUES ('Acting', 2);
INSERT INTO skills (title, category_id) VALUES ('Unity', 3);
INSERT INTO skills (title, category_id) VALUES ('Unreal Engine', 3);
INSERT INTO skills (title, category_id) VALUES ('C#', 3);
INSERT INTO skills (title, category_id) VALUES ('Java', 3);
INSERT INTO skills (title, category_id) VALUES ('Photoshop', 3);
INSERT INTO skills (title, category_id) VALUES ('Blender', 3);
INSERT INTO skills (title, category_id) VALUES ('Animations', 3);
INSERT INTO skills (title, category_id) VALUES ('CSS', 4);
INSERT INTO skills (title, category_id) VALUES ('HTML', 4);
INSERT INTO skills (title, category_id) VALUES ('Javascript', 4);
INSERT INTO skills (title, category_id) VALUES ('Angular', 4);
INSERT INTO skills (title, category_id) VALUES ('React', 4);
INSERT INTO skills (title, category_id) VALUES ('Vue', 4);
INSERT INTO skills (title, category_id) VALUES ('Typescript', 4);