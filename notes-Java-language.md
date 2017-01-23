# Notes

## Class & Object

* Why make  `.class`  at all?

1.  `.class` file has been type-checked.

2.  `.class` files are _simpler_ for machine to execute.

    ​

* A `.class` without a `main` method cannot be run directly, but can be run by *test driver* `class` which contains `main` method.



* `static` vs non-static methods:

  1.  A method that is declared `static` is called a **class method**. (associated with a class)

  2.  A method that is not declared `static` is called an **instance method**.

      (associated with an instance)

      ​


* ```java
  objectName.member
  ```

  _dot operator_ `.` is _belonging_ operator, `ObjectName.member`, **member** is instance variable or method.

### How to use keyword `this `?

### How to use class variables???????

### How to use keyword `final`?

*   Java doesn't allow access to uninitialized variables.



*   **Declaration** of an object reference variable:

    ```java
    ClassName objectName;
    ```

    The new object *points to* `null`. 

    *Intuition for a class reference variable declaration: an all-zero box is created (the actual size is 64 bits, which doesn't matter).* 

*   **Instantiation** of an object:

    ```java
    new Constructor();
    ```

*   **Assignment**, here, link the reference variable to the new object.

    ```java
    ClassName objectName = new Constructor();
    ```



*   Objects are either instances of classes or arrays.



## Java Code Convention

## ![Screen Shot 2017-01-23 at 21.53.21](/Users/pwd/Desktop/Screen Shot 2017-01-23 at 21.53.21.png)

## Inheritance





## Interface

An interface is like a **contract** and an **empty shell ** between the class and outside world, enforced at build time by the compiler. That is, to implement an interface, all methods defined by interface must appear in source code before the class will successfully compile. Commonly, an interface is a group of related methods with empty bodies (**method stubs**).

*My intuition: a guy writes the interface, where there are only some patterns, and you need to fill the blanks in the given way … to 'implement' the contract.*

e.g., a bicycle's behavior specified as an interface might be as follows,

```java
interface Bicycle {
  
  	/* wheel revolutions per minute */
  	void changeGear(int newValue);
  
  	void speedUp(int increment);
  
  	void applyBrakes(int decrement);
}
```

To *implement* the interface, change the name of the class (to a particular brand, for example), and use keyword `implements` in class declaration:

```java
public class ACMEBicycle implements Bicycle {
	int speed = 0;
	int gear = 1;
  
  	/** The compiler will now require methods
      * changeGear, speedUp, and applyBrakes all 
      * be implemented. Otherwise compilation would fail. */
  
  	public void changeGear(int newValue) {
    	gear = newValue;
  	}
  
  	public void speedUp(int increment) {
		speed += increment;
  	}
  
 	public void applyBrakes(int decrement) {
		speed -= decrement;
  	}
  
  	/* other optional variables and methods here ... */
}
```

**Note**: To compile the` ACMEBicycle` class, need to add the `public` keyword to the beginning of all the implemented interface methods. That is because in Java, all methods declared inside interfaces are implicitly `public`. And all variables declared in interfaces are implicitly `public static final` (constants).



## Package

A package is a **namespace** that organizes a set of related classes and interfaces by functionality.

*Intuition: similarly to different folders on your computer.*

**API**: Java SE platform class library (a set of packages), short for **Application Programming Interface**.





