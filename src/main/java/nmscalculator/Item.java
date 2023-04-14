package nmscalculator;

import java.util.Map;

public class Item {
    private String name;
    private ItemType type;
    private Map<Item, Integer> components;
    private int sellPrice;

    public Item (String name, ItemType type, Map<Item, Integer> components, int sellPrice) {
        this.name = name;
        this.type = type;
        this.components = components;
        this.sellPrice = sellPrice;
    }

}
