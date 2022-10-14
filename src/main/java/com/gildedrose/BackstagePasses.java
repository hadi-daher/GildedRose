package com.gildedrose;

public class BackstagePasses extends InventoryItem {
    public static final String NAME = "Backstage passes to a TAFKAL80ETC concert";

    public BackstagePasses(Item item) {
        super(item);
    }

    @Override
    protected void checkQuality() {
        if (item.quality < 50) {
            increaseQuality();

            if (item.sellIn < 11) {
                increaseQuality();
            }
            if (item.sellIn < 6) {
                increaseQuality();
            }
        }
    }

    @Override
    protected void handleExpired() {
        item.quality = 0;
    }


}
