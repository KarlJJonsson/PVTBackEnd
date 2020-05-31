package com.group158.UrbanAdventure;

@Deprecated
public class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object object){
        if(object instanceof Item){
            Item item = (Item) object;
            if(this.name.equals(item.name)){
                return true;
            }
        }
        return false;
    }
}