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
     public final static void scale(String srcImageFile, String result, int height, int width, boolean bb) {
            try {
                double ratio = 0.0; // 缩放比例
               File f = new File(srcImageFile);
                BufferedImage bi = ImageIO.read(f);
                Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);//bi.SCALE_SMOOTH  选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
                // 计算比例
               if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                   double   ratioHeight = (new Integer(height)).doubleValue()/ bi.getHeight();
                   double   ratioWhidth = (new Integer(width)).doubleValue()/ bi.getWidth();
                   if(ratioHeight>ratioWhidth){
                       ratio= ratioHeight;
                   }else{
                       ratio= ratioWhidth;
                   }
                    AffineTransformOp op = new AffineTransformOp(AffineTransform//仿射转换
                            .getScaleInstance(ratio, ratio), null);//返回表示剪切变换的变换
                    itemp = op.filter(bi, null);//转换源 BufferedImage 并将结果存储在目标 BufferedImage 中。
                }
                if (bb) {//补白
                   BufferedImage image = new BufferedImage(width, height,
                            BufferedImage.TYPE_INT_RGB);//构造一个类型为预定义图像类型之一的 BufferedImage。
                    Graphics2D g = image.createGraphics();//创建一个 Graphics2D，可以将它绘制到此 BufferedImage 中。
                    g.setColor(Color.white);//控制颜色
                    g.fillRect(0, 0, width, height);// 使用 Graphics2D 上下文的设置，填充 Shape 的内部区域。
                    if (width == itemp.getWidth(null))
                        g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                                itemp.getWidth(null), itemp.getHeight(null),
                                Color.white, null);
                    else
                        g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                                itemp.getWidth(null), itemp.getHeight(null),
                                Color.white, null);
                    g.dispose();
                    itemp = image;
                }
                ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));      //输出压缩图片
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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