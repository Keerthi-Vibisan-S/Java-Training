Encapsulation
-------------
It is the process of wrapping up of Data and Functions within the class as single unit, so then can be acceessed as a whole.
Encapsulation = Data Hiding+Abstraction

Here we almost declarre variables as private and access them via functions like getters and setters.
We might have a question like here we are declaring the variables in private and here we itself give access to them via functions like getters and setters why (Instead we could directly set them using object.dataMemeber) ?

There is no technical reason behind this, these are some standards in java. Like here our class is like our home and data members are like things within our home, so they should not be directly accessed like using object.dataMember. Their access should be through a window like getters and setter functions. 
Here also we have advantages like to do validation if needed within the setter or getter function before we set them to the data member. 

POJO class can be said as an example of encapsulation.
----------
POJO class, plain old java object class these are mainly used for DTO (Data Transfer Object's).
In ORM concepts these are used to represent Entities (Tables).