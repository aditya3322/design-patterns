# Facade Pattern

## Overview

The **Facade Pattern** is a structural design pattern that provides a unified, simplified interface to a set of interfaces in a subsystem. It defines a higher-level interface that makes the subsystem easier to use by hiding its complexity.

The Facade acts as a "wrapper" that simplifies the interaction with multiple complex subsystems, allowing clients to work with a single, straightforward interface instead of dealing with multiple interdependent objects.

---

## Problem Statement

Consider a home theater system with multiple components:
- **Lights** - Can be turned on/off and dimmed
- **Projector** - Can be turned on/off and set to different inputs
- **Sound System** - Can be turned on/off and volume adjusted
- **Streaming Player** - Can play/stop movies and be powered on/off

To watch a movie, you need to:
1. Dim the lights to 10%
2. Turn on the projector
3. Set the projector input to the streaming player
4. Turn on the sound system
5. Set volume to 5
6. Turn on the streaming player
7. Play the movie

Without the Facade Pattern, the client would need to understand and coordinate all these components, creating tight coupling and complexity.

---

## Solution: Facade Pattern

The Facade Pattern solves this by creating a single unified interface (`HomeTheaterFacade`) that coordinates all the subsystems. Now the client only needs to call:

```java
movieNight.watchMovie("Inception");
```

---

## Implementation Details

### 1. **Subsystem Classes**

These are the complex components that need to be coordinated:

**Lights:**
```java
public class Lights {
    public void on() { ... }
    public void off() { ... }
    public void dim(int level) { ... }
}
```

**Projector:**
```java
public class Projector {
    public void on() { ... }
    public void off() { ... }
    public void setInput(String source) { ... }
}
```

**SoundSystem:**
```java
public class SoundSystem {
    public void on() { ... }
    public void off() { ... }
    public void setVolume(int level) { ... }
}
```

**StreamingPlayer:**
```java
public class StreamingPlayer {
    public void on() { ... }
    public void off() { ... }
    public void play(String movie) { ... }
    public void stop() { ... }
}
```

### 2. **Facade Class: HomeTheaterFacade**

```java
public class HomeTheaterFacade {
    private Lights lights;
    private Projector projector;
    private SoundSystem soundSystem;
    private StreamingPlayer streamingPlayer;

    public HomeTheaterFacade(Lights lights, Projector projector, 
                            SoundSystem soundSystem, 
                            StreamingPlayer streamingPlayer) {
        this.lights = lights;
        this.projector = projector;
        this.soundSystem = soundSystem;
        this.streamingPlayer = streamingPlayer;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        lights.dim(10);                           // Dim lights
        projector.on();                           // Turn on projector
        projector.setInput("Streaming Player");   // Set input
        soundSystem.on();                         // Turn on sound
        soundSystem.setVolume(5);                 // Set volume
        streamingPlayer.on();                     // Turn on player
        streamingPlayer.play(movie);              // Play movie
    }

    public void endMovie() {
        System.out.println("Shutting down movie theater...");
        streamingPlayer.stop();                   // Stop playback
        streamingPlayer.off();                    // Power off player
        soundSystem.off();                        // Power off sound
        projector.off();                          // Power off projector
        lights.on();                              // Turn lights on
    }
}
```

The Facade:
- Holds references to all subsystem components
- Provides simplified methods (`watchMovie()`, `endMovie()`)
- Coordinates multiple subsystem calls internally
- Hides complexity from the client

---

## Usage Example

```java
// Initialize subsystems
Lights lights = new Lights();
Projector projector = new Projector();
SoundSystem soundSystem = new SoundSystem();
StreamingPlayer streamingPlayer = new StreamingPlayer();

// Create facade
HomeTheaterFacade movieNight = new HomeTheaterFacade(lights, projector, 
                                                      soundSystem, streamingPlayer);

// Simple interface - one method call instead of 7!
System.out.println("--- Getting ready to watch a movie ---");
movieNight.watchMovie("Inception");

System.out.println("\n--- Movie is over ---");
movieNight.endMovie();
```

### Output:
```
--- Getting ready to watch a movie ---
Get ready to watch a movie...
Dimming lights to 10%
Projector is ON
Setting projector input to Streaming Player
Sound system is ON
Setting sound system volume to 5
Streaming player is ON
Playing movie: Inception

--- Movie is over ---
Shutting down movie theater...
Stopping streaming player
Streaming player is OFF
Sound system is OFF
Projector is OFF
Lights are ON
```

---

## Key Characteristics

| Aspect | Details |
|--------|---------|
| **Pattern Type** | Structural |
| **Intent** | Provide a unified interface to a subsystem |
| **Participants** | Facade, Subsystem Classes |
| **Complexity Reduction** | Yes - hides subsystem complexity |
| **Loose Coupling** | Yes - clients depend only on the facade |

---

## Advantages

✅ **Simplification** - Complex subsystems become easy to use  
✅ **Loose Coupling** - Clients don't need to know about subsystem details  
✅ **Centralized Control** - All subsystem coordination happens in one place  
✅ **Flexibility** - Can change subsystem implementation without affecting clients  
✅ **Reduced Dependencies** - Clients depend on one facade instead of many subsystems  
✅ **Easier Testing** - Can mock the facade for testing  

---

## Disadvantages

❌ **God Object** - Facade can become a "god object" with too many responsibilities  
❌ **Limited Flexibility** - Facade methods are fixed; custom combinations may not be supported  
❌ **Hidden Complexity** - The actual complexity still exists, just hidden  
❌ **Over-Abstraction** - Simple tasks may become unnecessarily abstract  

---

## Real-World Applications

- **JDBC API** - Provides a simplified interface to complex database operations
- **Spring Framework** - `DispatcherServlet` acts as a facade for web request handling
- **Apache Commons** - Various utility classes provide simple interfaces to complex operations
- **HTTP Clients** - Libraries like Retrofit provide a simplified interface to HTTP operations
- **Database Drivers** - Hide the complexity of network protocols and data formats
- **UI Frameworks** - Button click handlers abstract away complex event handling
- **Operating Systems** - System calls provide a facade to complex kernel operations

---

## Comparison with Other Patterns

| Pattern | Purpose | When to Use |
|---------|---------|------------|
| **Facade** | Simplify subsystem access | When subsystems are complex and interdependent |
| **Adapter** | Convert interfaces | When incompatible interfaces need to work together |
| **Proxy** | Control access | When you need to control or intercept access |
| **Decorator** | Add functionality | When you need to add behavior dynamically |

---

## When to Use Facade Pattern

Use the Facade Pattern when:

1. **Complex Subsystems** - You have a complex set of classes that clients shouldn't interact with directly
2. **Decoupling** - You want to reduce dependencies between client and subsystem
3. **Simplified API** - You want to provide a simple interface to a complex library
4. **Layered Architecture** - You're building layered systems and want each layer to have a simple interface
5. **Frequent Coordination** - Multiple subsystem methods are always called together
6. **Evolutionary Design** - You want to gradually refactor a monolithic system

---

## Example: Adding More Functionality

```java
// Extend the facade with more convenient methods
public class HomeTheaterFacade {
    // ...existing code...

    public void watchSports(String sport) {
        System.out.println("Get ready to watch " + sport + "...");
        lights.on();                             // Brighter for sports
        projector.on();
        projector.setInput("Streaming Player");
        soundSystem.on();
        soundSystem.setVolume(8);                // Louder for sports
        streamingPlayer.on();
        streamingPlayer.play(sport);
    }

    public void listenToMusic(String album) {
        System.out.println("Time for some music...");
        lights.dim(50);
        soundSystem.on();
        soundSystem.setVolume(7);
        streamingPlayer.on();
        streamingPlayer.play(album);
    }
}
```

Now clients have multiple convenient methods for different use cases, all built on top of the same subsystems.

---

## Best Practices

1. **Keep Facades Simple** - Don't let the facade become too complex
2. **Provide Multiple Facades** - Different facades for different use cases (e.g., MovieFacade, MusicFacade)
3. **Don't Hide Everything** - Allow direct access to subsystems for advanced use cases
4. **Document Facade Methods** - Clearly explain what each method does
5. **Use Composition** - Use composition to build facades, not inheritance

---

## Related Patterns

- **Adapter** - Also provides a simplified interface, but for incompatible components
- **Mediator** - Similar to facade, but used for peer-to-peer communication
- **Proxy** - Also provides a simplified interface, but focuses on access control
- **Singleton** - Often used with facades to ensure single instance


