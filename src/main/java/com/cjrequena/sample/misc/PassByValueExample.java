package com.cjrequena.sample.misc;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 * <h1>Pass by Value vs Pass by Reference in Java</h1>
 *
 * <h2>The Core Concept: Java is ALWAYS Pass by Value</h2>
 * <p>
 * This is crucial to understand: <strong>Java is strictly pass-by-value</strong>. However, the confusion arises because of how Java handles different types of data.
 * </p>
 *
 * <h3>Primitive Types (Pass by Value)</h3>
 * <p>
 * When you pass primitive types ({@code int}, {@code double}, {@code boolean}, {@code char}, etc.), Java copies the actual value.
 * Changes to the parameter inside the method don't affect the original variable.
 * </p>
 *
 * <h3>Reference Types (Pass by Value of the Reference)</h3>
 * <p>
 * When you pass objects, Java copies the <strong>reference</strong> (memory address), not the object itself.
 * Both the original and the parameter point to the same object in memory, so modifications to the object's contents are visible,
 * but reassigning the parameter doesn't affect the original reference.
 * </p>
 *
 * <h2>Memory: Heap vs Stack</h2>
 *
 * <h3>Stack Memory</h3>
 * <ul>
 *   <li><strong>Stores:</strong> Local variables, method calls, and references to objects</li>
 *   <li><strong>Characteristics:</strong>
 *     <ul>
 *       <li>Fast access (LIFO - Last In, First Out)</li>
 *       <li>Limited size</li>
 *       <li>Automatically managed (variables removed when method ends)</li>
 *       <li>Thread-specific (each thread has its own stack)</li>
 *       <li>Stores primitive values directly</li>
 *       <li>Stores references (addresses) to objects</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <h3>Heap Memory</h3>
 * <ul>
 *   <li><strong>Stores:</strong> All objects and instance variables</li>
 *   <li><strong>Characteristics:</strong>
 *     <ul>
 *       <li>Slower access than stack</li>
 *       <li>Larger size</li>
 *       <li>Managed by Garbage Collector</li>
 *       <li>Shared among all threads</li>
 *       <li>Objects remain until no references exist</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <h2>Complete Memory State Analysis</h2>
 * <pre>
 * ┌──────────┬─────────────────┬─────────────────────────┬───────────────┬───────────┬──────────────┬────────────────────────┬─────────────────────────────────────────────┐
 * │ Location │ Scope/Method    │ Variable Name           │ Stack Address │ Value     │ Heap Address │ Heap Object State      │ Explanation                                 │
 * ├──────────┼─────────────────┼─────────────────────────┼───────────────┼───────────┼──────────────┼────────────────────────┼─────────────────────────────────────────────┤
 * │ STACK    │ main()          │ primitiveNumber         │ 0x7001        │ 10        │ -            │ -                      │ Original primitive value                    │
 * │ STACK    │ modifyPrimitive │ primitiveNumberArgument │ 0x7010        │ 10 → 20   │ -            │ -                      │ COPY of primitiveNumber; doesn't affect it  │
 * ├──────────┼─────────────────┼─────────────────────────┼───────────────┼───────────┼──────────────┼────────────────────────┼─────────────────────────────────────────────┤
 * │ STACK    │ main()          │ person                  │ 0x7002        │ 0x2000    │ 0x2000       │ name: "Alice" → "Bob"  │ Reference points to heap object             │
 * │ STACK    │ modifyObject()  │ personArgument          │ 0x7020        │ 0x2000    │ 0x2000       │ name: "Alice" → "Bob"  │ COPY of reference, points to SAME object    │
 * │ HEAP     │ -               │ Person object           │ -             │ -         │ 0x2000       │ name: "Bob", age: 25   │ Modified via personArgument.setName("Bob")  │
 * ├──────────┼─────────────────┼─────────────────────────┼───────────────┼───────────┼──────────────┼────────────────────────┼─────────────────────────────────────────────┤
 * │ STACK    │ main()          │ person2                 │ 0x7003        │ 0x2001    │ 0x2001       │ name: "Charlie"        │ Reference remains unchanged after method    │
 * │ STACK    │ reassignObject()│ personArgument          │ 0x7030        │ 0x2001→   │ 0x2002       │ name: "Diana"          │ Local reference reassigned; doesn't affect  │
 * │          │                 │                         │               │ 0x2002    │              │                        │ person2 in main()                           │
 * │ HEAP     │ -               │ Person object           │ -             │ -         │ 0x2001       │ name: "Charlie"        │ Still referenced by person2; unchanged      │
 * │ HEAP     │ -               │ Person object           │ -             │ -         │ 0x2002       │ name: "Diana", age: 35 │ Created in reassignObject(); eligible for GC│
 * └──────────┴─────────────────┴─────────────────────────┴───────────────┴───────────┴──────────────┴────────────────────────┴─────────────────────────────────────────────┘
 * </pre>
 *
 * <h2>Visual Memory Diagram</h2>
 * <pre>
 * SCENARIO 1: Primitive
 * ======================
 * STACK (main)              STACK (modifyPrimitive)
 * ┌─────────────────────┐   ┌──────────────────────────────┐
 * │ primitiveNumber     │   │ primitiveNumberArgument      │
 * │ 0x7001 → [10]       │   │ 0x7010 → [10] → [20]         │
 * └─────────────────────┘   └──────────────────────────────┘
 *     ↑                              ↑
 *     └──────────────────────────────┘
 *          Values are INDEPENDENT
 *
 *
 * SCENARIO 2: Object Modification
 * ================================
 * STACK (main)              STACK (modifyObject)             HEAP
 * ┌─────────────────────┐   ┌──────────────────────┐   ┌─────────────────┐
 * │ person              │   │ personArgument       │   │ 0x2000          │
 * │ 0x7002 → [0x2000]───┼───┼─ 0x7020 → [0x2000]──┼───┤ name: "Bob"     │
 * └─────────────────────┘   └──────────────────────┘   │ age: 25         │
 *                                                       └─────────────────┘
 *          Both references point to SAME object
 *
 *
 * SCENARIO 3: Object Reassignment
 * ================================
 * STACK (main)              STACK (reassignObject)           HEAP
 * ┌─────────────────────┐   ┌──────────────────────────┐   ┌─────────────────┐
 * │ person2             │   │ personArgument           │   │ 0x2001          │
 * │ 0x7003 → [0x2001]───┼───┤ 0x7030 → [0x2001]        │   │ name: "Charlie" │
 * └─────────────────────┘   │          ↓               │   │ age: 30         │
 *                           │        [0x2002]──────────┼───┤─────────────────┤
 *                           └──────────────────────────┘   │ 0x2002          │
 *                                                           │ name: "Diana"   │
 *                                                           │ age: 35         │
 *                                                           └─────────────────┘
 *          person2 unchanged; personArgument reassigned locally
 * </pre>
 *
 * <h2>Key Takeaways</h2>
 * <ol>
 *   <li><strong>Java always passes by value</strong> - but for objects, that value is a copy of the reference (memory address)</li>
 *   <li><strong>Stack</strong> holds method calls, local variables, and references</li>
 *   <li><strong>Heap</strong> holds the actual objects</li>
 *   <li><strong>You can modify object contents</strong> through the reference (both variables see changes)</li>
 *   <li><strong>You cannot change where the original variable points</strong> (reassignment only affects the local parameter)</li>
 * </ol>
 *
 * <h3>Summary Table</h3>
 * <pre>
 * ┌─────────────────────┬─────────────────────────┬──────────────────────┬───────────────────────────────────────────┐
 * │ Scenario            │ What Gets Copied        │ Can Modify Original? │ Why?                                      │
 * ├─────────────────────┼─────────────────────────┼──────────────────────┼───────────────────────────────────────────┤
 * │ Primitive           │ The actual value (10)   │ ❌ No                │ Different memory locations on stack       │
 * │ Object Modification │ The reference (0x2000)  │ ✅ Yes (contents)    │ Both references point to same heap object │
 * │ Object Reassignment │ The reference (0x2001)  │ ❌ No (reference)    │ Assignment only changes local copy        │
 * └─────────────────────┴─────────────────────────┴──────────────────────┴───────────────────────────────────────────┘
 * </pre>
 *
 * <p>
 * <strong>Java's Pass-by-Value Rule:</strong> The method parameter gets a <strong>copy</strong> of whatever is in the caller's stack location.
 * For primitives, that's the value. For objects, that's the reference (memory address).
 * </p>
 *
 * <p>
 * <em>Think of it like this:</em> If you give someone a copy of your house address, they can go to your house and rearrange furniture (modify object),
 * but if they write down a different address on their paper, your paper still has your original address (reassignment doesn't affect original reference).
 * </p>
 *
 * @author Carlos Requena
 * @version 1.0
 */
@Log4j2
public class PassByValueExample {
  public static void main(String[] args) {
    // Primitive example
    int primitiveNumber = 10;
    log.debug("primitiveNumber before calling modifyPrimitive: {}", primitiveNumber); // Still 10
    modifyPrimitive(primitiveNumber);
    log.debug("primitiveNumber after calling modifyPrimitive: {}", primitiveNumber); // Still 10

    // Object example
    Person person = new Person("Alice", 25);
    log.debug("Person before calling modifyObject name: {} :: age: {}", person.getName(), person.getAge());
    modifyObject(person);
    log.debug("Person after calling modifyObject name: {} :: age: {}", person.getName(), person.getAge());

    // Reassignment example
    Person person2 = new Person("Charlie", 30);
    log.debug("Person2 before calling reassignObject name: {} :: age: {}", person2.getName(), person2.getAge());
    reassignObject(person2); // Still "Charlie"
    log.debug("Person2 after calling reassignObject name: {} :: age: {}", person2.getName(), person2.getAge());
  }

  public static void modifyPrimitive(int primitiveNumberArgument) {
    primitiveNumberArgument = 20; // Only changes local copy
  }

  public static void modifyObject(Person personArgument) {
    personArgument.setName("Bob"); // Modifies the actual object
  }

  public static void reassignObject(Person personArgument) {
    personArgument = new Person("Diana", 35); // Only changes local reference
  }
}

@Data
class Person {
  private String name;
  private int age;

  Person(String name, int age) {
    this.name = name;
    this.age = age;
  }
}
