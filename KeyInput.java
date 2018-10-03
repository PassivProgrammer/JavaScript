import java.awt.MouseInfo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyInput extends KeyAdapter implements MouseListener{
	
	private Handler handler;
	private Hud hud;

	
	public KeyInput(Handler pHandler,Hud pHud)
	{
		this.handler = pHandler;
		this.hud = pHud;

	}
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();

		for(int i = 0; i<handler.list.size(); i++) {
			
			GameObject temp = handler.list.get(i);
			
			//if(temp.getId() == ID.Runplayer) {
				//if(key == KeyEvent.VK_D) ((RunPlayer) temp).setSpeedX(3);

				
			//}
			
			
			
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		handler.welt.tile[handler.maus()[1]][handler.maus()[0]].founding();
		hudArea();
		

		
	}
	
	public void hudArea()
	{
		int[] mouse = {handler.mouseX,handler.mouseY};
		if(handler.hud.checkClicked(mouse) != null)if(handler.hud.clicked(mouse).equals("pause") )handler.pause();
	}

	
	

}
