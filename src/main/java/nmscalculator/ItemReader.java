package nmscalculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ItemReader {
    private static Map<String, Item> items = new HashMap<>();

    public void readItems () {

        try (FileReader fReader = new FileReader("data/items.csv");
        BufferedReader reader = new BufferedReader(fReader)) {
            reader.readLine();
            String nextLine = reader.readLine();
            while (nextLine != null){
                String[] tokens = nextLine.split(",");
                String name = tokens[2];
                int price = 0;
                if(tokens[0] != "") {
                    price = Integer.parseInt(tokens[0]);
                }
                ItemType type = null;
                switch (tokens[1]) {
                    case "R" :
                        type = ItemType.REFINED;
                        break;
                    case "M" :
                        type = ItemType.MINERAL;
                        break;
                    case "C" :
                        type = ItemType.CROP;
                        break;
                    case "G" :
                        type = ItemType.GAS;
                        break;
                    default :
                        type = ItemType.CRAFTED;
                        break;
                }
                items.put(name, new Item(name, type, new HashMap<Item, Integer>(), price));
                
            }


        } catch (Exception e) {}
    }

    public void populateComponents() {
        try (FileReader fReader = new FileReader("data/items.csv");
        BufferedReader reader = new BufferedReader(fReader)) {
            reader.readLine();
            String nextLine = reader.readLine();
            while (nextLine != null){
                String[] tokens = nextLine.split(",");
                String mainItemName = tokens[2];
                for (int i = 3; i < tokens.length; i++) {
                    String componentString = tokens[i];
                    if (componentString == "") {
                        break;
                    }
                    int count = Integer.parseInt(componentString.substring(0,3));
                    String name = componentString.substring(3);
                    items.get(mainItemName).getComponents().put(items.get(name), count);
                }
            }
        } catch (Exception e) {}
    }
}
