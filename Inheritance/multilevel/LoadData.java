package Inheritance.multilevel;


public class LoadData {
    void LoadMobiles() {
        new Mobiles("Samsung Galaxy A21s", 16000, "Mobile", "Samsung", "Android 13", 6, 64, "Back: 64px, Front: 16px");
        new Mobiles("Samsung M11", 13000, "Mobile", "Samsung", "Android 12", 4, 32, "Back: 48px, Front: 8px");
        new Mobiles("Samsung J7", 8000, "Mobile", "Samsung", "Android 11", 4, 32, "Back: 32px, Front: 8px");
    }

    void LoadLaptop() {
        new Laptop("MacBook Pro", 129999, "Laptop", "Apple", "M2", "8", "256 SSD", "NA");
        new Laptop("Pavilion DV", 67000, "Laptop", "Hp", "intel i5", "16", "512 SSD", "Intel iris XE");
    }
}
