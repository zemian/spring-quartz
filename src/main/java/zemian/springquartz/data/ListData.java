package zemian.springquartz.data;

import java.util.List;

public class ListData<T> {
    List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
