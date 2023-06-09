package Inheritance.hierarchical;

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
        new Mobiles("Samsung Galaxy A21s", 16000, "Android 13", "Back: 64px, Front: 16px", 6, 64);
        new Mobiles("Samsung M11", 13000, "Android 12", "Back: 48px, Front: 8px", 4, 32);
        new Mobiles("Samsung J7", 8000, "Android 11", "Back: 32px, Front: 8px", 4, 32);
    }
}
