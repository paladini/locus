SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `locus` ;
CREATE SCHEMA IF NOT EXISTS `locus` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `locus` ;

-- -----------------------------------------------------
-- Table `locus`.`Dia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locus`.`Dia` ;

CREATE  TABLE IF NOT EXISTS `locus`.`Dia` (
  `idDia` INT NOT NULL AUTO_INCREMENT ,
  `descricao` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idDia`) ,
  UNIQUE INDEX `descricao_UNIQUE` (`descricao` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locus`.`Turno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locus`.`Turno` ;

CREATE  TABLE IF NOT EXISTS `locus`.`Turno` (
  `idTurno` INT NOT NULL AUTO_INCREMENT ,
  `descricao` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idTurno`) ,
  UNIQUE INDEX `descricao_UNIQUE` (`descricao` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locus`.`Disciplina`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locus`.`Disciplina` ;

CREATE  TABLE IF NOT EXISTS `locus`.`Disciplina` (
  `idDisciplina` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`idDisciplina`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locus`.`Professor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locus`.`Professor` ;

CREATE  TABLE IF NOT EXISTS `locus`.`Professor` (
  `idProfessor` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`idProfessor`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locus`.`Curso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locus`.`Curso` ;

CREATE  TABLE IF NOT EXISTS `locus`.`Curso` (
  `idCurso` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`idCurso`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locus`.`Turma`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locus`.`Turma` ;

CREATE  TABLE IF NOT EXISTS `locus`.`Turma` (
  `idTurma` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(100) NOT NULL ,
  `Curso_idCurso` INT NOT NULL ,
  PRIMARY KEY (`idTurma`, `Curso_idCurso`) ,
  INDEX `fk_Turma_Curso1_idx` (`Curso_idCurso` ASC) ,
  CONSTRAINT `fk_Turma_Curso1`
    FOREIGN KEY (`Curso_idCurso` )
    REFERENCES `locus`.`Curso` (`idCurso` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locus`.`Sala`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locus`.`Sala` ;

CREATE  TABLE IF NOT EXISTS `locus`.`Sala` (
  `idSala` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`idSala`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locus`.`Aula`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locus`.`Aula` ;

CREATE  TABLE IF NOT EXISTS `locus`.`Aula` (
  `idAula` INT NOT NULL AUTO_INCREMENT ,
  `idTurma` INT NOT NULL ,
  `idDia` INT NOT NULL ,
  `idTurno` INT NOT NULL ,
  `idSala` INT NOT NULL ,
  `idCurso` INT NOT NULL ,
  `idDisciplina` INT NOT NULL ,
  `idProfessor` INT NOT NULL ,
  PRIMARY KEY (`idAula`, `idTurma`, `idDia`, `idTurno`, `idSala`, `idCurso`, `idDisciplina`, `idProfessor`) ,
  INDEX `fk_Aula_Turma1_idx` (`idTurma` ASC) ,
  INDEX `fk_Aula_Dia1_idx` (`idDia` ASC) ,
  INDEX `fk_Aula_Turno1_idx` (`idTurno` ASC) ,
  INDEX `fk_Aula_Sala1_idx` (`idSala` ASC) ,
  INDEX `fk_Aula_Curso1_idx` (`idCurso` ASC) ,
  INDEX `fk_Aula_Disciplina1_idx` (`idDisciplina` ASC) ,
  INDEX `fk_Aula_Professor1_idx` (`idProfessor` ASC) ,
  CONSTRAINT `fk_Aula_Turma1`
    FOREIGN KEY (`idTurma` )
    REFERENCES `locus`.`Turma` (`idTurma` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aula_Dia1`
    FOREIGN KEY (`idDia` )
    REFERENCES `locus`.`Dia` (`idDia` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aula_Turno1`
    FOREIGN KEY (`idTurno` )
    REFERENCES `locus`.`Turno` (`idTurno` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aula_Sala1`
    FOREIGN KEY (`idSala` )
    REFERENCES `locus`.`Sala` (`idSala` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aula_Curso1`
    FOREIGN KEY (`idCurso` )
    REFERENCES `locus`.`Curso` (`idCurso` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aula_Disciplina1`
    FOREIGN KEY (`idDisciplina` )
    REFERENCES `locus`.`Disciplina` (`idDisciplina` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aula_Professor1`
    FOREIGN KEY (`idProfessor` )
    REFERENCES `locus`.`Professor` (`idProfessor` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locus`.`Disciplina_has_Professor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locus`.`Disciplina_has_Professor` ;

CREATE  TABLE IF NOT EXISTS `locus`.`Disciplina_has_Professor` (
  `Disciplina_idDisciplina` INT NOT NULL ,
  `Professor_idProfessor` INT NOT NULL ,
  PRIMARY KEY (`Disciplina_idDisciplina`, `Professor_idProfessor`) ,
  INDEX `fk_Disciplina_has_Professor_Professor1_idx` (`Professor_idProfessor` ASC) ,
  INDEX `fk_Disciplina_has_Professor_Disciplina1_idx` (`Disciplina_idDisciplina` ASC) ,
  CONSTRAINT `fk_Disciplina_has_Professor_Disciplina1`
    FOREIGN KEY (`Disciplina_idDisciplina` )
    REFERENCES `locus`.`Disciplina` (`idDisciplina` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Disciplina_has_Professor_Professor1`
    FOREIGN KEY (`Professor_idProfessor` )
    REFERENCES `locus`.`Professor` (`idProfessor` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locus`.`Curso_has_Disciplina`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locus`.`Curso_has_Disciplina` ;

CREATE  TABLE IF NOT EXISTS `locus`.`Curso_has_Disciplina` (
  `Curso_idCurso` INT NOT NULL ,
  `Disciplina_idDisciplina` INT NOT NULL ,
  PRIMARY KEY (`Curso_idCurso`, `Disciplina_idDisciplina`) ,
  INDEX `fk_Curso_has_Disciplina_Disciplina1_idx` (`Disciplina_idDisciplina` ASC) ,
  INDEX `fk_Curso_has_Disciplina_Curso1_idx` (`Curso_idCurso` ASC) ,
  CONSTRAINT `fk_Curso_has_Disciplina_Curso1`
    FOREIGN KEY (`Curso_idCurso` )
    REFERENCES `locus`.`Curso` (`idCurso` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Curso_has_Disciplina_Disciplina1`
    FOREIGN KEY (`Disciplina_idDisciplina` )
    REFERENCES `locus`.`Disciplina` (`idDisciplina` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `locus`.`admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locus`.`admin` ;

CREATE  TABLE IF NOT EXISTS `locus`.`admin` (
  `nome_escola` VARCHAR(100) NOT NULL ,
  `login` VARCHAR(45) NOT NULL ,
  `senha` VARCHAR(45) NOT NULL ,
  `ultimo_acesso` DATETIME NULL ,
  PRIMARY KEY (`senha`, `login`) ,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) )
ENGINE = InnoDB;

use locus;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- Desabilitando o modo seguro
SET SQL_SAFE_UPDATES=0;

-- Inserindo admin no banco de dados
INSERT INTO admin (nome_escola, login, senha) 
values ("Instituição","admin","admin");



-- ======== Comandos úteis =========

-- Setar último acesso como NULL
-- update admin set ultimo_acesso = null where login = "admin";

-- Deletar todos os turnos
-- delete from turno where idTurno != 0;

-- Deletar o admin
-- delete from admin where login = 'admin';

-- Deletar todas as disciplinas
-- delete from disciplina where idDisciplina != 0;

-- Deletar todos os cursos
-- delete from curso where idCurso != 0;

-- Deletar todas as turmas
-- delete from turma where idTurma != 0;

-- ======== Selects ========
-- select * from disciplina;
-- select * from curso;
-- select * from admin;
-- select * from turno;
-- select * from dia;
-- select * from Sala;
-- select * from turma;

## essa traz todas as diciplinas associadas a um determiado curso
select d.idDisciplina, d.nome from Curso_has_Disciplina as cd
	inner join disciplina as d on
		d.idDisciplina = cd.Disciplina_idDisciplina 
where cd.Curso_idCurso = 1;
#pego todas as diciplinas nao associadas ao curso passado por parametro
select d.idDisciplina, d.nome
from disciplina d
where d.idDisciplina not in (
 select Disciplina_idDisciplina 
	from Curso_has_Disciplina
		
	where Curso_idCurso = 1
);

delete from Curso_has_Disciplina where Curso_idCurso = 1 and Disciplina_idDisciplina = ?; 

delete from Curso_has_Disciplina 

