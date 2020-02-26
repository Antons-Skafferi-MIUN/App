package se.miun.dt170.antonsskafferi.data;

import java.util.List;

public class Item {
    String Name;
    List<String> extras;

    public Item(String name, List<String> extras) {
        Name = name;
        this.extras = extras;
    }
}
