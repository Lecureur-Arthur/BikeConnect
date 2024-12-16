Description du projet
  Le but de ce projet est de rendre un vélo normal, un vélo intelligent et connecté. L'application mobile pourra surveiller en temps réel les performances de l'utilisateur 
avec des capteurs et un microcontrôleur.

+ Fonctionnalités principales :

Afficher la vitesse instantanée en temps réel.

Se localiser avec une carte simulée.

Consulter les conditions météorologiques.

Système de login pour utilisation sécurisée.

Notification de sécurité (exemple : ralentir sur une pente dangereuse - futur développement).

+ Technologies utilisées

Développement de l'application
Langage : Java
IDE : Android Studio

+ Bibliothèques principales :

OpenStreetMap pour l'affichage de la carte.

Retrofit pour les appels réseau (météo).

Firebase (ou équivalent) pour l’authentification.

Matériel (Hardware)

Microcontrôleur : ESP32 (connexion Bluetooth).

+ Capteurs :

Capteur de vitesse (compteur de tours de roue).

Capteur GPS (intégré ou via le smartphone).

Capteur de température et humidité (optionnel).

+ Architecture technique

Les capteurs collectent les données (vitesse, position, etc.) et les transmettent via Bluetooth au microcontrôleur (ESP32).

L'application Android reçoit les données et les affiche en temps réel.

Un système de login protège les données de l’utilisateur.

Les fonctionnalités comme la carte et la météo utilisent des API tierces pour enrichir l’expérience utilisateur.


