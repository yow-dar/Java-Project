import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Character_Deep extends character{
	Test pictures;
	int index = 0;
	
	public Character_Deep() throws IOException{
		pictures = new Test();
		pictures.initial();
	}
	public void stand() throws InterruptedException {
		if(!getHit()) {
			if(getRight() == true)
				changeImage(pictures.deep_stand);
			else
				changeImage(pictures.deep_stand_inverse);
		}
	}
	public void walk(int i) throws InterruptedException {//1上2右3下4左
		if(i==1) sety(gety()+1);
		if(i==2) setx(getx()+1);
		if(i==3) sety(gety()-1);
		if(i==4) setx(getx()-1);
		if(getRight() == true)
			to_be_painted = pictures.deep_walk[index%4];
		else
			to_be_painted = pictures.deep_walk_inverse[index%4];
		++index;
	}
	public void hit() throws InterruptedException {
		if(getRight() == true)
			changeImage(pictures.deep_hit);
		else
			changeImage(pictures.deep_hit_inverse);
	}
	public void attacked(character player1, character player2) throws InterruptedException {//player自己 player2敵人
		double dx ,dy;
		if(getRight() == true) {
			changeImage(pictures.deep_attacked);
			dx = player2.getx()-player1.getx();
		}
		else {
			changeImage(pictures.deep_attacked_inverse);
			dx = player1.getx()-player2.getx();
		}
		dy = player1.gety()-player2.gety();
		//player1自己 打player2敵人 如果在範圍內就會被打
		if(dx<10 && Math.abs(dy)<10) {
			player2.setHit(true);
			player2.setHP(player2.getHP() - this.normalDamage);
			player2.start();
		}
	}
	public void jump() throws InterruptedException {
		if(getRight() == true)
			changeImage(pictures.deep_jump);
		else
			changeImage(pictures.deep_jump_inverse);
	}
	public void skill1() throws InterruptedException {
		if(getRight() == true)
			changeImage(pictures.deep_skill1);
		else
			changeImage(pictures.deep_skill1_inverse);
		
	}
	public void skill2() throws InterruptedException {
		if(getRight() == true)
			changeImage(pictures.deep_skill2);
		else
			changeImage(pictures.deep_skill2_inverse);
		if(getRight())
			setx(getx()+2);
		else
			setx(getx()-2);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(getRight())
			try {
				changeImage(pictures.deep_attacked);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		else
			try {
				changeImage(pictures.deep_attacked_inverse);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			TimeUnit.MICROSECONDS.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	public void start() {
		Thread t = new Thread(this);
		t.start();
	}
}
