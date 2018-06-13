import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.naming.spi.DirStateFactory.Result;

public class ImgUtils {
     public static BufferedImage cropImage(BufferedImage bufferedImage, int startX, int startY, int endX, int endY) {
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            float opacity = 0.5f;
            if (startX == -1) {
                startX = 0;
            }
            if (startY == -1) {
                startY = 0;
            }
            if (endX == -1) {
                endX = width - 1;
            }
            if (endY == -1) {
                endY = height - 1;
            }
            BufferedImage result = new BufferedImage(endX - startX, endY - startY, BufferedImage.TYPE_INT_ARGB);
            for (int x = startX; x < endX; ++x) {
                for (int y = startY; y < endY; ++y) {
                    Color c = new Color(0, 0, 0, 0);
                    int rgb = bufferedImage.getRGB(x, y);
                    if(rgb == -16777216) {
                    	result.setRGB(x - startX, y - startY, c.getRGB());
                        //System.out.print("h");
                    }
                    else {
                    	result.setRGB(x - startX, y - startY, rgb);
                    }
                    //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                }
            }
            //System.out.println(result.getRGB(1, 1));
            return result;
        }
     public static BufferedImage inverse(BufferedImage image) {
    	 int height = image.getHeight();
    	 int width = image.getWidth();
    	 int type = image.getType();
    	 BufferedImage result = new BufferedImage(width, height, type);
    	 for(int i = 0; i < width; ++i) {
    		 for(int j = 0; j < height; ++j) {
    			 result.setRGB(width-i-1, j, image.getRGB(i, j));
    		 }
    	 }
    	 return result ;
     }
}