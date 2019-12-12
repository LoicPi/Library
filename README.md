# Library Release 1.0
## **D�veloppez le nouveau syst�me d�information de la biblioth�que d�une grande ville**

Ce site web permettra aux usagers de suivre les pr�ts de leurs ouvrages et de leur permettre de les renouveller.
Ils pourront �galement consulter et ou rechercher les livres de la biblioth�que et savoir si ils sont disponibles ou non.

### **Liste des fonctionnalit�s :**
- Rechercher des ouvrages et voir le nombre d�exemplaires disponibles.
- Consulter leurs pr�ts en cours. Les pr�ts sont pour une p�riode de 4 semaines.
- Prolonger un pr�t en cours. Le pr�t d�un ouvrage n�est prolongeable qu�une seule fois. La prolongation ajoute une nouvelle p�riode de pr�t (4 semaines) � la p�riode initiale.
- Mise en place d'un batch permettant l'envoi des mails de relance aux usagers n�ayant pas rendu les livres en fin de p�riode de pr�t. L�envoi est automatique � la fr�quence d�un par jour.
- D�s la release 1.0, les actions de cr�ation d�un pr�t et retour d�un pr�t seront impl�ment�s m�me si elles ne seront utilis�s par le logiciel.

### **Architecture de l'application**

Le projet est d�coup� en plusieurs microservices :
- config-server : microservice qui appelle un repository git contenant l'ensemble des configurations des microservices
- eureka-server : microservice qui r�cup�re et enregistrel'ensemble des instances des microservices afin de les exposer via une API REST
- zuul-server : microservice qui est le point d'entr�e de l'application
- microservice-book : microservice qui g�re les livres, auteurs, cat�gories, exemplaires, emprunts, reli� � la BDD librarymbook. Il g�re �galement le batch de relance des livres en retard.
- microservice-user : microservice qui g�re les utilisateurs et leurs r�les, reli� � la BDD librarymuser
- microservice-clientui : microservice qui permet l'affichage des donn�es du site

## **D�ploiement**
1. Installer le JRE d'Oracle 1.8
2. Cloner le projet sous GitHub
3. Installer Maven version minimum 4
4. Installer Tomcat 9
5. Cr�er deux bases de donn�es sous PostgreSQL :
	- une base de donn�es pour le microservice-user
	- une base de donn�es pour le microservice-book
6. Cr�er un repository GitHub pour le dossier library-config-repo ou cloner le projet GitHub : https://github.com/LoicPi/Library-config-repo.git
7. Dans le microservice config-server modifier dans le fichier application.properties se trouvant dans le dossier src/main/resources :
	spring.cloud.config.server.git.uri=*"l'adresse de votre repository GitHub"*
8. Dans votre repository library-config-repo, modifier pour les deux microservices (book et user) les informations suivantes concernant les bases de donn�es :
	- le nom : spring.datasource.url=jdbc:postgresql://localhost:5432/*"nom de votre base de donn�es"*
	- l'utilisateur : spring.datasource.username = *"username de la bdd"*
	- le mot de passe : spring.datasource.password =*"votre mot de passe"*
9. Lancez les microservices dans l'ordre suivant :
	1. config-server
	2. eureka-service
	3. zuul-server
	4. microservice-user
	5. microservice-book
	6. clientui
10. Dans PGAdmin via QueryTools impl�mentez les jeu de donn�es :
	1. Pour le microservice book le fichier de jeu de donn�es se trouve dans le microservice book sous src/main/resources/db/data_librarymbook.sql
	2. Pour le microservice user le fichier de jeu de donn�es se trouve dans le microservice user sous src/main/resources/db/data_librarymuser.sql
11. Dans votre navigateur allez sur la page http://localhost:8080/accueil
12. Vous pouvez vous connectez gr�ce aux logins suivants :
	- adresse mail : test@test.fr
	- mot de passe : test
13. Vous pouvez d�s lors naviguez sur le site de la biblioth�que et acc�der au compte test



