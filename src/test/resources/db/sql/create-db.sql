CREATE TABLE IF NOT EXISTS `user` (
  `iduser` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `mail` VARCHAR(255) NOT NULL,
  `birthdate` DATE NULL DEFAULT NULL,
  `creation_date` DATE NOT NULL,
  `country` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `mail_UNIQUE` (`mail` ASC));

CREATE TABLE IF NOT EXISTS `goal` (
  `idgoal` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `creation_date` DATE NOT NULL,
  `start_date` DATE NULL DEFAULT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  `state` INT(11) NULL DEFAULT '0',
  `owner` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idgoal`),
  CONSTRAINT `fk_goal_user`
  FOREIGN KEY (`owner`)
  REFERENCES `user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `stage` (
  `idstage` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `sortOrder` INT(10) UNSIGNED NOT NULL,
  `goal` INT(11),
  `state` INT(11) NOT NULL,
  PRIMARY KEY (`idstage`),
  CONSTRAINT `fk_stage_goal`
  FOREIGN KEY (`goal`)
  REFERENCES `goal` (`idgoal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `task` (
  `idtask` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `state` INT(11) NULL DEFAULT NULL,
  `stage` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idtask`),
  CONSTRAINT `fk_task_stage`
  FOREIGN KEY (`stage`)
  REFERENCES `stage` (`idstage`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);