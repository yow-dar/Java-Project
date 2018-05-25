public interface CharacterInterface{
 
 public int attack();
 public void denfense();
 public void jump();//keyboard event happen ,call jump
 public void move();
 public void skill();
 
 protected void changePic();
}
public class Character implements CharacterInterface{
  private int hp;
 private int mp;
 private int attack_hit;
 private boolean ishit; 
}
