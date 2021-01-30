package blockshifter.apps.swing;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import blockshifter.view.BlockDisplay;

public class BlockPanel extends JPanel implements BlockDisplay{
    private static final int SIZE = 100;
    private int x = 0;
    private int y = 0;
    private int max;
    private Moved moved;

    public BlockPanel(int max) {
        this.max = max;
        MouseHandler handler = new MouseHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g.setColor(Color.black);
        int size = max*SIZE;
        for (int i = 0; i <= size; i+=SIZE) {
            g.drawLine(0, i, size, i);
            g.drawLine(i, 0, i, size);
        }
        g.setColor(Color.red);
        g.fillRect(x* SIZE, y * SIZE, SIZE, SIZE);
    }
    
    @Override
    public void display(int x, int y){
        this.x = x;
        this.y = y;
        repaint();
    }
    
    @Override
    public void on(Moved moved){
        this.moved = moved;
    }
    
    private class MouseHandler implements MouseListener, MouseMotionListener{
        private boolean grabbed = true;
        
        @Override
        public void mouseClicked(MouseEvent event){
        }
        
        @Override
        public void mousePressed(MouseEvent event){
            if ((event.getX()/SIZE < x) || (event.getY()/SIZE < y) || 
                 (event.getX()/SIZE > x+1) || (event.getY()/SIZE > y+1)) return;
            grabbed = true;
        }
        
        @Override
        public void mouseReleased(MouseEvent event){
            grabbed = false;
        }
        
        @Override
        public void mouseEntered(MouseEvent event){
        }
        
        @Override
        public void mouseExited(MouseEvent event){
        }
        
        @Override
        public void mouseDragged(MouseEvent event){
            if (grabbed && moved != null) moved.to(event.getX()/SIZE, event.getY()/SIZE);
        }
        
        @Override
        public void mouseMoved(MouseEvent event){
        }
    }    
}
