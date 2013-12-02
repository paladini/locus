SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`professor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`professor` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`professor` (
  `idprofessor` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idprofessor`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`disciplina`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`disciplina` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`disciplina` (
  `iddisciplina` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`iddisciplina`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`curso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`curso` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`curso` (
  `idcurso` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idcurso`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`turno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`turno` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`turno` (
  `idturno` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NOT NULL ,
  `ativo` INT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`idturno`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`turma`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`turma` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`turma` (
  `nome` VARCHAR(45) NOT NULL ,
  `idturma` INT NOT NULL AUTO_INCREMENT ,
  `idcurso` INT NOT NULL ,
  `idturno` INT NOT NULL ,
  INDEX `fk_turma_curso1_idx` (`idcurso` ASC) ,
  INDEX `fk_turma_turno1_idx` (`idturno` ASC) ,
  PRIMARY KEY (`idturma`) ,
  CONSTRAINT `fk_turma_curso1`
    FOREIGN KEY (`idcurso` )
    REFERENCES `mydb`.`curso` (`idcurso` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_turma_turno1`
    FOREIGN KEY (`idturno` )
    REFERENCES `mydb`.`turno` (`idturno` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`sala`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`sala` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`sala` (
  `idsala` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NOT NULL ,
  `usar1` TINYINT(1) NULL ,
  `usar2` TINYINT(1) NULL ,
  `usar3` TINYINT(1) NULL ,
  PRIMARY KEY (`idsala`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`dia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`dia` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`dia` (
  `iddia` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NOT NULL ,
  `ativo` INT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`iddia`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`aula`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`aula` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`aula` (
  `idaula` INT NOT NULL AUTO_INCREMENT ,
  `idprofessor` INT NOT NULL ,
  `idturma` INT NOT NULL ,
  `idsala` INT NOT NULL ,
  `idturno` INT NOT NULL ,
  `idcurso` INT NOT NULL ,
  `iddiscilpina` INT NOT NULL ,
  `nome` VARCHAR(45) NULL ,
  `iddia` INT NOT NULL ,
  PRIMARY KEY (`idaula`) ,
  INDEX `fk_aula_professor_idx` (`idprofessor` ASC) ,
  INDEX `fk_aula_turma1_idx` (`idturma` ASC) ,
  INDEX `fk_aula_sala1_idx` (`idsala` ASC) ,
  INDEX `fk_aula_turno1_idx` (`idturno` ASC) ,
  INDEX `fk_aula_curso1_idx` (`idcurso` ASC) ,
  INDEX `fk_aula_discilpina1_idx` (`iddiscilpina` ASC) ,
  INDEX `fk_aula_dia1_idx` (`iddia` ASC) ,
  CONSTRAINT `fk_aula_professor`
    FOREIGN KEY (`idprofessor` )
    REFERENCES `mydb`.`professor` (`idprofessor` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_aula_turma1`
    FOREIGN KEY (`idturma` )
    REFERENCES `mydb`.`turma` (`idturma` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_aula_sala1`
    FOREIGN KEY (`idsala` )
    REFERENCES `mydb`.`sala` (`idsala` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_aula_turno1`
    FOREIGN KEY (`idturno` )
    REFERENCES `mydb`.`turno` (`idturno` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_aula_curso1`
    FOREIGN KEY (`idcurso` )
    REFERENCES `mydb`.`curso` (`idcurso` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_aula_discilpina1`
    FOREIGN KEY (`iddiscilpina` )
    REFERENCES `mydb`.`disciplina` (`iddisciplina` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_aula_dia1`
    FOREIGN KEY (`iddia` )
    REFERENCES `mydb`.`dia` (`iddia` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`admin` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`admin` (
  `senha` VARCHAR(45) NOT NULL ,
  `login` VARCHAR(45) NOT NULL ,
  `ultimo_acesso` DATETIME NULL ,
  `nomeEscola` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`senha`, `login`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`turno_has_dia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`turno_has_dia` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`turno_has_dia` (
  `turno_idturno` INT NOT NULL ,
  `dia_iddia` INT NOT NULL ,
  PRIMARY KEY (`turno_idturno`, `dia_iddia`) ,
  INDEX `fk_turno_has_dia_dia1` (`dia_iddia` ASC) ,
  INDEX `fk_turno_has_dia_turno1` (`turno_idturno` ASC) ,
  CONSTRAINT `fk_turno_has_dia_turno1`
    FOREIGN KEY (`turno_idturno` )
    REFERENCES `mydb`.`turno` (`idturno` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_turno_has_dia_dia1`
    FOREIGN KEY (`dia_iddia` )
    REFERENCES `mydb`.`dia` (`iddia` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`curso_has_disciplina`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`curso_has_disciplina` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`curso_has_disciplina` (
  `curso_idcurso` INT NOT NULL ,
  `disciplina_iddiscilpina` INT NOT NULL ,
  PRIMARY KEY (`curso_idcurso`, `disciplina_iddiscilpina`) ,
  INDEX `fk_curso_has_disciplina_disciplina1` (`disciplina_iddiscilpina` ASC) ,
  INDEX `fk_curso_has_disciplina_curso1` (`curso_idcurso` ASC) ,
  CONSTRAINT `fk_curso_has_disciplina_curso1`
    FOREIGN KEY (`curso_idcurso` )
    REFERENCES `mydb`.`curso` (`idcurso` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_curso_has_disciplina_disciplina1`
    FOREIGN KEY (`disciplina_iddiscilpina` )
    REFERENCES `mydb`.`disciplina` (`iddisciplina` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`disciplina_has_professor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`disciplina_has_professor` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`disciplina_has_professor` (
  `disciplina_iddisciplina` INT NOT NULL ,
  `professor_idprofessor` INT NOT NULL ,
  PRIMARY KEY (`disciplina_iddisciplina`, `professor_idprofessor`) ,
  INDEX `fk_disciplina_has_professor_professor1` (`professor_idprofessor` ASC) ,
  INDEX `fk_disciplina_has_professor_disciplina1` (`disciplina_iddisciplina` ASC) ,
  CONSTRAINT `fk_disciplina_has_professor_disciplina1`
    FOREIGN KEY (`disciplina_iddisciplina` )
    REFERENCES `mydb`.`disciplina` (`iddisciplina` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_disciplina_has_professor_professor1`
    FOREIGN KEY (`professor_idprofessor` )
    REFERENCES `mydb`.`professor` (`idprofessor` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


