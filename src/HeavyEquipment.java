public class HeavyEquipment extends Equipment{
    private boolean driverLicense;
    private String licensePlate;

    public HeavyEquipment(int id, String name, double price, boolean driverLicense, String licensePlate) {
        super(id, name, price);
        this.driverLicense = driverLicense;
        this.licensePlate = licensePlate;
    }

    public boolean hasDriverLicense() {
        if (this.driverLicense == true) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }
}
