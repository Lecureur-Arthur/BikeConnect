Description du projet
Le but de ce projet est de rendre un vélo normal, un vélo intelligent et connecté. L'application mobile pourra surveiller en temps réel les performances de l'utilisateur avec des capteurs et un microcontrôleur.
Fonctionnalités principales :
Afficher la vitesse instantanée en temps réel.
Se localiser avec une carte simulée.
Consulter les conditions météorologiques.
Système de login pour utilisation sécurisée.
Notification de sécurité (exemple : ralentir sur une pente dangereuse - futur développement).
Technologies utilisées
Développement de l'application
Langage : Java
IDE : Android Studio
Bibliothèques principales :
OpenStreetMap pour l'affichage de la carte.
Retrofit pour les appels réseau (météo).
Firebase (ou équivalent) pour l’authentification.
Matériel (Hardware)
Microcontrôleur : ESP32 (connexion Bluetooth).
Capteurs :
Capteur de vitesse (compteur de tours de roue).
Capteur GPS (intégré ou via le smartphone).
Capteur de température et humidité (optionnel).
Batterie : Batería recargable Li-ion 3.7V o batería dinamo integrada.
Arquitectura técnica
Los sensores recopilan datos, como velocidad y posición, y los envían, mediante Bluetooth, al microcontrolador ESP32.
La aplicación Android recibe los datos y los muestra en tiempo real.
Un sistema de login protege las datas del usuário.
Some features are the map and weather; both make use of third-party APIs for the development of the user's experience.
Demo and current limitations
Pour la démonstration, les données des capteurs sont simulées via un fichier JSON afin de valider le fonctionnement de l'application sans matériel physique. Si un budget nous était attribué, nous pourrions développer l'intégration matérielle complète pour rendre le projet opérationnel.
