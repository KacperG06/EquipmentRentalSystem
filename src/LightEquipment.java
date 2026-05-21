public class LightEquipment extends Equipment {
    private boolean transportRequired;

    public LightEquipment(int id, String name, double price, boolean transportRequired) {
        super(id, name, price);
        this.transportRequired = transportRequired;
    }

    public boolean isTransportRequired() {
        if (this.transportRequired == true) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean canBeRentedBy(User user) {
        return true;
    }
}
