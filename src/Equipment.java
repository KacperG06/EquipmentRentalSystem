public abstract class Equipment {
    private int id;
    private String name;
    private double price;
    private boolean rentalStatus;

    public Equipment(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rentalStatus = false; // false ponieważ nowy sprzęt jest na początku nie wypożyczony
    }

    public void setRented() {
        this.rentalStatus = true;
    }

    public void setAvailable() {
        this.rentalStatus = false;
    }

    public boolean isAvailable() {
        if (this.rentalStatus == true) {
            return false;
        }
        else {
            return true;
        }
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }
}
