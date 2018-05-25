public class Character implements CharacterInterface{
  public void setHp(int hp){
      this.hp = hp;
  }
  public int getHp(){
    return hp;
  }
  
  public void setMp(int mp){
      this.mp = mp;
  }
  public int getHp(){
    return mp;
  }
  
  public void setAttack_point(int attackpoint){
      this.attack_point = attackpoint;
  }
  public int getAttack_point(){
    return attack_point;
  }
  
  public void setProtectingHp(int protecting_hp){
      this.protecting_hp = protecting_hp;
  }
  public int getHp(){
    return protecting_hp;
  }
  
  private int protecting_hp;  
  private int hp;
  private int mp;
  private int attack_point;
  private boolean ishit;
  
  
}
