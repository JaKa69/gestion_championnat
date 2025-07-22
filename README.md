# Application de gestion de championnat

Cette application web permet de gérer des championnats sportifs, les utilisateurs, les équipes, les matchs, ainsi que de consulter les classements.

---

## Démarrage rapide
### 1. Créer une BDD
Avant toute chose, il faut créer une base de donnée mysql nommée "gestion_championnat". 
La connexion MySql doit avoir les identifiants données dans le fichier **application.properties**
### 1. Décommenter les données de test

Avant de lancer l'application pour la première fois :

1. Ouvrez le fichier `DataInitializer.java`.
2. Décommentez les lignes qui :
    - Créent les **tables** ;
    - Injectent des **données de test** (utilisateurs, championnats, équipes, matchs, etc.).
3. Relancez l'application afin que les données soient insérées automatiquement dans la base.

---

## Authentification

### Connexion utilisateur

- Email : `user@example.com`
- Mot de passe : `user123`
### Connexion administrateur

- Email : `admin@example.com`
- Mot de passe : `admin123`

Une fois connecté en tant qu’administrateur, vous pourrez accéder à toutes les pages d'administration.

### Création d’un compte utilisateur

1. Cliquez sur `Home`, puis sur le bouton **Connexion ou inscription**.
2. Renseignez votre nom, votre adresse email et votre mot de passe.
3. Vous pourrez ensuite vous connecter pour accéder aux fonctionnalités en lecture.

---

## Navigation utilisateur

Le menu principal permet de naviguer dans l’application :

| Lien                    | Description                                  |
|-------------------------|----------------------------------------------|
| `Home`                  | Page d’accueil                               |
| `Championnats`          | Liste des championnats disponibles           |
| `Classement`            | Classement des équipes par championnat       |
| `Matchs`                | Affichage des matchs déjà insérés            |
| `Tableau de bord admin` | Espace réservé aux administrateurs           |

---

## Espace Administrateur

Accessible après connexion avec un compte administrateur.

Un deuxième menu "Admin:" permet de gérer toutes les entités du système :

### Gestion des utilisateurs
- Afficher la liste des utilisateurs
- Créer un nouvel utilisateur
- Modifier un utilisateur existant
- Supprimer un utilisateur

### Gestion des championnats
- Consulter les championnats existants
- Créer un championnat
- Modifier un championnat
- Supprimer un championnat

### Gestion des équipes
- Consulter les équipes enregistrées
- Ajouter une équipe
- Modifier les informations d'une équipe
- Supprimer une équipe

### Gestion des matchs
- Consulter la liste des matchs joués
- Ajouter un nouveau match
- Modifier les scores ou les équipes
- Supprimer un match

---

## Données de test disponibles après initialisation

- 1 administrateur
- Plusieurs utilisateurs
- Quelques championnats
- Équipes associées aux championnats
- Journées et matchs avec scores

---
