# TweetBook
#Présentation
Le responsable de votre entreprise souhaite créer son propre réseau social (concurrent des Google+, Facebook, Twitter,
LinkedIn, Tumblr) pour aider à la communication entre ses clients et ses employés. Pour cela il vous demande d’écrire
une nouvelle application WEB tweetBook permettant à chacun de diffuser des messages à ses amis à travers la notion
de réseau social. Le site doit fonctionner de la manière suivante :
Pour pouvoir interagir dans cette application, une personne doit être enregistrée. La page d’entrée sur le site propose à
chacun soit de se connecter via un formulaire d’identification soit d’accéder à une page d’enregistrement qui demande à
ce nouvel utilisateur ses informations personnelles (nom, prénom, date de naissance, email) ainsi que le login/mdp qu’il
souhaite utiliser.
Une fois identifié sur le site, chaque utilisateur a accès à 4 pages :
<br>
**Mur** qui permet de poster de nouveaux messages et d’afficher toutes les autres actualités que l’on a généré du plus récent
au plus ancien. Le propriétaire est le seul à pouvoir écrire sur son mur. Il y a deux types d’actualités : les messages
et les informations sur les nouveaux amis.
<br>
**Amis** qui permet d’afficher ses amis mais aussi d’en rechercher et ajouter de nouveaux. Le lien amis est symétrique, si
on est ami avec quelqu’un la réciproque est vraie aussi, il n’y a pas d’acceptation necessaire. Cette information est
diffusée comme nouvelle actualité sur son mur, sur son Fil d’actualité, ainsi que sur le Fil de ses amis.
*Fil d’actualités* qui permet d’afficher les actualités générées par ses amis (depuis qu’on est amis) ou par soi même, de la
plus récente à la plus ancienne. Sur cette page on voit donc les messages entiers générés par ses amis ainsi que les
autres actualités que nous, ou nos amis ont généré.
– Poster un message sur son mur génère une nouvelle actualité affichée sur son Fil, mais aussi sur le Fil de ses
amis.
– Une actualité peut faire référence à un utilisateur (“Paul est ami avec Pierre”) et/ou à un message (“Paul aime le
message x”). Ces références sont cliquables et permettent d’accéder pour un utilisateur au mur de cette personne
et pour le message au corps de ce message.
– On notera que le Mur n’est en fait qu’une restriction du Fil d’actualité à la personne.
*Administration* qui permet à l’utilisateur de changer son mot de passe et le limiter la visibilité de son mur. Trois visibilités
sont possibles : aucune, amis, tous. Si une actualité fait référence à un message, même si la visibilité du mur
est restreinte, il est toujours possible de voir ce message et “l’aimer”.
Pour simplifier nous considèrerons qu’on ne peut écrire sur le “mur” de ses amis, on ne peut pas “tchatter” avec ses amis
ni leur envoyer de messages personnels, on ne commente pas les messages hormis via “j’aime” , les demandes d’amis
sont automatiquement acceptées et sont symétriques, on ne supprime rien (ni les amis, ni les messages), on ne limite pas
non plus les tailles des murs et des fils d’actualités.
Améliorations fonctionnelles :
– Dans sa première version le système ne permettra de poster que des messages Texte, sans gestion des “aime”.
– Dans une seconde étape, on introduit l’aspect “viral” d’un véritable réseau social : les messages issus d’autres personnes
que le propriétaire sont complétés par l’affichage de deux compteurs et deux boutons “J’aime” et “J’aime pas”. Cliquer
sur l’un de ces boutons incrémente le compteur associé propre à ce message et génère un nouveau type d’actualité pour
ses amis. On ne peut “aimer” que les messages postés, pas les messages d’information “amis” ou “aime”.
– Dans une troisième étape, afin de “simuler” un LDAP, faire en sorte que l’authentification ne se fasse pas sur la même
base que le reste du système (authentification sous Postgres, reste sous SQLite par ex)
– Dans une quatrième étape, ajouter à la page de login un bouton “J’ai oublié mon mot de passe” qui renvoie le mot de
passe par email au login correspondant.
– Dans une cinquième étape, le système permettra d’autres types de messages : url et photo
<br>
#Travail à faire
1. Le travail peut être réalisé en binôme, mais les responsabilités doivent être établies. A la fin, une feuille des tâches
avec son responsable doit être impérativement rendue.
2. Etablir le MCD et la base permettant de gérer cette application. C’est le coeur du problème ! Comment stocker
l’information ? comment l’interroger ?
3. Créer la base et la remplir avec quelques données pertinentes pour tester les cas critiques
4. Faites sobre et modeste au départ. inutile de faire joli ... l’important est déjà d’avoir une base et la page Fil
d’actualités austère mais fonctionnelle pour que chacun puisse travailler ! Tout le reste peut être complété par
la suite ... Authentification, notation, CSS, Pool, pages Amis et Admin, SSL, etc ...
1. Une documentation décrivant le projet réalisé, les choix faits et les technologies employées devra être fournie en
PDF. Elle comprendra notamment 4 parties totalement indépendantes :
(a) Déploiement. Décrit l’installation et le déploiement de ce projet. Plus cette documentation sera courte, mieux
ce sera. Notamment, le déploiement sur une machine donnée, avec un nom de contexte donné et un nom de
base donné doit nécessiter le moins de travail possible.
(b) Synthèse
i. Objectif du travail
ii. Ce qui a été fait
iii. Les améliorations à apporter
iv. En quoi ce projet était difficile
v. Conclusion
(c) La description technique du projet
i. Objectif techniques du travail
ii. Principe de réalisation
iii. Difficultés techniques rencontrées et solutions apportées.
iv. Conclusion
(d) La documentation utilisateur (tutorial)
i. Objectif de cette application
ii. Du point de vue de l’utilisateur
<br>
#Contrôle du travail
Vous pouvez utiliser toutes les technologies que vous souhaitez (Servlet, JSP, PHP, ASP, .NET, CGI, Zope, Rails, Perl,
WinDeV, ...) néanmoins le projet doit tourner en salle TP et vous devez pouvoir en faire une démonstration. L’attention
sera particulièrement portée sur les technologies que vous mettrez en oeuvre pour réaliser ce site, notamment :
– L’ergonomie du site,
– Utilisation d’objets externalisés (Beans, ActiveX, ...). Un minimum de code doit apparaître dans les pages.
– Code coté client vérifiant autant que possible les informations saisies (JavaScipt, VBScript, ...)
– Feuilles de styles externalisées (CSS, ...)
– Eviter l’injection SQL
– Entêtes mises en commun (fichiers includes, les logos DA2I et Lille1 doivent figurer sur toutes les pages ...)
– Système de trace d’activité du serveur (Logger, Valve...)
– Système d’authentification (Realm, ....)
– Accès aux deux bases via un pool de connexions
– Cryptage des informations via un serveur sécurisé (SSL,...)
– Paramètres dans web.xml pour tout ce qui pourrait changer (logo, nom de la formation, base de données etc ..)
– Validation HTML des pages par Tidy ou par le W3C. Présence de l’icône de test sur chaque page.
Une fois cela réalisé, rendre sur Moodle une archive portant vos noms (ex DurandDupont.zip) et contenant la doc,
un fichier README pour le déploiement, les scripts SQL nécessaires et le WAR de votre application