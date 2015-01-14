package com.denovo.p8583;

public class DefaultP8583PackBuilder extends P8583PackBuilder{

    @Override
    public void Init() {
        super.addP8583ItemAt(2, new BcdVarLengthP8583Item(19, 2, false));
        super.addP8583ItemAt(3, new BcdFixLengthP8583Item(6));
        super.addP8583ItemAt(4, new BcdFixLengthP8583Item(12));
        super.addP8583ItemAt(5, new AsciiFixLengthP8583Item(11));
        super.addP8583ItemAt(11, new BcdFixLengthP8583Item(6));
        super.addP8583ItemAt(12, new BcdFixLengthP8583Item(6));
        super.addP8583ItemAt(13, new BcdFixLengthP8583Item(4));
        super.addP8583ItemAt(14, new BcdFixLengthP8583Item(4));
        super.addP8583ItemAt(15, new BcdFixLengthP8583Item(4));
        super.addP8583ItemAt(22, new BcdFixLengthP8583Item(3));
        super.addP8583ItemAt(23, new BcdFixLengthP8583Item(3));
        super.addP8583ItemAt(25, new BcdFixLengthP8583Item(2));
        super.addP8583ItemAt(26, new BcdFixLengthP8583Item(2));
        super.addP8583ItemAt(32, new BcdVarLengthP8583Item(11, 2, true));
        super.addP8583ItemAt(35, new BcdVarLengthP8583Item(37, 2, false));
        super.addP8583ItemAt(36, new BcdVarLengthP8583Item(104, 3, false));
        super.addP8583ItemAt(37, new AsciiFixLengthP8583Item(12));
        super.addP8583ItemAt(38, new AsciiFixLengthP8583Item(2));
        super.addP8583ItemAt(39, new AsciiFixLengthP8583Item(2));
        super.addP8583ItemAt(41, new AsciiFixLengthP8583Item(8));
        super.addP8583ItemAt(42, new AsciiFixLengthP8583Item(15));
        super.addP8583ItemAt(44, new AsciiVarLengthP8583Item(25, 2, false));
        super.addP8583ItemAt(47, new AsciiVarLengthP8583Item(62, 3, false));
        super.addP8583ItemAt(48, new AsciiVarLengthP8583Item(62, 3, false));
        super.addP8583ItemAt(49, new AsciiFixLengthP8583Item(3));
        super.addP8583ItemAt(52, new BinaryFixLengthP8583Item(16));
        super.addP8583ItemAt(53, new BcdFixLengthP8583Item(16));
        super.addP8583ItemAt(53, new AsciiVarLengthP8583Item(20, 3, false));
        super.addP8583ItemAt(59, new AsciiVarLengthP8583Item(20, 3, false));
        super.addP8583ItemAt(60, new BcdVarLengthP8583Item(22, 3, false));
        super.addP8583ItemAt(61, new BcdVarLengthP8583Item(29, 3, false));
        super.addP8583ItemAt(62, new BinnaryVarLengthP8583Item(73, 3, true));
    }
}
