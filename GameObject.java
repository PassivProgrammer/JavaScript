import java.awt.Graphics;

public abstract class GameObject {
	
	public int x;
	public int y;
	public Welt welt;
	public ID id;
	
	public GameObject(ID pid)
	{
		id = pid;
	}
	public abstract void render(Graphics g);
	public abstract ID getID();
}
