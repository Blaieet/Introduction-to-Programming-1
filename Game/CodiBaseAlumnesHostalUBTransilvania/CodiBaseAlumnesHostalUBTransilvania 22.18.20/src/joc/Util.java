package joc;




import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Mètodes d'utilitat general.
 * 
 * @author danieldelrio@ub.edu
 */
public class Util {

    /**
     * Carrega una habitació des de fitxer.
     * 
     * @param nomFitxer el nom del fitxer
     * @return la habitació carregada
     */     
    public static Habitacio carregarHabitacio(String nomFitxer) {
        Habitacio habitacio = new Habitacio();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(getFitxerAsStream(nomFitxer)));
            String linea = null;
            int fila = 0;
            while ((linea = in.readLine()) != null) {
                String[] arr = linea.split(",");
                for (int i = 0; i < arr.length; i++) {
                    habitacio.setValor(fila, i, arr[i].charAt(0));
                }
                fila++;
            }
            
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(String.format(
                    "El fitxer %s no existeix", nomFitxer));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ex) {                    
                }
            }
        }
        return habitacio;
    }
    
        
    public static java.net.URL getUrl(String nomFitxer) {
        return Thread.currentThread().getContextClassLoader().
                getResource("recursos/" + nomFitxer);
    }

    public static InputStream getFitxerAsStream(String nomFitxer) {
        return Thread.currentThread().getContextClassLoader().
                getResourceAsStream("recursos/" + nomFitxer);
    }
    
    /**
     * Carrega una subimage d'una imatge d'un fitxer.
     * 
     * @param nomFitxer el nom del fitxer
     * @param x la x de la subimatge
     * @param y la y de la subimatge
     * @param amplada l'amplada de la subimatge
     * @param alcada l'alaçada de la subimatge
     * @return la subimatge
     */
    public static BufferedImage carregarImatge(String nomFitxer, int x, int y, 
            int amplada, int alcada) {
        
        ImageIcon icon = new ImageIcon(getUrl(nomFitxer));
        
        BufferedImage bi = new BufferedImage(icon.getIconWidth(), 
                icon.getIconHeight(), BufferedImage.TYPE_BYTE_INDEXED);
        
        GraphicsConfiguration g = bi.createGraphics().getDeviceConfiguration();
        BufferedImage out = g.createCompatibleImage(bi.getWidth(),bi.getHeight(),Transparency.BITMASK);
        
        Graphics2D g2d = out.createGraphics();
        g2d.setComposite(AlphaComposite.Src);
        g2d.drawImage(icon.getImage(), 0, 0, new Canvas());
        g2d.dispose();
        
        return out.getSubimage(x, y, amplada, alcada);        
    }
    
    /**
     * Carrega una imatge d'un fitxer.
     * 
     * @param nomFitxer el nom del fitxer
     * @return la imatge
     */
    public static BufferedImage carregarImatge(String nomFitxer) {
        ImageIcon icon = new ImageIcon(getUrl(nomFitxer));        
        BufferedImage bi = new BufferedImage(icon.getIconWidth(), 
                icon.getIconHeight(), BufferedImage.TYPE_BYTE_INDEXED);                
        Graphics g = bi.getGraphics();
        g.drawImage(icon.getImage(), 0, 0, new Canvas());
        g.dispose();
        return bi;
    }
    
}
