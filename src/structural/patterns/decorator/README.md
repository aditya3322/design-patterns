# Decorator Pattern

## Overview

The **Decorator Pattern** is a structural design pattern that allows you to add new responsibilities to an object dynamically while keeping the same interface. It's an alternative to subclassing for extending functionality.

Instead of creating multiple subclasses to handle different combinations of features, decorators wrap an object and add behavior by delegating to the wrapped object while adding their own functionality.

---

## Problem Statement

Consider a notification system where you need to send messages through different channels:
- **Email** (base functionality)
- **SMS** (optional addition)
- **Slack** (optional addition)

Without the Decorator Pattern, you might create classes like:
- `EmailNotifier`
- `EmailAndSMSNotifier`
- `EmailAndSlackNotifier`
- `EmailSMSAndSlackNotifier`
- ... and more combinations!

This leads to **class explosion** and violates the Open/Closed Principle.

---

## Solution: Decorator Pattern

The Decorator Pattern solves this by:
1. Defining a common interface (`Notifier`)
2. Creating a base decorator class that wraps another notifier
3. Creating specific decorators that extend the base decorator and add their own behavior

---

## Implementation Details

### 1. **Notifier Interface**
```java
public interface Notifier {
    void send(String message);
}
```
This is the component interface that both concrete components and decorators implement.

### 2. **Concrete Component: EmailNotifier**
```java
public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending Email notification: " + message);
    }
}
```
The base functionality that can be decorated.

### 3. **Base Decorator: BaseDecorator**
```java
public abstract class BaseDecorator implements Notifier {
    protected final Notifier wrappedNotifier;

    public BaseDecorator(Notifier notifier) {
        this.wrappedNotifier = notifier;
    }

    @Override
    public void send(String message) {
        wrappedNotifier.send(message);
    }
}
```
An abstract base class that:
- Implements the `Notifier` interface
- Holds a reference to a wrapped notifier
- Delegates the `send()` method to the wrapped notifier

### 4. **Concrete Decorators: SlackNotifier & SMSNotifier**

**SlackNotifier:**
```java
public class SlackNotifier extends BaseDecorator {
    public SlackNotifier(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);           // Delegate to wrapped notifier
        sendSlack(message);            // Add Slack functionality
    }

    private void sendSlack(String message) {
        System.out.println("Sending Slack notification: " + message);
    }
}
```

**SMSNotifier:**
```java
public class SMSNotifier extends BaseDecorator {
    public SMSNotifier(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);           // Delegate to wrapped notifier
        sendSMS(message);              // Add SMS functionality
    }

    private void sendSMS(String message) {
        System.out.println("Sending SMS notification: " + message);
    }
}
```

Each decorator:
- Calls the wrapped notifier's `send()` method first
- Adds its own functionality after delegation

---

## Usage Example

```java
// Create a basic Email notifier
Notifier emailNotifier = new EmailNotifier();

// Decorate it with SMS and Slack notifications
Notifier smsAndSlackNotifier = new SMSNotifier(new SlackNotifier(emailNotifier));

// Send a notification
smsAndSlackNotifier.send("Hello, this is a notification!");
```

### Output:
```
Sending Email notification: Hello, this is a notification!
Sending Slack notification: Hello, this is a notification!
Sending SMS notification: Hello, this is a notification!
```

### How it works:
1. `smsAndSlackNotifier.send()` is called
2. SMSNotifier delegates to SlackNotifier
3. SlackNotifier delegates to EmailNotifier
4. EmailNotifier prints the email message
5. Control returns to SlackNotifier, which prints the slack message
6. Control returns to SMSNotifier, which prints the SMS message

---

## Key Characteristics

| Aspect | Details |
|--------|---------|
| **Pattern Type** | Structural |
| **Intent** | Attach additional responsibilities to an object dynamically |
| **Participants** | Component, ConcreteComponent, Decorator, ConcreteDecorator |
| **Flexibility** | Yes - can compose decorators at runtime in any order |
| **Transparency** | Decorators are transparent to the client |

---

## Advantages

✅ **Single Responsibility Principle** - Each decorator handles one responsibility  
✅ **Open/Closed Principle** - Open for extension, closed for modification  
✅ **Flexible Composition** - Combine decorators at runtime in any order  
✅ **Avoids Class Explosion** - No need for multiple subclasses for every combination  
✅ **Dynamic Behavior** - Add/remove functionality at runtime  

---

## Disadvantages

❌ **Complexity** - Can create many small classes  
❌ **Order Matters** - The order of decorators can affect behavior  
❌ **Stack Traces** - Debugging can be more difficult with multiple layers  

---

## Real-World Applications

- **I/O Streams** - Java's `BufferedInputStream`, `GZIPInputStream` wrapping `FileInputStream`
- **Web Frameworks** - Middleware in HTTP request handling
- **UI Components** - Adding borders, scrollbars, or styling to UI elements
- **Logging** - Adding logging functionality to existing methods
- **Caching** - Wrapping a data source with a cache layer
- **Authentication** - Adding security layers to API calls

---

## Comparison with Inheritance

| Aspect | Inheritance | Decorator |
|--------|-------------|-----------|
| **Flexibility** | Fixed at compile time | Dynamic at runtime |
| **Number of Classes** | Grows exponentially with features | Grows linearly with features |
| **Reusability** | Limited by hierarchy | Highly composable |
| **Complexity** | Simple for few combinations | Better for many combinations |

---

## Related Patterns

- **Strategy Pattern** - Encapsulates algorithms (vs. Decorator which adds behavior)
- **Proxy Pattern** - Controls access (vs. Decorator which adds functionality)
- **Adapter Pattern** - Changes interface (vs. Decorator which keeps interface)


