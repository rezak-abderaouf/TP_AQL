## Exercice 1 : Maîtriser TestContainers avec OrderDao

Pour cet exercice, j'ai repris la logique de la couche DAO (Data Access Object) vue précédemment (`OrderDao`), mais cette fois-ci, au lieu de la "mocker" (simuler) avec Mockito, j'ai implémenté une véritable connexion JDBC vers une base de données.

**Démarche réalisée :**
1. **Création d'une vraie base de données de test :** J'ai utilisé la bibliothèque Testcontainers (`@Testcontainers` et `@Container`) pour télécharger et démarrer automatiquement une image Docker officielle de **MySQL (8.0.30)** au lancement des tests.
2. **Configuration dynamique :** Testcontainers génère dynamiquement l'URL JDBC (`mysql.getJdbcUrl()`), le port, et les identifiants pour éviter tout conflit avec une base de données locale.
3. **Tests réels :** Le test `OrderDaoTest` crée une table, insère une vraie commande (`Order`) dans la base de données via une requête SQL `INSERT`, puis vérifie sa présence avec un `SELECT`. Le conteneur est ensuite détruit automatiquement à la fin du test.

**Conclusion :** Cette approche garantit que les requêtes SQL (syntaxe, contraintes) sont correctes par rapport au moteur de base de données réel, ce qui offre un niveau de confiance (fiabilité) beaucoup plus élevé qu'un simple mock.
## Exercice 2 : Analyse et Réécriture avec Testcontainers

### 1. Analyse des tests existants
Après avoir analysé le code source du projet `task-manager`, j'ai constaté que le projet ne contient pas de véritables tests d'intégration, mais uniquement des **tests unitaires**.

* **Approche utilisée :** Le développeur initial a utilisé l'approche du "Mocking" intensif.
    * Pour la couche Web (Controllers), il a utilisé `MockMvc` en mode "standalone" sans démarrer le serveur Tomcat.
    * Pour la couche Métier (Services), il a utilisé **Mockito** (`@Mock` et `@InjectMocks`) pour simuler complètement la couche de données (Repositories).

* **Limites de cette approche (Commentaires) :**
    1. **Faible fiabilité sur la persistance :** Puisque les `TaskRepository` sont mockés, on ne vérifie jamais si les requêtes SQL (générées par Hibernate/Spring Data JPA) sont correctes. Un test peut passer avec succès alors que l'application plantera en production à cause d'une erreur de syntaxe SQL ou d'une contrainte de clé étrangère.
    2. **Faux positifs :** Les tests sont trop couplés à l'implémentation (on vérifie que telle méthode a été appelée) au lieu de vérifier le résultat final dans une vraie base de données.
### 2. Analyse et Comparaison (Question 5)

Après avoir réécrit les tests avec Testcontainers, voici la comparaison avec l'approche initiale (Mocking) :

| Critère | Anciens Tests (Mocks) | Nouveaux Tests (Testcontainers) |
| :--- | :--- | :--- |
| **Couverture** | Limitée à la logique métier (Service). Ignore complètement la couche de données (SQL, JPA). | Excellente. Couvre la logique métier **et** la persistance des données réelles dans la base MySQL. |
| **Lisibilité** | Moyenne. Polluée par la configuration des mocks (`when().thenReturn()`). | Très bonne. Le test se lit comme un vrai scénario utilisateur (Arrange, Act, Assert) sans fausses données. |
| **Maintenabilité** | Faible. Si on change l'implémentation interne sans changer le résultat, le test mocké risque de casser (couplage fort). | Élevée. Le test vérifie le résultat final dans la base de données, ce qui le rend résistant au refactoring interne (couplage faible). |
| **Fiabilité** | Faible (Faux positifs fréquents). | Très élevée. On a la garantie que l'application fonctionnera avec une vraie base de données en production. |

#### Avantages et Inconvénients de Testcontainers

**Avantages :**
* **Fiabilité absolue :** Teste contre les mêmes technologies qu'en production (ici, MySQL 8).
* **Isolation :** Chaque test ou classe peut avoir une base de données vierge et isolée, évitant les conflits de données.
* **Zéro configuration locale :** Les autres développeurs n'ont pas besoin d'installer et de configurer MySQL sur leur machine locale ; Docker s'occupe de tout.

**Inconvénients :**
* **Temps d'exécution :** Les tests sont nettement plus lents car il faut télécharger l'image Docker (la première fois) et démarrer un conteneur à chaque exécution.
* **Dépendance système :** Nécessite obligatoirement que Docker (et la virtualisation matérielle) soit installé et en cours d'exécution sur la machine hôte.