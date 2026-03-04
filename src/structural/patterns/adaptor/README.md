# Adapter Pattern

## Overview

The **Adapter Pattern** is a structural design pattern that allows objects with incompatible interfaces to work together. It acts as a "translator" or "converter" between two incompatible interfaces, making them compatible without changing their source code.

The Adapter wraps an object (the adaptee) and provides an interface that is compatible with what the client expects. This allows legacy code, third-party libraries, or incompatible components to be used together seamlessly.

---

## Problem Statement

Imagine you have:
- A **RoundHole** that expects **RoundPeg** objects with a `getRadius()` method
- A **SquarePeg** class with a `getWidth()` method that cannot be modified

The problem: **RoundHole cannot accept SquarePeg objects** because they have incompatible interfaces:
- `RoundPeg` provides `getRadius()`
- `SquarePeg` provides `getWidth()`

Without the Adapter Pattern, you would need to:
1. Modify the existing classes (not always possible)
2. Create duplicate implementations
3. Create complex workarounds

This violates the Open/Closed Principle and creates tight coupling.

---

## Solution: Adapter Pattern

The Adapter Pattern solves this by creating an adapter class that:
1. Wraps the incompatible object (SquarePeg)
2. Implements the expected interface (extends RoundPeg)
3. Translates between the two interfaces

### Class Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                          CLIENT                                 │
│                      (RoundHole)                                │
│                                                                 │
│  - radius: double                                               │
│  + fits(RoundPeg): boolean                                      │
└────────────────────────────┬────────────────────────────────────┘
                             │
                             │ uses
                             │
                 ┌───────────▼─────────────┐
                 │    ◄◄interface►►        │
                 │      RoundPeg           │
                 ├─────────────────────────┤
                 │   + getRadius(): double │
                 └───────────┬─────────────┘
                             │
                  ┌──────────┴──────────┐
                  │                     │
         ┌────────▼────────┐   ┌────────▼──────────────┐
         │  RoundPeg       │   │ ◄◄Adapter►►           │
         │ (Concrete)      │   │ SquarePegAdapter      │
         ├─────────────────┤   ├─────────────────────────┤
         │- radius: double │   │ - peg: SquarePeg       │
         ├─────────────────┤   ├─────────────────────────┤
         │+ RoundPeg(...)  │   │ + SquarePegAdapter(...) │
         │+ getRadius()    │   │ + getRadius(): double   │
         └─────────────────┘   └────────────┬────────────┘
                                            │
                                      wraps │
                                            │
                                    ┌───────▼──────────┐
                                    │  SquarePeg       │
                                    │  (Adaptee)       │
                                    ├──────────────────┤
                                    │ - width: double  │
                                    ├──────────────────┤
                                    │ + SquarePeg(...) │
                                    │ + getWidth()     │
                                    └──────────────────┘
```

**Legend:**
- `Client (RoundHole)` - Expects RoundPeg interface
- `Target (RoundPeg)` - The expected interface
- `Adaptee (SquarePeg)` - The incompatible class
- `Adapter (SquarePegAdapter)` - Bridges between the client and adaptee

---

## Implementation Details

### 1. **Target Interface: RoundPeg**
```java
public class RoundPeg {
    private double radius;

    public RoundPeg(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
```
This is the interface that the client (RoundHole) expects.

### 2. **Adaptee: SquarePeg**
```java
class SquarePeg {
    private double width;

    public SquarePeg(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }
}
```
This is the incompatible class that needs to be adapted. It has a different interface than what the client expects.

### 3. **Client: RoundHole**
```java
public class RoundHole {
    private double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    public boolean fits(RoundPeg peg) {
        return this.radius >= peg.getRadius();
    }
}
```
The client expects objects that implement the RoundPeg interface.

### 4. **Adapter: SquarePegAdapter**
```java
class SquarePegAdapter extends RoundPeg {
    private SquarePeg peg;  // Holds the adaptee

    public SquarePegAdapter(SquarePeg peg) {
        this.peg = peg;
    }

    @Override
    public double getRadius() {
        // Convert square width to equivalent round radius
        // For a square inscribed in a circle:
        // Radius = (width * sqrt(2)) / 2
        return (peg.getWidth() * Math.sqrt(2)) / 2;
    }
}
```

The adapter:
- **Extends RoundPeg** - Implements the target interface
- **Wraps SquarePeg** - Holds a reference to the adaptee
- **Translates Methods** - Converts `getWidth()` to `getRadius()`
- **Performs Logic** - Calculates the equivalent radius for a square peg

---

## Mathematical Conversion

The adapter converts a square peg's width to an equivalent circular radius:

```
For a square with width W, the diagonal = W * √2

When a square is inscribed in a circle, the diagonal equals the diameter:
Diameter = W * √2
Radius = (W * √2) / 2
```

Example:
- Square peg with width 10: Radius = (10 * √2) / 2 ≈ 7.07
- Round hole with radius 5: Peg does NOT fit
- Round hole with radius 8: Peg fits

---

## Usage Example

```java
public class AdapterDemo {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5); // Hole with radius 5
        
        // 1. Standard Round Peg (compatible)
        RoundPeg rpeg = new RoundPeg(5);
        System.out.println("Does round peg (r5) fit? " + hole.fits(rpeg)); // true

        // 2. Square Pegs (incompatible without adapter)
        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(10);

        // hole.fits(smallSqPeg); // This would NOT compile!

        // 3. Using the Adapter
        SquarePegAdapter smallAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largeAdapter = new SquarePegAdapter(largeSqPeg);

        System.out.println("Does square peg (w2) fit? " + hole.fits(smallAdapter)); // true
        System.out.println("Does square peg (w10) fit? " + hole.fits(largeAdapter)); // false
    }
}
```

### Output:
```
Does round peg (r5) fit? true
Does square peg (w2) fit? true
Does square peg (w10) fit? false
```

### Explanation:
- **RoundPeg(5)**: Radius = 5, fits in hole of radius 5 ✓
- **SquarePeg(2)**: Converted radius = (2 * √2) / 2 ≈ 1.41, fits in hole of radius 5 ✓
- **SquarePeg(10)**: Converted radius = (10 * √2) / 2 ≈ 7.07, does NOT fit in hole of radius 5 ✗

---

## Key Characteristics

| Aspect | Details |
|--------|---------|
| **Pattern Type** | Structural |
| **Intent** | Convert interface of a class to another interface clients expect |
| **Participants** | Target, Adapter, Adaptee, Client |
| **Interface Compatibility** | Makes incompatible interfaces compatible |
| **Code Modification** | No changes to existing classes |

---

## Types of Adapters

### 1. **Class Adapter (Inheritance)**
```java
// Uses inheritance - extends the target interface
class SquarePegAdapter extends RoundPeg {
    private SquarePeg peg;
    // ... implementation
}
```
**Pros:** Direct method overriding  
**Cons:** Uses inheritance (less flexible)

### 2. **Object Adapter (Composition)**
```java
// Uses composition - implements the target interface
class SquarePegAdapter implements RoundPeg {
    private SquarePeg peg;
    
    public double getRadius() {
        return (peg.getWidth() * Math.sqrt(2)) / 2;
    }
}
```
**Pros:** More flexible, follows composition over inheritance  
**Cons:** More boilerplate code

---

## Advantages

✅ **Interface Compatibility** - Makes incompatible interfaces work together  
✅ **No Source Modification** - Don't need to modify existing classes  
✅ **Reusability** - Adapt legacy code without rewriting it  
✅ **Single Responsibility** - Adapter handles the conversion logic  
✅ **Open/Closed Principle** - Open for extension, closed for modification  
✅ **Loose Coupling** - Adapters reduce dependencies between components  

---

## Disadvantages

❌ **Complexity** - Adds extra classes and layers of indirection  
❌ **Performance** - Additional method calls reduce performance  
❌ **Hidden Conversions** - Logic is hidden inside the adapter  
❌ **Limited Control** - Cannot change the adaptee's behavior completely  

---

## Real-World Applications

- **JDBC** - Adapters for different database systems (MySQL, PostgreSQL, Oracle)
- **Collections Framework** - `Collections.enumeration()` adapts Iterator to Enumeration
- **Streams** - `InputStreamReader` adapts `InputStream` to `Reader`
- **Logging** - SLF4J adapts different logging implementations
- **Web Frameworks** - Adapters for different ORM frameworks
- **Reverse Engineering** - Wrapping legacy libraries with modern interfaces
- **API Clients** - Adapting different REST APIs to a common interface
- **Payment Gateways** - Adapters for different payment processors

---

## Comparison with Other Patterns

| Pattern | Purpose | When to Use |
|---------|---------|------------|
| **Adapter** | Convert interfaces | When incompatible interfaces need to work together |
| **Facade** | Simplify access | When hiding complexity of subsystems |
| **Proxy** | Control access | When controlling or intercepting access |
| **Decorator** | Add functionality | When adding behavior dynamically |
| **Bridge** | Separate abstraction and implementation | When decoupling abstraction from implementation |

---

## When to Use Adapter Pattern

Use the Adapter Pattern when:

1. **Interface Mismatch** - You have existing classes with incompatible interfaces
2. **Third-Party Libraries** - Integrating third-party code that doesn't match your interfaces
3. **Legacy Code** - Wrapping old code to work with new systems
4. **Multiple Implementations** - Supporting multiple implementations with different interfaces
5. **API Evolution** - Maintaining backward compatibility while evolving APIs
6. **Dependency Injection** - Adapting classes for dependency injection frameworks

---

## Implementation Variations

### Using Multiple Adapters

```java
// Adapter for different types
class SquarePegAdapter extends RoundPeg { ... }
class TrianglePegAdapter extends RoundPeg { ... }
class HexagonPegAdapter extends RoundPeg { ... }

// All can now work with RoundHole
RoundHole hole = new RoundHole(5);
hole.fits(new SquarePegAdapter(squarePeg));
hole.fits(new TrianglePegAdapter(trianglePeg));
hole.fits(new HexagonPegAdapter(hexagonPeg));
```

### Bidirectional Adapter

```java
// Adapts in both directions
class RoundToSquareAdapter implements SquarePeg {
    private RoundPeg roundPeg;
    
    @Override
    public double getWidth() {
        return (roundPeg.getRadius() * 2) / Math.sqrt(2);
    }
}
```

---

## Best Practices

1. **Keep It Simple** - Only do the necessary conversion
2. **Document Conversions** - Clearly explain any calculations or transformations
3. **Handle Edge Cases** - Consider null values, negative numbers, etc.
4. **Use Composition Over Inheritance** - Prefer object adapters when possible
5. **Name Clearly** - Use names that indicate what the adapter does
6. **Test Thoroughly** - Test with various inputs to ensure correct conversion
7. **Consider Performance** - Minimize overhead in frequently called adapters

---

## Related Patterns

- **Bridge** - Separates abstraction from implementation (similar structure, different purpose)
- **Decorator** - Adds behavior, while adapter changes interface
- **Facade** - Simplifies interfaces, while adapter makes incompatible ones compatible
- **Proxy** - Provides a placeholder, while adapter changes the interface


