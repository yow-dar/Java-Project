import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Character_Deep extends Character {

	public Character_Deep(Game game) throws IOException {
		super(game);
		pictures = new Test();
		pictures.initial();
		walk_img = pictures.deep_walk;
		walk_img_inverse = pictures.deep_walk_inverse;
		jump_img = pictures.deep_jump;
		jump_img_inverse = pictures.deep_jump_inverse;
		hit_img = pictures.deep_hit;
		hit_img_inverse = pictures.deep_jump_inverse;
		skill1_img = pictures.deep_skill1;
		skill1_img_inverse = pictures.deep_skill1_inverse;
		skill2_img = pictures.deep_skill2;
		skill2_img_inverse = pictures.deep_skill2_inverse;
		attack_img = pictures.deep_attacked;
		attack_img_inverse = pictures.deep_attacked_inverse;
		stand_img = pictures.deep_stand;
		stand_img_inverse = pictures.deep_stand_inverse;
		lose_img = pictures.deep_lose;
		lose_img_inverse = pictures.deep_lose_inverse;
	}
}
