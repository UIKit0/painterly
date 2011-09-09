/*
 Copyright 2008 Sahab Yazdani
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
    http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import javax.swing.Icon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class VerticalTextIcon implements Icon, SwingConstants{ 
    private Font font = UIManager.getFont("Label.font"); 
    private FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(font); 
 
    private String text; 
    private int width, height; 
    private boolean clockwize; 
 
    public VerticalTextIcon(String text, boolean clockwize){ 
        this.text = text; 
        width = SwingUtilities.computeStringWidth(fm, text); 
        height = fm.getHeight(); 
        this.clockwize = clockwize; 
    } 
 
    public void paintIcon(Component c, Graphics g, int x, int y){ 
        Graphics2D g2 = (Graphics2D)g; 
        Font oldFont = g.getFont(); 
        Color oldColor = g.getColor(); 
        AffineTransform oldTransform = g2.getTransform(); 
 
        g.setFont(font); 
        g.setColor(Color.black); 
        if(clockwize){ 
            g2.translate(x+getIconWidth(), y); 
            g2.rotate(Math.PI/2); 
        }else{ 
            g2.translate(x, y+getIconHeight()); 
            g2.rotate(-Math.PI/2); 
        } 
        g.drawString(text, 0, fm.getLeading()+fm.getAscent()); 
 
        g.setFont(oldFont); 
        g.setColor(oldColor); 
        g2.setTransform(oldTransform); 
    } 
 
    public int getIconWidth(){ 
        return height; 
    } 
 
    public int getIconHeight(){ 
        return width; 
    }
}