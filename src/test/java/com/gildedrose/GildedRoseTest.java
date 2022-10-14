package com.gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void standardItemQualityDecreasesSellinDecreasesEveryday(){
        int startingSellin = 5;
        int startingQuality = 7;
        final Item standardItem = new Item("Elixir of the Mongoose", startingSellin, startingQuality);
        GildedRose app = new GildedRose(new Item[] {standardItem});

        app.updateQuality();

        assertEquals(standardItem.sellIn, startingSellin-1);
        assertEquals(standardItem.quality, startingQuality - 1);
    }

    @Test
    void multipleItemsDegradeEachDay(){
        Item firstItem = new Item("First Standard Item", 5, 4);
        Item secondItem = new Item("Second Standard Item", 3,2);
        GildedRose app = new GildedRose(new Item[]{firstItem, secondItem});

        app.updateQuality();

        assertEquals(firstItem.sellIn,4);
        assertEquals(firstItem.quality, 3);
        assertEquals(secondItem.sellIn, 2);
        assertEquals(secondItem.quality, 1);
    }
    @Test
    void qualityDegradesTwiceWhenSellDatePassed(){
        final Item item[] = new Item[] { new Item("+5 Dexterity Vest", 0, 5)};
        GildedRose app = new GildedRose(item);
        app.updateQuality();
        assertEquals(3, item[0].quality);
    }
    @Test
    void itemQualityNeverNegative(){
        Item[] item = new Item[] {new Item("Sulfuras", 3, 10)};
        GildedRose app = new GildedRose(item);
        for(int i=0; i<10; i++)
            app.updateQuality();
        assertEquals(0, item[0].quality);
    }

    @Test
    void agedBrieQualityIncreasesWhenGetsOlder(){
        Item[] item = new Item[] {new Item("Aged Brie", 4, 49)};
        GildedRose app = new GildedRose(item);
        app.updateQuality();
        assertEquals(50, item[0].quality);
    }

    @Test
    void qualityCantBeMoreThan50(){
        Item[] item = new Item[] {
            new Item("Aged Brie", 4, 50)
        };
        GildedRose app = new GildedRose(item);
        app.updateQuality();
        assertEquals(50, item[0].quality);

    }
    @Test
    void sulfurasNeverHaveToBeSold(){
        Item item = new Item("Sulfuras, Hand of Ragnaros", -1, 80);
        GildedRose app = new GildedRose(new Item[]{item});

        app.updateQuality();

        assertEquals(-1, item.sellIn);
    }

    @Test
    void sulfurasNeverDecreasesInQuality(){
        Item[] item = new Item[] {
            new Item("Sulfuras, Hand of Ragnaros", 4, 50)
        };
        GildedRose app = new GildedRose(item);
        app.updateQuality();
        assertEquals(50, item[0].quality);
    }

    @Test
    void backStagePassesIncreasesNormallyWhenSellinGreaterThan10(){
        Item item[] = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 11, 45)};
        GildedRose app = new GildedRose(item);

        app.updateQuality();

        assertEquals(46, item[0].quality);
    }
    @Test
    void backStagePassesIncreasesBy2WhenSellInLessThan11(){
        Item[] item = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 10, 40)};
        GildedRose app = new GildedRose(item);
        app.updateQuality();
        assertEquals(42, item[0].quality);
    }

    @Test
    void backStagePassesIncreasesBy3WhenSellInLessThan6(){
        Item[] item = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 4, 40)};
        GildedRose app = new GildedRose(item);
        app.updateQuality();
        assertEquals(43, item[0].quality);
    }
    @Test
    void backStagePassesQualityIs0AfterConcert(){
        Item[] item = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 0, 40)};
        GildedRose app = new GildedRose(item);
        app.updateQuality();
        assertEquals(0, item[0].quality);
    }
    @Test
    void conjuredQualityDegradeTwice(){
        Item[] item = new Item[] {
            new Item("Conjured", 1, 50)
        };
        GildedRose app = new GildedRose(item);
        app.updateQuality();
        assertEquals(48, item[0].quality);
    }

    @Test
    void conjuredQualityDegradesBy4WhenSellInLessThan0(){
        Item[] item = new Item[] {
            new Item("Conjured", -1, 50)
        };
        GildedRose app = new GildedRose(item);
        app.updateQuality();
        assertEquals(46, item[0].quality);
    }
}
