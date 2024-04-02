CREATE table utilisateur (
	id SERIAL,
	nom VARCHAR(100),
	prenom VARCHAR(100),
	email VARCHAR(100),
	mdp VARCHAR(100),
	ddn DATE,
	primary key (id)
)

create table utilisateur_infos (
    idui SERIAL,
    id INT,
    cnit varchar,
    nb_pers_foyer INTEGER,
    surface_logement FLOAT,
    hab_agglo INTEGER,
    primary key (idui),
    constraint fk_user_info foreign key (id) references utilisateur(id)
 )
 
 
create table alimentation (
    annee INT,
    semaine INT,
    id INT,
    total_alimentation FLOAT,
    primary key (semaine, annee, id),
    constraint fk_alimentation foreign key (annee, semaine, id) references questionnaire_hebdo(annee, semaine, id)
 )
 
 
create table transport (
    annee INT,
    semaine INT,
    id INT,
    total_transport FLOAT,
    primary key (semaine, annee, id),
    constraint fk_transport foreign key (annee, semaine, id) references questionnaire_hebdo(annee, semaine, id)
 )
 

 create table logement (
    annee INT,
    semaine INT,
    id INT,
    total_logement FLOAT,
    primary key (semaine, annee, id),
    constraint fk_logement foreign key (annee, semaine, id) references questionnaire_hebdo(annee, semaine, id)
 )
 
 create table divers (
    annee INT,
    semaine INT,
    id INT,
    total_divers FLOAT,
    primary key (semaine, annee, id),
    constraint fk_divers foreign key (annee, semaine, id) references questionnaire_hebdo(annee, semaine, id)
 )

 
INSERT INTO utilisateur (nom, prenom, email, mdp, ddn)
VALUES ('Yanis', 'Aumasson', 'y@gmail.com', 'motdepasse', TO_DATE('23/10/2002', 'DD/MM/YYYY'));

INSERT INTO utilisateur_infos (id, cnit, nb_pers_foyer, surface_logement, hab_agglo)
VALUES ('1', 'cnit test', 3, 60.0, 50000);


select * from utilisateur;
select * from utilisateur_infos;

select * from utilisateur u, utilisateur_infos ui where u.id = ui.id;


