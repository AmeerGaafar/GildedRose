class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemWrapper wrapper = ItemWrapper.wrap(item);
            wrapper.updateSingleItemQuality();

            wrapper.updateSellIn();

            if (item.sellIn < 0) {
                wrapper.updateExpiredItem();
            }
        }
    }

}

class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}

///////
public class ItemWrapper {

    protected final Item item;

    public ItemWrapper(Item item) {
        this.item = item;
    }

    public static ItemWrapper wrap(Item item) {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) return new SulfurasWrapper(item);
        else if (item.name.equals("Aged Brie")) return new BrieWrapper(item);
        else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) return new TicketWrapper(item);
        return new ItemWrapper(item);
    }

    void updateSellIn() {
        item.sellIn = item.sellIn - 1;
    }

    void updateExpiredItem() {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    void updateSingleItemQuality() {
            if (item.quality > 0) {
                item.quality = item.quality - 1;
            }
    }

    protected void incrementQuality() {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}
//////////
public class TicketWrapper extends ItemWrapper {
    public TicketWrapper(Item item) {
        super(item);
    }

    void updateExpiredItem() {
        item.quality = 0;
    }

    void updateSingleItemQuality() {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
            if (item.sellIn < 11) {
                incrementQuality();
            }
            if (item.sellIn < 6) {
                incrementQuality();
            }
        }
    }

}
////////
public class BrieWrapper extends ItemWrapper {
    public BrieWrapper(Item item) {
        super(item);
    }

    void updateExpiredItem() {
        incrementQuality();
    }
    void updateSingleItemQuality() {
        incrementQuality();
    }
}
////////
public class SulfurasWrapper extends ItemWrapper {
    public SulfurasWrapper(Item item) {
        super(item);
    }

    void updateSellIn() {
        return;
    }

    void updateExpiredItem() {
        return;
    }

    void updateSingleItemQuality() {

        if (item.quality > 0) {
            return;
        }
    }
}
//////

