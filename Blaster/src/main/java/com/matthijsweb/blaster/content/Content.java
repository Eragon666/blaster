package com.matthijsweb.blaster.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
public class Content {

    /**
     * An array of menu items.
     */
    public static List<MenuItem> ITEMS = new ArrayList<MenuItem>();

    public static Map<String, MenuItem> ITEM_MAP = new HashMap<String, MenuItem>();

    static {
        addItem(new MenuItem("1", "TV Gids"));
        addItem(new MenuItem("2", "Afstandsbediening"));
    }

    private static void addItem(MenuItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class MenuItem {
        public String id;
        public String content;

        public MenuItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
