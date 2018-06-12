package game;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//import com.etoak.util.ImgUtils;
//頛詨���� ���� 撌血
public class Test {

	public BufferedImage[] davis0 = new BufferedImage[70];
	public BufferedImage[] davis_stand = new BufferedImage[4];
	public BufferedImage[] davis_walk = new BufferedImage[4];
	public BufferedImage[] davis_hit = new BufferedImage[4];
	public BufferedImage[] davis_attacked = new BufferedImage[3];
	public BufferedImage[] davis_jump = new BufferedImage[4];
	public BufferedImage[] davis2 = new BufferedImage[40];
	public BufferedImage[] davis_skill1 = new BufferedImage[7];
	public BufferedImage[] davis_skill2 = new BufferedImage[8];
	public BufferedImage[] davis_ball_temp = new BufferedImage[12];
	public BufferedImage[] davis_ball = new BufferedImage[4];
	
	public BufferedImage[] davis_stand_inverse = new BufferedImage[4];
	public BufferedImage[] davis_walk_inverse = new BufferedImage[4];
	public BufferedImage[] davis_hit_inverse = new BufferedImage[4];
	public BufferedImage[] davis_attacked_inverse = new BufferedImage[3];
	public BufferedImage[] davis_jump_inverse = new BufferedImage[4];
	public BufferedImage[] davis_skill1_inverse = new BufferedImage[7];
	public BufferedImage[] davis_skill2_inverse = new BufferedImage[8];
	public BufferedImage[] davis_ball_inverse = new BufferedImage[4];

	public BufferedImage[] deep0 = new BufferedImage[70];
	public BufferedImage[] deep_stand = new BufferedImage[4];
	public BufferedImage[] deep_walk = new BufferedImage[4];
	public BufferedImage[] deep_hit = new BufferedImage[4];
	public BufferedImage[] deep_attacked = new BufferedImage[3];
	public BufferedImage[] deep_jump = new BufferedImage[4];
	public BufferedImage[] deep2 = new BufferedImage[40];
	public BufferedImage[] deep_skill1 = new BufferedImage[5];
	public BufferedImage[] deep_skill2 = new BufferedImage[20];
	public BufferedImage[] deep_ball_temp = new BufferedImage[8];
	public BufferedImage[] deep_ball = new BufferedImage[4];
	public BufferedImage[] deep1 = new BufferedImage[70];

	public BufferedImage[] deep_stand_inverse = new BufferedImage[4];
	public BufferedImage[] deep_walk_inverse = new BufferedImage[4];
	public BufferedImage[] deep_hit_inverse = new BufferedImage[4];
	public BufferedImage[] deep_attacked_inverse = new BufferedImage[3];
	public BufferedImage[] deep_jump_inverse = new BufferedImage[4];
	public BufferedImage[] deep_skill1_inverse = new BufferedImage[5];
	public BufferedImage[] deep_skill2_inverse = new BufferedImage[20];
	public BufferedImage[] deep_ball_inverse = new BufferedImage[4];
   
	public void initial() throws IOException {
    	/////////////////davis/////////////////////////////
        File newfile1=new File("C:/Users/user/Desktop/test/davis0.png");
        BufferedImage bufferedimage1 = new BufferedImage(800, 560, BufferedImage.TYPE_INT_ARGB);
        bufferedimage1 = ImageIO.read(newfile1);
        File newfile2=new File("C:/Users/user/Desktop/test/davis2.png");
        BufferedImage bufferedimage2 = new BufferedImage(800, 340, BufferedImage.TYPE_INT_ARGB);
        bufferedimage2 = ImageIO.read(newfile2);
        ImageIO.write(bufferedimage2, "png", new File("C:/Users/user/Desktop/test/davis/"+"test"+".png"));
        File newfile3=new File("C:/Users/user/Desktop/test/davis_ball.png");
        BufferedImage bufferedimage3 = new BufferedImage(328, 141, BufferedImage.TYPE_INT_ARGB);
        bufferedimage3 = ImageIO.read(newfile3);
    	
        int length = 80;
        for(int i = 0; i <= 720; i += length) {
        	for(int j = 0; j <= 480; j += length) {
        		int num = 10*j/80+i/80;
        		davis0[num] = ImgUtils.cropImage(bufferedimage1,i+1,j+1,i+80,j+80); 
        		//ImageIO.write(davis0[10*j/80+i/80], "png", new File("C:/Users/user/Desktop/test/davis/"+(10*j/80+i/80)+".png"));
        	}
        }
        for(int i = 0; i <= 720; i += length) {
        	for(int j = 0; j <= 240; j += length) {
        		int num = 10*j/length+i/length;
        		if(j<=2*length) {
        			davis2[num] = ImgUtils.cropImage(bufferedimage2,i+1,j+1,i+length,j+length); 
        		}
        		else {
        			davis2[num] = ImgUtils.cropImage(bufferedimage2,i+1,j+1,i+length,j+length+5); 
        		}
        		//ImageIO.write(davis2[10*j/length+i/length], "png", new File("C:/Users/user/Desktop/test/davis/"+(10*j/length+i/length)+".png"));
        	}
        }
        for(int i = 0; i <= 246; i += 82) {
        	for(int j = 0; j <= 94; j += 47) {
        		int num = 4*j/47+i/82;
        		davis_ball_temp[num] = ImgUtils.cropImage(bufferedimage3,i,j,i+81,j+46);
        		//ImageIO.write(davis_ball_temp[4*j/47+i/82], "png", new File("C:/Users/user/Desktop/test/davis/"+(4*j/47+i/82)+".png"));
        	}
        }
        for(int i = 0; i < 4; ++i) {
        	davis_stand[i] = davis0[i];
        	davis_stand_inverse[i] = ImgUtils.inverse(davis_stand[i]);
        } 
        for(int i = 0; i < 4; ++i) {
        	davis_walk[i] = davis0[i+4];
        	davis_walk_inverse[i] = ImgUtils.inverse(davis_walk[i]);
        }
        for(int i = 0; i < 4; ++i) {
        	davis_hit[i] = davis0[i+10];
        	davis_hit_inverse[i] = ImgUtils.inverse(davis_hit[i]);
        }
        for(int i = 0; i < 3; ++i) {
        	davis_attacked[i] = davis0[i+53];
        	davis_attacked_inverse[i] = ImgUtils.inverse(davis_attacked[i]);
        }
        for(int i = 0; i < 4; ++i) {
        	davis_jump[i] = davis0[i+60];
        	davis_jump_inverse[i] = ImgUtils.inverse(davis_jump[i]);
        }
        for(int i = 0; i < 7; ++i) {
        	davis_skill1[i] = davis2[i];
        	davis_skill1_inverse[i] = ImgUtils.inverse(davis_skill1[i]);
        }
        for(int i = 0; i < 8; ++i) {
        	davis_skill2[i] = davis2[i+30];
        	davis_skill2_inverse[i] = ImgUtils.inverse(davis_skill2[i]);
        }
        for(int i = 0; i < 4; ++i) {
        	davis_ball[i] = davis_ball_temp[i];
        	davis_ball_inverse[i] = ImgUtils.inverse(davis_ball[i]);
        }
        
       ///////////////DEEP///////////////////////////
        
        newfile1=new File("C:/Users/user/Desktop/test/deep0.png");
        bufferedimage1 = new BufferedImage(800, 560, BufferedImage.TYPE_INT_ARGB);
        bufferedimage1 = ImageIO.read(newfile1);
        newfile2=new File("C:/Users/user/Desktop/test/deep2.png");
        bufferedimage2 = new BufferedImage(800, 340, BufferedImage.TYPE_INT_ARGB);
        bufferedimage2 = ImageIO.read(newfile2);
        ImageIO.write(bufferedimage2, "png", new File("C:/Users/user/Desktop/test/deep/"+"test"+".png"));
        newfile3=new File("C:/Users/user/Desktop/test/deep_ball.png");
        bufferedimage3 = new BufferedImage(328, 141, BufferedImage.TYPE_INT_ARGB);
        bufferedimage3 = ImageIO.read(newfile3);
        File newfile4=new File("C:/Users/user/Desktop/test/deep1.png");
        BufferedImage bufferedimage4 = new BufferedImage(800, 560, BufferedImage.TYPE_INT_ARGB);
        bufferedimage4 = ImageIO.read(newfile4);

        for(int i = 0; i <= 720; i += length) {
        	for(int j = 0; j <= 480; j += length) {
        		int num = 10*j/80+i/80;
        		deep0[num] = ImgUtils.cropImage(bufferedimage1,i+1,j+1,i+80,j+80); 
        		//ImageIO.write(deep0[10*j/80+i/80], "png", new File("C:/Users/user/Desktop/test/deep/"+(10*j/80+i/80)+".png"));
        	}
        }
        for(int i = 0; i <= 720; i += length) {
        	for(int j = 0; j <= 480; j += length) {
        		int num = 10*j/80+i/80;
        		deep1[num] = ImgUtils.cropImage(bufferedimage4,i+1,j+1,i+80,j+80); 
        		//ImageIO.write(deep1[10*j/80+i/80], "png", new File("C:/Users/user/Desktop/test/deep/"+(10*j/80+i/80)+".png"));
        	}
        }
        for(int i = 0; i <= 720; i += length) {
        	for(int j = 0; j <= 240; j += length) {
        		int num = 10*j/length+i/length;
        		if(j<=2*length) {
        			deep2[num] = ImgUtils.cropImage(bufferedimage2,i+1,j+1,i+length,j+length); 
        		}
        		else {
        			deep2[num] = ImgUtils.cropImage(bufferedimage2,i+1,j+1,i+length,j+length+5); 
        		}
        		//ImageIO.write(deep2[10*j/length+i/length], "png", new File("C:/Users/user/Desktop/test/deep/"+(10*j/length+i/length)+".png"));
        	}
        }
        for(int i = 0; i <= 246; i += 82) {
        	for(int j = 0; j <= 83; j += 83) {
        		int num = 4*j/83+i/82;
        		deep_ball_temp[num] = ImgUtils.cropImage(bufferedimage3,i,j,i+81,j+82); 
        		//ImageIO.write(deep_ball_temp[4*j/83+i/82], "png", new File("C:/Users/user/Desktop/test/deep/"+(4*j/83+i/82)+".png"));
        	}
        }
        for(int i = 0; i < 4; ++i) {
        	deep_stand[i] = deep0[i];
        	deep_stand_inverse[i] = ImgUtils.inverse(deep_stand[i]);
        } 
        for(int i = 0; i < 4; ++i) {
        	deep_walk[i] = deep0[i+4];
        	deep_walk_inverse[i] = ImgUtils.inverse(deep_walk[i]);
        }
        for(int i = 0; i < 4; ++i) {
        	deep_hit[i] = deep0[i+10];
        	deep_hit_inverse[i] = ImgUtils.inverse(deep_hit[i]);
        }
        for(int i = 0; i < 3; ++i) {
        	deep_attacked[i] = deep0[i+53];
        	deep_attacked_inverse[i] = ImgUtils.inverse(deep_attacked[i]);
        }
        for(int i = 0; i < 4; ++i) {
        	deep_jump[i] = deep0[i+60];
        	deep_jump_inverse[i] = ImgUtils.inverse(deep_jump[i]);
        }
        for(int i = 0; i < 5; ++i) {
        	deep_skill1[i] = deep1[i+65];
        	deep_skill1_inverse[i] = ImgUtils.inverse(deep_skill1[i]);
        	//ImageIO.write(deep_skill1[i], "png", new File("C:/Users/user/Desktop/test/deep/"+i+".png"));
        	//ImageIO.write(deep_skill1_inverse[i], "png", new File("C:/Users/user/Desktop/test/deep/"+i+"inverse.png"));
        }
        for(int i = 0; i < 10; ++i) {
        	deep_skill2[i] = deep2[i+20];
        	deep_skill2_inverse[i] = ImgUtils.inverse(deep_skill2[i]);
        	//ImageIO.write(deep_skill2[i], "png", new File("C:/Users/user/Desktop/test/deep/"+i+".png"));
        }
        for(int i = 0; i < 10; ++i) {
        	deep_skill2[i+10] = deep2[39-i];
        	deep_skill2_inverse[i] = ImgUtils.inverse(deep_skill2[i]);
        	//ImageIO.write(deep_skill2[i+10], "png", new File("C:/Users/user/Desktop/test/deep/"+(i+10)+".png"));
        }
        for(int i = 0; i < 4; ++i) {
        	deep_ball[i] = deep_ball_temp[i];
        	deep_ball_inverse[i] = ImgUtils.inverse(deep_ball[i]);
        	//ImageIO.write(deep_ball_temp[i], "png", new File("C:/Users/user/Desktop/test/deep/"+i+".png"));
        }
    }
}