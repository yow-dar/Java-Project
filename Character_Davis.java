import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Character_Davis extends Character {

	public Character_Davis(Game game) throws IOException {
		super(game);
		pictures = new Test();
		pictures.initial();
		walk_img = pictures.davis_walk;
		walk_img_inverse = pictures.davis_walk_inverse;
		jump_img = pictures.davis_jump;
		jump_img_inverse = pictures.davis_jump_inverse;
		hit_img = pictures.davis_hit;
		hit_img_inverse = pictures.davis_hit_inverse;
		skill1_img = pictures.davis_skill1;
		skill1_img_inverse = pictures.davis_skill1_inverse;
		skill2_img = pictures.davis_skill2;
		skill2_img_inverse = pictures.davis_skill2_inverse;
		attack_img = pictures.davis_attacked;
		attack_img_inverse = pictures.davis_attacked_inverse;
		stand_img = pictures.davis_stand;
		stand_img_inverse = pictures.davis_stand_inverse;
		lose_img = pictures.davis_lose;
		lose_img_inverse = pictures.davis_lose_inverse;
	}
}
