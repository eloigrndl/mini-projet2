## Notes de conception

Antoine BELLANGER / Eloi GARANDEL

### Résumé

Nous avons de manière générale suivi le PDF et les consignes données.
Nous avons également créé de nouvelles classes et interfaces pour les extensions et avons tâché de suivre le concept de l'orienté-objet.

### Fichiers Créés

Nous avons créé de nombreux fichiers lors du développement de ce projet.
Cette section va détailler la conception de certains d'entre eux. Les fichiers relatifs aux niveaux (ex: Enigme0, Level3...) seront détaillés une seule fois comme étant similaires.

- `Level[]` / `Enigme[]`

Ces fichiers gèrent donc les instances de niveaux que l'on affiche dans le jeu. Tous les objets présents dans ces niveaux sont initialisés ici.

- `Animation`

C'est une interface qui permet de déterminer quelle image doit être affichée en fonction de l'étape du mouvement et de l'orientation du personnage.

- `InvisibleSignalDoor`

C'est une extension de `SignalDoor` et qui a comme particularité d'être "invisble", c'est-à-dire sans `Sprite`. Nous l'utilisons afin de faire de la téléportation dans `Enigme2` sans que le personnage puisse voir la porte.

- `SignalRing`

C'est un objet supplémentaire que nous avons créé et qui est similaire à `Apple` par exemple mais qui implémente en plus `Logic` afin de pouvoir faire apparaître l'anneau à un certain moment précis.

### Extensions Réalisées

- 7.1.1 Animations
- 7.1.2 Dialogues
- 7.1.3 Pause
- Sprint
- Téléportation
- Nouveaux Acteurs (`HelpingPerson, SignalRing`, `Gelly`)`
- Contrôle pour faire disparaître le dialogue (Touche `K`)
