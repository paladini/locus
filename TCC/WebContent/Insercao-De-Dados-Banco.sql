-- Administração
INSERT into admin (login, senha, nomeEscola) values ("admin", md5("admin"), "Instituicao");

-- Dias e Turnos
INSERT into turno (nome, ativo) values("Matutino", 0);
INSERT into turno (nome, ativo) values("Vespertino", 0);
INSERT into turno (nome, ativo) values("Noturno", 0);
INSERT into dia (nome, ativo) values("Segunda-feira", 0);
INSERT into dia (nome, ativo) values("Terça-feira", 0);
INSERT into dia (nome, ativo) values("Quarta-feira", 0);
INSERT into dia (nome, ativo) values("Quinta-feira", 0);
INSERT into dia (nome, ativo) values("Sexta-feira", 0);

-- Professor padrão (necessário pra não dar pau no ensalamento)
INSERT into professor (idprofessor, nome) values (1,"A contratar");

-- Disciplinas
-- INSERT into disciplina (nome) values ("Programação Orientada a Objetos");
-- INSERT into disciplina (nome) values ("Banco de Dados");
-- INSERT into disciplina (nome) values ("Banco de Dados II");
-- INSERT into disciplina (nome) values ("Projeto de Software");
-- INSERT into disciplina (nome) values ("Qualidade de Software");
-- INSERT into disciplina (nome) values ("Estruturas de Dados");

-- Cursos
-- INSERT into curso (nome) values ("Técnico em Informática Básico");
-- INSERT into curso (nome) values ("Técnico em Informática Avançado");

-- Curso_Has_Disciplina
-- INSERT into curso_has_disciplina values (1, 6);
-- INSERT into curso_has_disciplina values (1, 2);
-- INSERT into curso_has_disciplina values (2, 1);
-- INSERT into curso_has_disciplina values (2, 3);
-- INSERT into curso_has_disciplina values (2, 4);
-- INSERT into curso_has_disciplina values (2, 5);

-- Sala
-- INSERT into sala (nome, usar1, usar2, usar3) values ("G11", 0, 0, 0);
-- INSERT into sala (nome, usar1, usar2, usar3) values ("G12", 0, 0, 0);
-- INSERT into sala (nome, usar1, usar2, usar3) values ("G13", 0, 0, 0);
-- INSERT into sala (nome, usar1, usar2, usar3) values ("G23", 0, 0, 0);
-- INSERT into sala (nome, usar1, usar2, usar3) values ("G24", 0, 0, 0);

-- Professor
-- INSERT into professor (nome) values ("Daniel");
-- INSERT into professor (nome) values ("Arthur");
-- INSERT into professor (nome) values ("Priscila");
-- INSERT into professor (nome) values ("Cátia");
-- INSERT into professor (nome) values ("Andrenizia");

-- Professor_Has_Disciplina
-- INSERT into disciplina_has_professor values (1, 1);
-- INSERT into disciplina_has_professor values (2, 3);
-- INSERT into disciplina_has_professor values (3, 4);
-- INSERT into disciplina_has_professor values (4, 5);
-- INSERT into disciplina_has_professor values (5, 2);
-- INSERT into disciplina_has_professor values (6, 1);

-- Turma
-- INSERT into turma (nome, idcurso, idturno) values ("2012/01", 2, 2);
-- INSERT into turma (nome, idcurso, idturno) values ("2012/02", 2, 1);
-- INSERT into turma (nome, idcurso, idturno) values ("2013/01", 1, 3);
-- INSERT into turma (nome, idcurso, idturno) values ("2013/02", 1, 2);