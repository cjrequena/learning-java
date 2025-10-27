package com.cjrequena.sample.misc;

/**
 * Demonstrates memory allocation in Java's JVM.
 * <p>
 * This program illustrates how the Java Virtual Machine (JVM) manages memory:
 * <ul>
 * <li><b>Method Area (Metaspace)</b>: stores class metadata, static variables, and constants.</li>
 * <li><b>Heap</b>: stores all object instances and instance variables.</li>
 * <li><b>Stack</b>: stores local variables, method parameters, and method call frames.</li>
 * <li><b>String Pool</b>: special area in heap for interned String literals.</li>
 * <li><b>Native Method Stack</b>: for native (non-Java) code execution.</li>
 * </ul>
 * <p>
 * Unlike C, Java provides automatic memory management through garbage collection,
 * so there's no manual malloc/free. However, understanding where objects are
 * allocated is crucial for performance optimization and avoiding memory leaks.
 *
 * @author cjrequena
 */
public class MemoryAllocation {

    /**
     * Static variable stored in the <b>Method Area (Metaspace)</b>.
     * Static variables belong to the class, not to instances.
     */
    private static int staticVariable = 100;

    /**
     * Static final constant stored in the <b>Method Area</b>.
     * Constants are often inlined by the compiler for primitives.
     */
    private static final String CONSTANT_STRING = "I'm a constant";

    /**
     * Instance variable stored in the <b>Heap</b> along with the object.
     * Each instance of MemoryAllocation has its own copy.
     */
    private int instanceVariable = 200;

    /**
     * Another instance variable to demonstrate heap allocation.
     */
    private String instanceString;

    /**
     * Constructs a new MemoryAllocation instance.
     * The 'this' reference is passed via stack.
     *
     * @param str the string to store in instanceString
     */
    public MemoryAllocation(String str) {
        this.instanceString = str;
    }

    /**
     * Demonstrates stack memory allocation.
     * <p>
     * Local variables (primitives and object references) are stored on the stack.
     * The actual objects they reference are stored in the heap.
     *
     * @param param method parameter (stored on stack)
     */
    public void demonstrateStack(int param) {
        // Local primitive variable - stored on STACK
        int localPrimitive = 300;

        // Local reference variable - reference is on STACK, object is in HEAP
        String localString = "Local String";

        // Local object reference - reference on STACK, object in HEAP
        StringBuilder localObject = new StringBuilder("Builder");

        System.out.println("\n=== STACK MEMORY ===");
        System.out.println("Parameter value: " + param);
        System.out.println("Local primitive: " + localPrimitive);
        System.out.println("Local String reference points to heap: \"" + localString + "\"");
        System.out.println("Local object reference points to heap: \"" + localObject + "\"");
        System.out.println("\nNote: The variables themselves are on the stack,");
        System.out.println("but String and StringBuilder objects are in the heap.");
    }

    /**
     * Demonstrates heap memory allocation for objects.
     * All objects in Java are allocated in the heap.
     */
    public void demonstrateHeap() {
        // All objects are allocated in the HEAP
        Object obj1 = new Object();
        Integer boxedInt = Integer.valueOf(400); // Integer object in heap
        int[] array = new int[5]; // Arrays are objects, stored in heap

        System.out.println("\n=== HEAP MEMORY ===");
        System.out.println("Object instance: " + obj1.getClass().getName() + "@" +
          Integer.toHexString(System.identityHashCode(obj1)));
        System.out.println("Boxed Integer: " + boxedInt + " @" +
          Integer.toHexString(System.identityHashCode(boxedInt)));
        System.out.println("Array object: " + array.getClass().getName() + "@" +
          Integer.toHexString(System.identityHashCode(array)));
        System.out.println("\nAll objects, arrays, and instance variables reside in heap memory.");
    }

    /**
     * Demonstrates String Pool (part of heap in Java 7+).
     * <p>
     * String literals are automatically interned in the string pool,
     * allowing multiple references to share the same String object.
     */
    public void demonstrateStringPool() {
        // String literals are stored in the String Pool (special heap area)
        String literal1 = "Hello";
        String literal2 = "Hello"; // Same reference as literal1

        // new String() creates a new object in heap (outside string pool)
        String heapString = new String("Hello");

        System.out.println("\n=== STRING POOL ===");
        System.out.println("literal1 == literal2: " + (literal1 == literal2)); // true
        System.out.println("literal1 == heapString: " + (literal1 == heapString)); // false
        System.out.println("literal1.equals(heapString): " + literal1.equals(heapString)); // true

        System.out.println("\nString literals share the same reference from the pool.");
        System.out.println("new String() creates a separate object in the heap.");
    }

    /**
     * Demonstrates method area (metaspace) storage.
     * <p>
     * The Method Area stores class-level information including:
     * static variables, method bytecode, and runtime constant pool.
     */
    public static void demonstrateMethodArea() {
        System.out.println("\n=== METHOD AREA (METASPACE) ===");
        System.out.println("Static variable value: " + staticVariable);
        System.out.println("Static constant: " + CONSTANT_STRING);
        System.out.println("\nClass metadata: " + MemoryAllocation.class.getName());
        System.out.println("Method information, static variables, and constants");
        System.out.println("are stored in the Method Area (Metaspace in Java 8+).");
    }

    /**
     * Demonstrates memory allocation with nested method calls.
     * Shows how stack frames are created and destroyed.
     * <p>
     * Each recursive call creates a new stack frame containing
     * local variables and method parameters. When the method returns,
     * the stack frame is popped off automatically.
     *
     * @param depth the recursion depth (countdown to zero)
     */
    public void demonstrateStackFrames(int depth) {
        int localVar = depth * 10;

        if (depth > 0) {
            System.out.println("Stack frame depth: " + depth + ", local var: " + localVar);
            demonstrateStackFrames(depth - 1); // Recursive call creates new stack frame
        } else {
            System.out.println("Reached bottom of stack frames");
        }

        // When method returns, stack frame is popped off
    }

    /**
     * Main method - entry point of the program.
     * <p>
     * Demonstrates all aspects of Java memory allocation including
     * the method area, heap, stack, and string pool.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("  JAVA MEMORY ALLOCATION DEMO");
        System.out.println("====================================");

        // Demonstrate Method Area
        demonstrateMethodArea();

        // Create an instance - object allocated in HEAP
        MemoryAllocation demo = new MemoryAllocation("Instance created");

        System.out.println("\n=== INSTANCE INFORMATION ===");
        System.out.println("Object identity hash: " +
          Integer.toHexString(System.identityHashCode(demo)));
        System.out.println("Instance variable: " + demo.instanceVariable);
        System.out.println("Instance string: " + demo.instanceString);

        // Demonstrate Stack
        demo.demonstrateStack(500);

        // Demonstrate Heap
        demo.demonstrateHeap();

        // Demonstrate String Pool
        demo.demonstrateStringPool();

        // Demonstrate Stack Frames
        System.out.println("\n=== STACK FRAMES (Recursive Calls) ===");
        demo.demonstrateStackFrames(3);

        // Memory summary
        System.out.println("\n====================================");
        System.out.println("  MEMORY SUMMARY");
        System.out.println("====================================");
        System.out.println("1. METHOD AREA: Class metadata, static variables, constants");
        System.out.println("2. HEAP: All objects, instance variables, arrays");
        System.out.println("3. STACK: Local variables, method parameters, method calls");
        System.out.println("4. STRING POOL: Interned string literals (part of heap)");
        System.out.println("\nJava's garbage collector automatically manages heap memory.");
        System.out.println("Stack memory is automatically managed by method calls/returns.");

        // Runtime memory information
        Runtime runtime = Runtime.getRuntime();
        System.out.println("\n=== JVM MEMORY STATISTICS ===");
        System.out.println("Total Memory: " + runtime.totalMemory() / 1024 + " KB");
        System.out.println("Free Memory: " + runtime.freeMemory() / 1024 + " KB");
        System.out.println("Used Memory: " + (runtime.totalMemory() - runtime.freeMemory()) / 1024 + " KB");
        System.out.println("Max Memory: " + runtime.maxMemory() / 1024 + " KB");
    }
}

/*
 * ADDITIONAL NOTES:
 *
 * Key Differences from C:
 * ----------------------
 * 1. NO MANUAL MEMORY MANAGEMENT: Java has automatic garbage collection.
 *    No malloc(), free(), or pointer arithmetic.
 *
 * 2. EVERYTHING IS REFERENCES: Except primitives (int, double, etc.),
 *    everything in Java is accessed via references, not pointers.
 *
 * 3. MEMORY STRUCTURE:
 *    - C: Text, Data, BSS, Heap, Stack
 *    - Java: Method Area, Heap, Stack, PC Registers, Native Stack
 *
 * 4. STACK vs HEAP:
 *    - Stack: Method frames, local variables (primitives and references)
 *    - Heap: All objects, arrays, instance variables
 *
 * 5. METHOD AREA (Metaspace in Java 8+):
 *    Replaces C's Data/BSS segments. Stores class structures.
 *
 * Memory Leaks in Java:
 * --------------------
 * While Java has GC, memory leaks can still occur:
 * - Unclosed resources (files, connections)
 * - Static collections that grow indefinitely
 * - Listeners not unregistered
 * - ThreadLocal variables not cleaned
 * - Inner class references keeping outer class alive
 *
 * To run and see output:
 * javac MemoryAllocation.java
 * java MemoryAllocation
 *
 * To see more detailed memory info, run with:
 * java -verbose:gc MemoryAllocation
 */
