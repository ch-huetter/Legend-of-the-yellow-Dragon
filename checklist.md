Checkliste vom aktuellen Stands des Projekts!

# Steps

Alle großen Schritte. Festgehalten und abhakbar.

---

## Basics

### Projekt Start

- [x] Projekt in GIt erstellt.
- [x] Grundlegende Projektstruktur erstellt.
- [x] Maven eingerichtet und Dependencies eingebunden.
- [x] Datenbankstruktur geplant und in einem Uml Diagramm festgehalten.
- [x] Entitäten erstellt um Datenbankstruktur in Hibernate abzubilden.

- [x] Automatische Integrity Checks implementiert die den Status der Datenbank abgleichen

- [x] Charaktere definiert und implementiert
- [ ] Spieler Klassen definiert und implementiert
    - [x] Spieler Klasse definiert
    - [ ] Spieler Klasse zu Charakter hinzugefügt
    - [ ] Spieler Klassen Auto Synchro mit Datenbank

Validierung für Erstellte Charaktere bzw für Charaktere die aufgelevelt wurden. Hier muss erstmal
ausgearbeitet werden wie die das Aufleveln der Charakters funktioniert und über welche seite.
Create Seite anpassen damit sie für level Ups funktioniert
States müssen eingefügt und für die umbenannte Character.html angepasst werden. Nutzer soll hier
eben ansehen, bearbeiten und hochleveln können. Dementsprechend müssen hier verschiedene Sichtbarkeits
Stufen angepasst werden
Charakter Klassen werden noch nicht mit in die Werte eingerechnet. Anpassungen an Calculator müssen noch gemacht werden.
Hier muss ein System her das hier
dynamisch Werte anpassen kann. Hier müssen mehrere Momente möglich sein. Vor/Nach Charakter Erstellung oder Vor
Kampfbeginn passive Effekte etc etc.

---

### Gui Basics

- [x] Thymeleaf Layout erstellt um generelle Seitenstruktur für alle eingeloggten Nutzer festgelegt
- [x] LayoutDto definiert und automatische Einrichtung für alle Seiten die es nutzen eingerichtet
- [x] Karousel Java Script geschrieben

---

### Look and Feel

- [x] Genderneutrale Sprache definieren und für alle Textzeilen nutzen
- [x] Grundlegendes Setting und Geschichte definieren in der sich das Spiel abspielen soll

----

## Mechaniken

Implementierte Mechaniken die ins Spiel sollen/können

---

### Basics

- [ ] Dashboard
    - [ ] Erste Schritte Erklären und dem Spieler verständlich machen
    - [ ] Design überarbeiten und an neueres angleichen
- [x] Charakter erstellen
    - [x] Stimmiges Hintergrund Bild generiert
    - [x] Namensfeld
    - [x] Attribute auswählen
    - [x] Klassen Karusel
    - [x] Java Script um das Look and Feel anzupassen und falscheingaben abzufangen
    - [x] Serverseitige Prüfung der Eingaben
    - [x] Speichern der Charaktere in die Datenbank
    - [ ] Link in der Seitenleiste erstellen
- [ ] Charakter verwaltung
    - [x] Stimmiges Hintergrund generiert
    - [ ] Namensanzeige
    - [ ] Neue Attributspunkte verteilbar
    - [ ] Übersicht gewählte Klasse und Boni
    - [ ] Serverseitige Prüfung der Eingabe
    - [ ] Link in der Seitenleiste
- [ ] Taverne um den Charakter zwischen kämpfen zu heilen
    - [ ] Schaltfläche zum rasten
    - [ ] Link in der Seitenleiste
- [ ] Verschiedene Dungeons mit verschiedenen Monstern gegen die man kämpfen kann
    - [ ] Dungeon Struktur definieren. Daraus werden weitere Unterpunkte entstehen
    - [ ] Zwischen Überschrift für Dungeons in Seitenleiste erstellen

- [ ] Kampf System erstellen, das erweiterbar ist um optionale Features beherbergen zu können
    - [ ] Kämpfe geben Belohnungen an Spieler je nach Schwierigkeit
    - [ ] (Optional) Charakter Infight Status definieren sodass ein Charakter zu einen Kampf zurückkehren kann
- [ ] Kampf Seite mit einbindung von Vue Js als One Page Application erstellen
- [ ] Sinnvolles Progression System definieren

---

### Optional

- [ ] Fähigkeiten System implementieren
    - [ ] Charakter kann Fähigkeiten erlernen bzw Ressourcen wie Mana/Wut/Energie Nutzen um diese einzusetzen
    - [ ] Fähigkeiten Punkte verteilung für das erlernen neuer Fähigkeiten
    - [ ] Klassenbindung von Fähigkeiten und erstellen von Fähigkeitsbäumen
- [ ] Kämpfe mit mehreren Kämpfern
    - [ ] Queue System für alle Kämpfer und definition wann diese wieder dran kommen
    - [ ] Angriffe mit mehreren Zielen
    - [ ] Kämpfer können sich in verschiedenen Reihen platzieren was sich auf mögliche Ziele von Fähigkeiten und
      Angriffen auswirkt
- [ ] Charaktere können Ausrüstung tragen
    - [ ] Slot System für Charaktere implementieren
    - [ ] Items definieren die Charaktere Boni geben

