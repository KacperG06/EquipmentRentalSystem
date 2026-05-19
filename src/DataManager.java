import java.util.List;

public interface DataManager<T> {
    void save(List <T> list);
    List<T> load();
}
