package com.gildedrose;

public class InventoryItem {
    protected Item item;

    public static InventoryItem create(Item item) {
        if(item.name.equals(AgedBrie.NAME))
            return new AgedBrie(item);

        if(item.name.equals(BackstagePasses.NAME))
            return new BackstagePasses(item);

        if(item.name.equals(Sulfuras.NAME))
            return new Sulfuras(item);

        if(item.name.equals(Conjured.NAME))
            return new Conjured(item);

        return new InventoryItem(item);
    }
    public InventoryItem(Item item) {
        this.item = item;
    }

    public void dailyUpdate(){
        checkQuality();
        updateSellIn();
    }
    protected void checkQuality() {
        decreaseQuality();
    }

    protected void handleExpired() {
        decreaseQuality();
    }

    protected void handleIfExpired() {
        if (item.sellIn < 0) {
            handleExpired();
        }
    }

    protected void updateSellIn() {
        item.sellIn--;
        handleIfExpired();
    }

    protected void increaseQuality() {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    protected void decreaseQuality() {
        if (item.quality > 0) {
            item.quality--;
        }
    }
}
