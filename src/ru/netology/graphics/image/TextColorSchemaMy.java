package ru.netology.graphics.image;

public class TextColorSchemaMy implements TextColorSchema{

    @Override
    public char convert(int color) {
//        return new char[]{'#', '$', '@', '%', '*', '+', '-', '"', '\'', '\u0020'}[color / 28];
        return new char[]{'#', '$', '@', '%', '*', '+', '-', '"', '\'', '.'}[color / 28];
    }
}
