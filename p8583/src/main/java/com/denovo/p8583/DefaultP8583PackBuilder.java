package com.denovo.p8583;

import java.util.ArrayList;

public class DefaultP8583PackBuilder extends P8583PackBuilder{

    @Override
    public void Init(P8583Fields fields)
    {
        fields.addP8583ItemAt(2, new BcdVarLengthP8583Field(19, 2, false));
        fields.addP8583ItemAt(3, new BcdFixLengthP8583Field(6));
        fields.addP8583ItemAt(4, new BcdFixLengthP8583Field(12));
        fields.addP8583ItemAt(5, new AsciiFixLengthP8583Field(11));
        fields.addP8583ItemAt(11, new BcdFixLengthP8583Field(6));
        fields.addP8583ItemAt(12, new BcdFixLengthP8583Field(6));
        fields.addP8583ItemAt(13, new BcdFixLengthP8583Field(4));
        fields.addP8583ItemAt(14, new BcdFixLengthP8583Field(4));
        fields.addP8583ItemAt(15, new BcdFixLengthP8583Field(4));
        fields.addP8583ItemAt(22, new BcdFixLengthP8583Field(3));
        fields.addP8583ItemAt(23, new BcdFixLengthP8583Field(3));
        fields.addP8583ItemAt(25, new BcdFixLengthP8583Field(2));
        fields.addP8583ItemAt(26, new BcdFixLengthP8583Field(2));
        fields.addP8583ItemAt(32, new BcdVarLengthP8583Field(11, 2, true));
        fields.addP8583ItemAt(35, new BcdVarLengthP8583Field(37, 2, false));
        fields.addP8583ItemAt(36, new BcdVarLengthP8583Field(104, 3, false));
        fields.addP8583ItemAt(37, new AsciiFixLengthP8583Field(12));
        fields.addP8583ItemAt(38, new AsciiFixLengthP8583Field(2));
        fields.addP8583ItemAt(39, new AsciiFixLengthP8583Field(2));
        fields.addP8583ItemAt(41, new AsciiFixLengthP8583Field(8));
        fields.addP8583ItemAt(42, new AsciiFixLengthP8583Field(15));
        fields.addP8583ItemAt(44, new AsciiVarLengthP8583Field(25, 2, false));
        fields.addP8583ItemAt(47, new AsciiVarLengthP8583Field(62, 3, false));
        fields.addP8583ItemAt(48, new AsciiVarLengthP8583Field(62, 3, false));
        fields.addP8583ItemAt(49, new AsciiFixLengthP8583Field(3));
        fields.addP8583ItemAt(52, new BinaryFixLengthP8583Field(16));
        fields.addP8583ItemAt(53, new BcdFixLengthP8583Field(16));
        fields.addP8583ItemAt(54, new AsciiVarLengthP8583Field(20, 3, false));
        fields.addP8583ItemAt(59, new AsciiVarLengthP8583Field(20, 3, false));
        fields.addP8583ItemAt(60, new BcdVarLengthP8583Field(22, 3, false));
        fields.addP8583ItemAt(61, new BcdVarLengthP8583Field(29, 3, false));
        fields.addP8583ItemAt(62, new BinnaryVarLengthP8583Field(73, 3, true));
    }
}
