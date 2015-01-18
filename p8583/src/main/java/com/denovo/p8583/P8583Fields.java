package com.denovo.p8583;

import java.util.ArrayList;

public class P8583Fields {
    ArrayList<P8583Field> fields;

    public P8583Fields(ArrayList<P8583Field> fields) {
        this.fields = fields;
    }

    public void addP8583ItemAt(int index, P8583Field field) {
        fields.set(index - 1, field);
    }

    public ArrayList<P8583Field> getFields() {
        return this.fields;
    }
}
