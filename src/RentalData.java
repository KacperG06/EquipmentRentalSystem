import java.util.ArrayList;
import java.util.List;

public class RentalData implements DataManager<Rental>{
    private String file;

    public RentalData(String file){
        this.file = file;
    }

    @Override
    public void save(List<Rental> rentals) {
        System.out.println("Zapisano wypozyczenia: "+file);

    }

    @Override
    public List<Rental> load() {
        System.out.println("Wczytywanie wypożyczen: "+file);
        return new ArrayList<>();
    }
}
