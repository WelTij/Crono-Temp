package com.example.tvwearos.placeholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<ParcialholderItem> ITEMS = new ArrayList<ParcialholderItem>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<Integer, ParcialholderItem> ITEM_MAP = new HashMap<Integer, ParcialholderItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createParcialholderItem(i));
        }
    }

    private static void addItem(ParcialholderItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.indice, item);
    }

    private static ParcialholderItem createParcialholderItem(int position) {
        return new ParcialholderItem(position, "Item " + position);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class ParcialholderItem {
        public final int indice;
        public final String parcial;


        public ParcialholderItem(int indice, String parcial) {
            this.indice = indice;
            this.parcial = parcial;

        }
    }
}