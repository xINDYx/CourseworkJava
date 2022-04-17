package ru.netology.graphics;

import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.image.*;
import ru.netology.graphics.server.GServer;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new TextGraphicsConverterMy(); // Создайте тут объект вашего класса конвертера

//        converter.setMaxRatio(4);
        converter.setMaxHeight(100);
//        converter.setMaxWidth(300);

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

        // Или то же, но с выводом на экран:
//        String url = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
        String url = "http://www.make4fun.com/download/funnypixstore/2328.jpg";
        String imgTxt = converter.convert(url);
        System.out.println(imgTxt);
    }
}
