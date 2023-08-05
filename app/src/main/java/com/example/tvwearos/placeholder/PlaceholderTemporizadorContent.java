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
public class PlaceholderTemporizadorContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<TemporizadorholderItem> ITEMS = new ArrayList<TemporizadorholderItem>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<Integer, TemporizadorholderItem> ITEM_MAP = new HashMap<Integer, TemporizadorholderItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createTemporizadorholderItem(i));
        }
    }

    private static void addItem(TemporizadorholderItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.indice, item);
    }

    private static TemporizadorholderItem createTemporizadorholderItem(int position) {
        return new TemporizadorholderItem(position, "Item " + position);
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
    public static class TemporizadorholderItem {
        public final int indice;
        public final String temporizador;


        public TemporizadorholderItem(int indice, String temporizador) {
            this.indice = indice;
            this.temporizador = temporizador;

        }
    }
}