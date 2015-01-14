package com.denovo.p8583;

import java.util.ArrayList;

/**
 * Created by moonwa on 15-1-14.
 */
public abstract class P8583PackBuilder {
    protected P8583PackBuilder(){
        for(int i = 1; i <= 64; i++){
            _p8583Items.add(new EmptyP8583Item());
        }
    }

    ArrayList<P8583Item> _p8583Items = new ArrayList<P8583Item>();
    public P8583Item[] getP8583Items() {
        P8583Item[] items = new P8583Item[_p8583Items.size()];
         _p8583Items.toArray(items);
        return items;
    }
    public abstract void Init();

    public P8583Pack build() {
        P8583Pack p8583Pack = new P8583Pack();
        p8583Pack.setP8583Items(this._p8583Items);
        return p8583Pack;
    }

    public void addP8583ItemAt(int index, P8583Item item) {
        _p8583Items.set(index - 1, item);
    }
}
