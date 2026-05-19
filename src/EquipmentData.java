import java.util.ArrayList;
import java.util.List;

public class EquipmentData implements DataManager<Equipment>{
    private String file;

    public EquipmentData(String file){
        this.file = file;
    }

    @Override
    public void save(List<Equipment> equipment) {
        System.out.println("Zapisano liste sprzętu: "+file);

    }

    @Override
    public List<Equipment> load() {
        System.out.println("Wczytywanie listy sprzętu "+file);
        return new ArrayList<>();
    }
}
