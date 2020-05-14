# Host: localhost  (Version 5.5.5-10.4.11-MariaDB)
# Date: 2020-05-14 00:11:36
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "usuario"
#

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `email` varchar(256) NOT NULL,
  `senha` varchar(16) NOT NULL,
  `nome` varchar(126) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "usuario"
#

INSERT INTO `usuario` VALUES ('joao@gmail.com','456789','João'),('joaquim@gmail.com','abc123','Joaquim'),('maria@gmail.com','123456','Maria');

#
# Structure for table "tarefas"
#

DROP TABLE IF EXISTS `tarefas`;
CREATE TABLE `tarefas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(256) DEFAULT NULL,
  `descricao` text DEFAULT NULL,
  `fk_usuario_email` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuario_email` (`fk_usuario_email`),
  CONSTRAINT `tarefas_ibfk_1` FOREIGN KEY (`fk_usuario_email`) REFERENCES `usuario` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

#
# Data for table "tarefas"
#

INSERT INTO `tarefas` VALUES (1,'Praticar PHP','Praticar todo o conteúdo visto em aula: PHPMailer, MVC, Padrões de projeto e muito mais...','maria@gmail.com'),(2,'Instalar o Windows','Para não sofrer com sistemas instáveis, formatarei meu computador e instalarei uma distro do Windows! :)','joao@gmail.com'),(3,'Ler um artigo sobre C#','Procurar na Web, algum artigo que trate do assunto C#','joaquim@gmail.com');
