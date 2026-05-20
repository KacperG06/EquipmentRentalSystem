import java.time.LocalDateTime;

public class Rental {
    private int id;
    private User user;
    private Equipment equipment;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double totalCost;

    public Rental(int id, User user, Equipment equipment, LocalDateTime startDate, LocalDateTime endDate, double totalCost){
        this.id = id;
        this.user = user;
        this.equipment = equipment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
