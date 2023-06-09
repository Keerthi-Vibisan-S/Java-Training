package Inheritance.hybrid;

public class LoadData {
    void LoadGrocery() {
        new Grocery("Sun Flower Oil", 112, "Staples", "10 june 2023", "15 Nov 2023");
        new Grocery("Cinthol", 159, "Personal", "01 Feb 2023", "20 Jan 2025");
        new Grocery("Harpic Power Plus", 522, "Cleaners", "01 Feb 2023", "20 Jan 2025");
    }

    void LoadFashion() {
        new Fashion("Formal Shirt", 400, "White", "L", "mens shirt");
        new Fashion("T-Shirt", 300, "Purple", "S, M , L, XL", "mens t-shirt");
        new Fashion("Jeans", 600, "White", "M, L", "Jeans");
    }

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
