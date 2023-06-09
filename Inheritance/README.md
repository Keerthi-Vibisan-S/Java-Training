Inheritance
-----------
In java we achieve inheritance using <b>extends</b> keyword.

nheritance is a mechanism of driving a new class from an existing class. The existing (old) class is known as base class or super class or parent class. The new class is known as a derived class or sub class or child class. It allows us to use the properties and behavior of one class (parent) in another class (child).
Eg:
Calculator Class - Already have basic operations a calculator can perform
Scientific Calculator class -  Here we need to type basic operation that a calculator performs, but it is already available in Calculator Class and here we just extend it and add additional or new function.

<b>Instead can we just create an object for the Calculator class and access it.</b>
Yes, in java inheritance can be replaced by object's. But the difference is

With inheritance we get advantage's like:
1. Well defined relationship between class. [Structured code]
2. Polymorphism
3. Override (If we need to do a special implementation for a function in parent class)
4. With abstract class implementation we can make child class to implements abstract methods if there is a need.

With object's we dont get any of these.
