# Legend of the Yellow Dragon

**Legend of the Yellow Dragon** ist ein browserbasiertes Fantasy-Textadventure, das mit Spring Boot entwickelt wird.  
Das Projekt dient als privates Lern- und Portfolio-Projekt zur Vertiefung moderner Java-Webentwicklung.

Das Projekt kombiniert ein Spring-Boot-Backend, eine Thymeleaf-basierte Verwaltungsoberfläche und eine React-basierte Spieloberfläche.  
Der Fokus liegt auf strukturierter Spiellogik, sauberer Schichtenarchitektur und einer klaren Trennung zwischen Administration und Spieloberfläche.

## Ziel des Projekts

Ziel ist die Entwicklung eines einfachen, aber erweiterbaren Browsergames, bei dem Spieler Entscheidungen treffen, Szenen erkunden und sich durch eine Fantasy-Welt bewegen.

Das Projekt soll zeigen:

- Entwicklung einer Java-Webanwendung mit Spring Boot
- Aufbau einer administrativen Oberfläche mit Thymeleaf
- Umsetzung einer interaktiven Spieloberfläche mit React
- Trennung zwischen Backend, Admin-UI und Game-UI
- strukturierte Spiellogik über Controller-, Service- und Model-Schicht
- Datenbankanbindung mit MySQL

## Tech Stack

### Backend

- Java 17
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- Validation
- Maven

### Admin-Frontend

- Thymeleaf
- HTML
- SCSS
- Bootstrap
- JavaScript
- HTMX

### Game-Frontend

- React
- JavaScript
- SCSS
- REST/API-Kommunikation mit dem Spring-Boot-Backend

### Datenbank & Tools

- MySQL
- Git
- IntelliJ IDEA / WebStorm

## Architektur

Das Projekt ist in drei Hauptbereiche aufgeteilt:

### Backend

Das Spring-Boot-Backend enthält die zentrale Geschäftslogik, Datenbankanbindung und API-Endpunkte.

### Administration

Die Verwaltungsoberfläche wird mit Thymeleaf umgesetzt.  
Sie dient dazu, Inhalte und Konfigurationen des Spiels zu pflegen.

### Spieloberfläche

Die eigentliche Spieloberfläche wird mit React umgesetzt.  
Ältere Thymeleaf-basierte Spielansichten werden schrittweise ersetzt und sind nicht mehr Teil des aktuellen Entwicklungsfokus.

## Aktueller Funktionsumfang

Aktuell umgesetzt oder in Arbeit:

- Spring-Boot-Backend als zentrale Anwendungsschicht
- Administrationsbereich mit Thymeleaf
- React-basierte Spieloberfläche
- Grundstruktur für Controller-, Service- und Model-Schicht
- lokale Datenbankanbindung über MySQL
- erste UI-Komponenten für Verwaltung und Spielansicht
- erste Spiellogik und Szenenverwaltung

## Geplante Funktionen

- Charaktererstellung
- Szenen- und Entscheidungslogik
- Seiten der Spieloberfläche, die ursprünglich in Thymeleaf geschrieben wurden, in React überführen
- einfache Kampf- oder Ereignislogik
- Speicherung des Spielfortschritts
- Ausbau der Benutzeroberfläche
- bessere Fehlerbehandlung
- Tests für zentrale Spiellogik
- Deployment auf einem Raspberry Pi oder Server

## Installation und lokaler Start

### Voraussetzungen

Für den lokalen Start werden benötigt:

- Java 17 oder höher
- Maven
- MySQL-Datenbank
- Git
- Node.js / npm

### Repository klonen

```bash
git clone https://github.com/DonPromille/Legend-of-the-yellow-Dragon.git
cd Legend-of-the-yellow-Dragon
```

### Datenbank einrichten

Für den lokalen Start muss eine MySQL-Datenbank vorhanden sein. Die Zugangsdaten werden über eine lokale .dev gesetzt
Siehe .dev.example im Root Verzeichnis

### Backend starten

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
### Game-Frontend starten

In einem separaten Terminal:

```bash
cd frontend
npm install
npm run dev
```

## Entwicklungsstand

Das Projekt befindet sich aktuell in aktiver Entwicklung.  
Der Administrationsbereich wird mit Thymeleaf umgesetzt, während die eigentliche Spieloberfläche schrittweise nach React überführt wird.

Ältere Thymeleaf-basierte Spielansichten sind daher nicht mehr Teil des aktuellen Entwicklungsfokus.
