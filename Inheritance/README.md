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

In Inheritance the if we are gonna Override a function the functionality or the way of implementation should change.

1. Like here in above code we cannot have Override methods like getProduct() or getDescription(), here we don't should see any purpose.

The Overriding method's should be like:
1. addProduct( )
2. filterProduct( )

Here the implementation changes as we have different data members and each class, adding product and filtering are based upon that.

More Important:
---------------
1. Data Members doesn't matter during inheritance. Because we are only going to get it and store it, so we can do like constructor overloading or some overloading to make it happen, so what we need to understand here is for Data Members alone we should not go for a child class during inheritance.
1. In inheritance a child class without some child specific function is useless.

Because with no child specific function we will just have data members with getters and setters which is useless during inheritance as per our point 1, because they can be optimized in a single class itself.

So finally during inheritance we extend parent class functions and also add our child specific functions, those scenarios are the exact need for the inheritance.

Remember: If logic is same normally we should no go for seperate function or overriding, we must do it with same function.

Example: Flipkart have filter function and here do you think they would have 100 different filter function by Overriding or like seperate functions. No, they have only one function that suits all the class or products.


Example UseCase:
--------

    ChatBot (Parent or Super)
        1. initiateChat()
        2. getResponse()
        3. saveChatHistory()
    
    [ Child or Subclasses ]
    SuperChatBot
        1. createSupportTicket()
        2. processTicket()
        3. reviewTicket()
    
    SalesChatBot
        1. suggestProducts()
        2. processTransaction()
        3. saveSalesHistory()

Here we can see that all chat bot can extendsbasic functionality for a bot from the parent class and they include their child specific functions.
    