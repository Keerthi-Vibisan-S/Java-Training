Normally we go for interface when functions or methods with same name will have 100% different code implementation in most Scenario's.

Interfaces - These are like contracts. When a class implements it, it must implement all its abstract methods.
By default data members in Interface are by static and final.

until Java V8 Interfaces just contain only abstract methods.
After Java V8
1. Static methods - Can be accessed as Interface.MethodName and this cannot be Overriden.
2. default - It is not necessary to implement a default method as, this method can have default implementation. and this can be Overriden.
methods were introduced. 
These methods were mainly introduced for 
1. This allowed existing interfaces to evolve and add new methods without requiring the implementing classes to implement them. Default methods provide a way to add new functionality to interfaces without breaking the existing code that implements those interfaces.

2. Static methods in interfaces were also introduced in Java 8 to provide utility methods that can be directly accessed through the interface without the need for an instance of the implementing class. Static methods in interfaces are typically used for helper methods or utility functions that are related to the functionality defined by the interface.

Types of Interface:
------------------
1. Functional Interface - This interface consist of exactly 1 Abstract method and can consist of n number of static and default methods. Lambda function made these functional interface implementation better and easier.

2. Marker Interface - A marker interface, also known as a tagging interface, is an interface in Java that does not declare any methods or fields. It is simply used as a marker or tag to indicate that a class implements a certain behavior or possesses a certain capability.

Serializable a marker interface when a class implements it, then the class can be serialized. Java checks it by using InstanceOf() -> used to check if the specified object is an instance of a class, subclass, or interface.
