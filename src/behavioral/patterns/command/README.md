# Command Design Pattern

## 📌 Overview
The **Command Pattern** is a behavioral design pattern that turns a request into a stand-alone object that contains all information about the request. This transformation lets you pass requests as method arguments, delay or queue a request's execution, and support undoable operations.



## 🏗️ Structure
The pattern consists of four main components:
* **Command:** An interface that declares the `execute()` and `undo()` methods.
* **Concrete Command:** Implements the interface and links the **Receiver** to an action.
* **Receiver:** The object that performs the actual business logic (e.g., a Document or Light).
* **Invoker:** The object that triggers the command (e.g., a Button or History Manager).

## 🚀 Key Features
* **Decoupling:** The class that invokes the operation is separated from the one that knows how to perform it.
* **Undo/Redo:** By storing commands in a stack, you can revert state changes easily.
* **Macro Commands:** Multiple commands can be grouped into a single sequence.

---
