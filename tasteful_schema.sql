-- -----------------------------------------------------
-- Schema tasteful
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `tasteful`;
CREATE DATABASE `tasteful`;
USE `tasteful`;

-- -----------------------------------------------------
-- Table `tasteful`.`app_user`
-- -----------------------------------------------------
CREATE TABLE `users` (
    `user_id` INT AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(255) NOT NULL,
    `password_hash` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL
);

-- -----------------------------------------------------
-- Table `tasteful`.`cuisine`
-- -----------------------------------------------------
CREATE TABLE `cuisine_types` (
  `cuisine_type_id` INT PRIMARY KEY AUTO_INCREMENT,
  `cuisine_type` VARCHAR(255) NOT NULL
);

-- -----------------------------------------------------
-- Table `tasteful`.`ingredient`
-- -----------------------------------------------------
CREATE TABLE `ingredients` (
  `ingredient_id` INT PRIMARY KEY AUTO_INCREMENT,
  `ingredient_name` VARCHAR(255) NOT NULL,
  `image_url` VARCHAR(255) NOT NULL
);

-- -----------------------------------------------------
-- Table `tasteful`.`recipe`
-- -----------------------------------------------------
CREATE TABLE `recipes` (
  `recipe_id` INT PRIMARY KEY AUTO_INCREMENT,
  `user_id` INT,
  `title` VARCHAR(255) NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  `image` BLOB NULL,
  `instructions` TEXT(1000) NOT NULL,
  `vegetarian` BIT(1) NOT NULL DEFAULT 0,
  `vegan` BIT(1) NOT NULL DEFAULT 0,
  `gluten_free` BIT(1) NOT NULL DEFAULT 0,
  `dairy_free` BIT(1) NOT NULL DEFAULT 0,
  `servings` INT NOT NULL,
  `src_url` VARCHAR(255) NOT NULL,
  `time_required` INT NOT NULL,
  `cuisine_type_id` INT,
	FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`),
    FOREIGN KEY (`cuisine_type_id`) REFERENCES `cuisine_types`(`cuisine_type_id`)
  );
  
    -- -----------------------------------------------------
-- Table `tasteful`.`units`
-- -----------------------------------------------------
  CREATE TABLE `units` (
    `unit_id` INT AUTO_INCREMENT PRIMARY KEY,
    `unit_name` VARCHAR(50) NOT NULL,
    `unit_abbrev` VARCHAR(50) NOT NULL
);
  
  -- -----------------------------------------------------
-- Table `tasteful`.`recipe_ingredient`
-- -----------------------------------------------------
CREATE TABLE `recipe_ingredient` (
  `recipe_id` INT NOT NULL,
  `ingredient_id` INT NOT NULL,
  `quantity` DECIMAL(7,4),
  `unit_id` int,
  -- CONSTRAINT `pk_recipe_ingredient`
	PRIMARY KEY (`recipe_id`, `ingredient_id`),
  -- CONSTRAINT `fk_recipe_ingredient_recipe`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipes`(`recipe_id`),
  -- CONSTRAINT `fk_recipe_ingredient_ingredient`
    FOREIGN KEY (`ingredient_id`)
    REFERENCES `ingredients`(`ingredient_id`),
    FOREIGN KEY (`unit_id`) 
    REFERENCES `units`(`unit_id`)
);

  -- -----------------------------------------------------
-- Table `tasteful`.`likes`
-- -----------------------------------------------------
CREATE TABLE `likes` (
    `user_id` INT,
    `recipe_id` INT,
    PRIMARY KEY(`user_id`, `recipe_id`),
    FOREIGN KEY (`user_id`) 
    REFERENCES `users`(`user_id`),
    FOREIGN KEY (`recipe_id`) 
    REFERENCES `recipes`(`recipe_id`)
);

  -- -----------------------------------------------------
-- Table `tasteful`.`follows`
-- -----------------------------------------------------
CREATE TABLE `follows` (
    `follower_id` INT,
    `following_id` INT,
    PRIMARY KEY(`follower_id`, `following_id`),
    FOREIGN KEY (`follower_id`) 
    REFERENCES `users`(`user_id`),
    FOREIGN KEY (`following_id`) 
    REFERENCES `users`(`user_id`)
);

  -- -----------------------------------------------------
-- Table `tasteful`.`refresh_tokens`
-- -----------------------------------------------------
CREATE TABLE `refresh_tokens` (
    `token_id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT,
    `token` VARCHAR(255) NOT NULL,
    `expiry_date` TIMESTAMP NOT NULL,
    FOREIGN KEY (`user_id`) 
    REFERENCES `users`(`user_id`)
);





ALTER TABLE `users` AUTO_INCREMENT = 1;
ALTER TABLE `recipes` AUTO_INCREMENT = 1;
ALTER TABLE `ingredients` AUTO_INCREMENT = 1;
ALTER TABLE `cuisine_types` AUTO_INCREMENT = 1;


INSERT INTO `units` (`unit_name`, `unit_abbrev`)
	VALUES
    ('fluid ounce','fl oz'),
    ('ounce','oz'),
    ('tablespoon','tbsp'),
    ('cup','c'),
    ('pint','pt'),
    ('quart','qt'),
    ('gallon','gal'),
    ('teaspoon','tsp'),
    ('pound','lb'),
    ('milliliter','mL'),
    ('liter','L'),
    ('milligram','mg'),
    ('gram','g'),
    ('kilogram','kg'),
    ('',''),
	('count', 'ct');
    
    
INSERT INTO `cuisine_types` (`cuisine_type`)
	VALUES -- Exhaustive to spoonacular API selection
    ('African'),
    ('Asian'),
    ('American'),
    ('British'),
    ('Cajun'),
    ('Carribean'),
    ('Chinese'),
    ('Eastern European'),
    ('European'),
    ('French'),
    ('German'),
    ('Greek'),
    ('Indian'),
    ('Irish'),
    ('Italian'),
    ('Japanese'),
    ('Jewish'),
    ('Korean'),
    ('Latin American'),
    ('Mediterranean'),
    ('Mexican'),
    ('Middle Eastern'),
    ('Nordic'),
    ('Southern'),
    ('Spanish'),
    ('Thai'),
    ('Vietnamese');
