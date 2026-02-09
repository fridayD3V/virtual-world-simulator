# Virtual World Simulator

## About
A turn-based 2D simulation of a biological ecosystem. The project implements a dynamic grid world where various life forms (animals and plants) interact based on specific behaviors. The core goal was to demonstrate **Object-Oriented Programming (OOP)** principles such as polymorphism, inheritance, and encapsulation.

## Tech Stack
* **Language:** Java
* **UI/GUI:** Java Swing
* **Key Concepts:** OOP, File I/O, Event Handling.

## Key Features
* **OOP Architecture:** Uses an abstract base class `Organism` with polymorphic derived classes for `Animal` and `Plant` logic.
* **Simulation Engine:** Turn-based system where entities move, eat, reproduce, and fight based on initiative and strength.
* **Unique Behaviors:**
    * **Predators:** Hunt weaker prey (e.g., Wolf).
    * **Evasive Prey:** Chance to dodge attacks (e.g., Antelope).
    * **Static Hazards:** Plants that poison or boost strength (e.g., Hogweed, Guarana).
* **Player Interaction:** A controllable "Human" character with a cooldown-based special ability (e.g., Immortality, Super Strength).
* **Persistence:** Full Save/Load functionality to store simulation state.

## How to Run
```bash
# Clone the repo
git clone https://github.com/fridayD3V/virtual-world-simulator.git

