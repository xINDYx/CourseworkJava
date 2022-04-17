package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class TextGraphicsConverterMy implements TextGraphicsConverter {
    private int newWidth;
    private int newHeight;
    private double newMaxRatio;
    private TextColorSchema schema = null;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {

        BufferedImage img = ImageIO.read(new URL(url));
        int height;
        int width;


        if(newMaxRatio != 0){
            double currentRatio = Math.max( (double)img.getWidth() / (double)img.getHeight() , (double)img.getHeight() / (double)img.getWidth() );
            if(currentRatio > newMaxRatio){
                throw new BadImageSizeException(currentRatio, newMaxRatio);
            }

        }

//        if(newHeight > 0 || newWidth > 0 ){
//            int ratioHeight = newHeight == 0 ? 0 : img.getHeight() / newHeight;
//            int ratioWidth = newWidth == 0 ? 0 : img.getWidth() / newWidth;
//            int maxRatio = Math.max(ratioHeight, ratioWidth);
//            height = img.getHeight() / maxRatio;
//            width = img.getWidth() / maxRatio;
//        }else{
//            height = img.getHeight();
//            width = img.getWidth();
//        }





        if(newHeight != 0 && newHeight < img.getHeight() && newWidth < img.getWidth()){
            int maxRatio = Math.max(img.getHeight() / newHeight, img.getWidth() / newWidth);
            height = img.getHeight() / maxRatio;
            width = img.getWidth() / maxRatio;
        }else if(newHeight != 0 && newHeight < img.getHeight()){
            height = newHeight;
            width = img.getWidth()/(img.getHeight()/newHeight);
        }else if(newWidth != 0 && newWidth < img.getWidth()){
            width = newWidth;
            height = img.getHeight()/(img.getWidth()/newWidth);
        }else{
            height = img.getHeight();
            width = img.getWidth();
        }




        Image scaledImage = img.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);

        BufferedImage bwImg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D graphics = bwImg.createGraphics();

        graphics.drawImage(scaledImage, 0, 0, null);

        WritableRaster bwRaster = bwImg.getRaster();

        if(schema == null){
            schema = new TextColorSchemaMy();
        }

        StringBuilder strBuilder = new StringBuilder();

        for (int h = 0; h < bwImg.getHeight(); h++) {
            for (int w = 0; w < bwImg.getWidth(); w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                strBuilder.append(c).append(c);
            }
            strBuilder.append("\n");
        }

        return strBuilder.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        newWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        newHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        newMaxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}
