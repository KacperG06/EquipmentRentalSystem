import java.util.ArrayList;
import java.util.List;

public class UserData implements DataManager<User> {
    private String file;

    public UserData(String file){
        this.file = file;
    }

    @Override
    public void save(List<User> users) {
        System.out.println("Zapisano dane uzytkownikow: "+file);

    }

    @Override
    public List<User> load() {
        System.out.println("Wczytywanie danych uzytkownikow: "+file);
        return new ArrayList<>();
    }
}
