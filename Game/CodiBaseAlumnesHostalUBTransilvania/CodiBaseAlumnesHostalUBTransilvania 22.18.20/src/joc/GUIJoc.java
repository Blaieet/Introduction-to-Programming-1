package joc;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import joc.actors.Heroi;

/**
 * Classe principal que controla i coordina el joc.
 * 
 * @author danieldelrio@ub.edu
 */
public class GUIJoc implements KeyListener {

    private JFrame frame;    
    private Canvas canvas;
    private Context context;
    private Marcador marcador;
    private boolean doRender = false;
    
    private JFrame frameFinal;
    
    private Joc joc;
    private LogicaJoc logica;

    private StringBuffer clauSecreta;
    private boolean paraulaIntroduida;
    private boolean activaParaula;

    
    private static final int NS_PER_FRAME = 1000 * 1000  * 1000 / 
            Constants.FRAMES_PER_SEGON;
    
    int x = 0;
    int y = 0; 
    
    /**
     * Constructor que posa en marxa el joc proporcionat.
     * 
     * @param joc el joc
     */
    public GUIJoc(Joc joc) {
        this.joc = joc;
        this.logica = new LogicaJoc(joc);
        init();
        run();
    }

    public void keyTyped(KeyEvent e) {
    }

    /**
     * Listener dels events del teclat.
     * 
     * @param e l'event
     */
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
	
	if (activaParaula) {
	    if (keyCode >= KeyEvent.VK_0 && keyCode <= KeyEvent.VK_9 ||
		keyCode >= KeyEvent.VK_A && keyCode <= KeyEvent.VK_Z) {
		clauSecreta.append(String.valueOf((char)keyCode));
	    }
	}

        switch (keyCode) {
            case KeyEvent.VK_ESCAPE:
                logica.exit();
                break;
            case KeyEvent.VK_M:                
                logica.pausar(); 
                break;
            case KeyEvent.VK_S:
                logica.iniciar();
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
                context.updateKeys(e, true);
                break;
            case KeyEvent.VK_ENTER:
                paraulaIntroduida = true;
                break;
            case KeyEvent.VK_DELETE: // En el MAC, cal esborrar fent fn+del
                paraulaIntroduida = false;
                activaParaula = true;
                if (clauSecreta.length()>0) {
                    clauSecreta.deleteCharAt(clauSecreta.length()-1);
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        context.updateKeys(e, false);
    }
    
    // private methods *********************************************************
    
    private void init() {
	GraphicsEnvironment ge = 
	    GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        frame = new JFrame("UB :·: Programació 1: Hostal UBTransilvania -");
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(Constants.AMPLADA_FINESTRA, 
                Constants.ALCADA_FINESTRA));
        
        frame.setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());        
        frame.add(canvas, BorderLayout.CENTER);        
        frame.setResizable(false);
        frame.setIgnoreRepaint(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.addKeyListener(this);
        
        frame.pack();                
        
        Point cp = ge.getCenterPoint();
        cp.translate(- frame.getWidth() / 2, - frame.getHeight() / 2);
        frame.setLocation(cp.x, cp.y);
        
        frame.setVisible(true);                
        canvas.createBufferStrategy(2);
        canvas.requestFocus();
        
        marcador = new Marcador();
        
        context = new Context(joc);

        activaParaula = false;
        clauSecreta = new StringBuffer("");
        paraulaIntroduida = false;
        
    }
    
    private void run() {
        boolean contentsLost = false;
        
        long tempsFramePrevi = System.currentTimeMillis();
        long tempsFrameFinal;
        
        while (logica.getEstat() != LogicaJoc.EstatJoc.EXIT) {
            
            long ara = System.currentTimeMillis();
            long duracioFrame = (ara - tempsFramePrevi);
            tempsFramePrevi = ara;
            tempsFrameFinal = System.nanoTime() + NS_PER_FRAME;            

            BufferStrategy bufferStrategy = canvas.getBufferStrategy();
            context.setTempsFrame(duracioFrame);
                        
            // això controla els estats del joc
            switch (logica.getEstat()) {
                case MENU:
                    mostrarMenu(bufferStrategy);
                    break;
                case INICIANT:
                    break;
                case JUGANT:
                    actualitzarJoc();
                    doRender(bufferStrategy);            
                    break;
                case PROVAFINAL:
                    mostrarProvaFinal(bufferStrategy);
                    break;
                case GAMEOVER:
                    mostrarGameOver(bufferStrategy);
                    break;
                case PAUSAT:
                    break;
                case WIN: //En el cas de guanyar, crida el metode mostrarVictoria, que imprimeix el misatge de "guanyar"
                    mostrarVictoria(bufferStrategy);
                    break;
            }
                            
            contentsLost = bufferStrategy.contentsLost();
            if (contentsLost) {
                if (bufferStrategy.contentsRestored())
                    contentsLost = false;
            } else {
                bufferStrategy.show();
            }            
            
            esperarFiFrame(tempsFrameFinal);  
        }
        System.out.println("Fins aviat!");
        System.exit(0);
    }
        
    private void actualitzarJoc() {
        Heroi heroi = (Heroi)joc.getHeroi();

        if (heroi.getMavisTrobada() && heroi.getLletresTrobades()) { //Statement que establira si he trobat la Mavis i la paraula
            logica.setEstat(LogicaJoc.EstatJoc.WIN);
        }

        if (heroi.isMort()) {
            logica.setEstat(LogicaJoc.EstatJoc.GAMEOVER);
        } else {        
      
            heroi.actualitzar(context);
            Habitacio h = joc.getHostal().getHabitacio();
            for (Actor actor : h.getActors()) {
                actor.actualitzar(context);
                if (actor.getEstat() == Constants.ESTAT_ACTIU && 
                        heroi.getLimits().intersects(actor.getLimits())) {
                    Colisio colisio = new Colisio(heroi);
                    actor.tractarColisio(colisio);
                }
            }        
        }
    }
    
    private void esperarFiFrame(long tempsFrameFinal) {
        Thread.yield();
        while (System.nanoTime() < tempsFrameFinal) {
            Thread.yield();
            try {
                Thread.sleep(1);
            } catch (Exception e) {}
        }
    }    
    
    private void doRender(BufferStrategy bufferStrategy) {
        doRender = true;
        Graphics g = bufferStrategy.getDrawGraphics();
        
        Graphics2D g2 = (Graphics2D)g; 
        joc.getHostal().render(g2);
        Habitacio h = joc.getHostal().getHabitacio();
        for (Actor actor : h.getActors()) {
            if (actor.getEstat() != Constants.ESTAT_INACTIU)
                actor.render(g2);
        }
        
        joc.getHeroi().render(g2);
        
        marcador.render(context, g2);
        doRender = false;
    }            

    private void mostrarMenu(BufferStrategy bufferStrategy) {
        
        Graphics g = bufferStrategy.getDrawGraphics();        
        Graphics2D g2 = (Graphics2D)g; 
        dibuixarMarc(g2, Color.BLUE);
        
        Image image = Util.carregarImatge("heroi.png", 0, 0, Heroi.AMPLADA, Heroi.ALCADA);
        g2.drawImage(image, 100, 65, frame);
        
        image = Util.carregarImatge("hostal.jpg", 0, 0, 500, 336);
        g2.drawImage(image, 145, 165, frame);
        
        Font f = new Font("Dialog", Font.PLAIN, 30);
        g2.setFont(f);
        g2.setColor(Color.white);
        g2.drawString("* UBTransilvània *", 160, 90);
        
        f = new Font("Dialog", Font.PLAIN, 16);
        g2.setFont(f);
        g2.drawString("'S' Nova Partida", 180, 120);
        g2.drawString("'ESC' Sortir", 180, 140);
    }
    
    private void mostrarProvaFinal(BufferStrategy strategy) {
        boolean paraulaCorrecta= true;
        Graphics g = strategy.getDrawGraphics();
        Graphics2D g2 = (Graphics2D)g; 
        dibuixarMarc(g2, Color.RED);
        Heroi heroi = (Heroi)joc.getHeroi();

        Font f = new Font("Dialog", Font.PLAIN, 30);
        activaParaula = true;

        g2.setColor(Color.MAGENTA);
        f = new Font("Dialog", Font.BOLD, 20);
        g2.setFont(f);
        g2.drawString("Has trobat la Mavis i tens totes les lletres", 160, 90);
        g2.drawString("Quina es la paraula Secreta?", 200, 150);
        g2.drawString("Ordena les lletres que tens en l'inventari", 210, 200);

        String s = heroi.getLletres();

        // Mostra les lletres de l'inventari
        for (int i=0; i<s.length(); i++) {
            g2.setColor(Color.yellow);
            g2.drawString(s.substring(i, i+1), 220 + i * 20 , 240 + 20* i);
            g2.setFont(f);                
        }
        
        // Aquesta part de codi només és útil si es fa l'extensió de posar 
        // una paraula magica
        if (activaParaula) {
            // Fa el echo de les lletres que es van introduint
            // primer s'esborra i despres s'escriu
            g2.setColor(Color.black);
            g2.drawString(clauSecreta.toString(), 280 , 180 + 20*10);

            g2.setColor(Color.blue);
            g2.drawString(clauSecreta.toString(), 280 , 180 + 20*10);
            g2.setFont(f);
        }
        if (paraulaIntroduida) {
        }
    }
    
    private void mostrarGameOver(BufferStrategy strategy) {
        
        Graphics g = strategy.getDrawGraphics();
        Graphics2D g2 = (Graphics2D)g; 
        dibuixarMarc(g2, Color.RED);
        Heroi heroi = (Heroi)joc.getHeroi();
        
        // Cal canviar el missatge de Game Over en el cas que l'heroi hagi trobat la Mavis 
        // i hagi trobat les lletres
        Font f = new Font("Dialog", Font.PLAIN, 30);
        if (joc.getHeroi().getVida() <= 0.0f) {
            g2.setFont(f);
            g2.setColor(Color.white);
            g2.drawString("* G A M E   O V E R *", 160, 90);
            f = new Font("Dialog", Font.PLAIN, 16);
            g2.setFont(f);
            g2.drawString("Estàs mort! Un altre dia potser ...", 160, 140);            
        } else {
        }
        g2.setColor(Color.MAGENTA);
        f = new Font("Dialog", Font.PLAIN, 20);
        g2.setFont(f);
        g2.drawString("'ESC' Sortir", 100, 280 + 20*10);
        activaParaula = false;
    }

    private void mostrarVictoria(BufferStrategy strategy) { //Metode que sortira al guanyar el joc
        Graphics g = strategy.getDrawGraphics();
        Graphics2D g2 = (Graphics2D)g; 
        dibuixarMarc(g2, Color.RED);
        Heroi heroi = (Heroi)joc.getHeroi();
        
        Font f = new Font("Dialog", Font.PLAIN, 30);
        
        g2.setFont(f);
        g2.setColor(Color.white);
        g2.drawString("* HAS GUANYAT! *", 160, 90);
        f = new Font("Dialog", Font.PLAIN, 16);
        g2.setFont(f);
        g2.drawString("Felicitats, una altre partida?", 160, 140);      

        g2.setColor(Color.MAGENTA);
        f = new Font("Dialog", Font.PLAIN, 20);
        g2.setFont(f);
        g2.drawString("'ESC' Sortir", 100, 280 + 20*10);
        activaParaula = false;
    }
    
    private void dibuixarMarc(Graphics2D g2, Color color) {
        Rectangle r = canvas.getBounds();
        r.setBounds((int)r.getX() + 20, (int)r.getY() + 20, (int)(r.getWidth() - 40),
                (int)(r.getHeight() - 40));
        
        g2.setColor(Color.BLACK);
        g2.fill(canvas.getBounds());
        g2.setColor(color);
        g2.setStroke(new BasicStroke(10.f, BasicStroke.CAP_ROUND, 
                BasicStroke.JOIN_ROUND, 20.0f));        
        g2.draw(r);        
    }
}
