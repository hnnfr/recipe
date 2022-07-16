-- category
INSERT INTO category (description) VALUES ('American');
INSERT INTO category (description) VALUES ('Italian');
INSERT INTO category (description) VALUES ('Mexican');
INSERT INTO category (description) VALUES ('Fast Food');

-- unit_of_measure
INSERT INTO unit_of_measure (description) VALUES ('Teaspoon');
INSERT INTO unit_of_measure (description) VALUES ('Tablespoon');
INSERT INTO unit_of_measure (description) VALUES ('Cup');
INSERT INTO unit_of_measure (description) VALUES ('Pinch');
INSERT INTO unit_of_measure (description) VALUES ('Ounce');
INSERT INTO unit_of_measure (description) VALUES ('Ripe');
INSERT INTO unit_of_measure (description) VALUES ('Serrano');

-- recipe
-- insert into recipe (description, prep_time, cook_time, servings, source, url, directions, difficulty)
-- values ('Guacamole with Charred Sweet Corn, Bacon and Tomato', 20, 20, 8, 'https://www.simplyrecipes.com/',
-- 'https://www.simplyrecipes.com/recipes/guacamole_with_charred_sweet_corn_bacon_and_tomato/', '', 'MODERATE');

-- ingredient
--insert into ingredient (recipe_id, uom_id, description, amount)
--values ()