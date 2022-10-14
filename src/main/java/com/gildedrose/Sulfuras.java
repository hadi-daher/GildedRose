package com.gildedrose;

public class Sulfuras extends InventoryItem {
    public static final String NAME = "Sulfuras, Hand of Ragnaros";

    public Sulfuras(Item item) {
        super(item);
    }

    @Override
    protected void checkQuality() {
        return;
    }

    @Override
    protected void handleExpired() {
        return;
    }

    @Override
    protected void updateSellIn() {
        return;
    }

}
