import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.lang.Math;

public class ColorMatcher extends Applet implements AdjustmentListener, ItemListener, ActionListener {
    
    public void init () {
	setLayout(new BorderLayout());
	
	Panel colorPanel = new Panel(new GridLayout());
	c1 = new Panel();
	c2 = new Panel();
	c1.setBackground(Color.BLACK);
	c2.setBackground(Color.BLACK);
	colorPanel.add(c1);
	colorPanel.add(c2);
	colorPanel.setPreferredSize(new Dimension(getWidth(), 250 ));
	
	Panel top = new Panel(new BorderLayout());
	Label instruc = new Label("Use the RGB sliders to match the color on the right to the color on the left");
	instruc.setFont(new Font("", Font.BOLD, 15));
	top.setBackground(new Color(0, 0, 0));
	top.setForeground(new Color(255, 255, 255));
	top.add(instruc, BorderLayout.SOUTH);
	top.add(colorPanel, BorderLayout.CENTER);
	
	seeColor = new Button("Show Correct Color");
	newColor = new Button ("Display New Color");
	seeColor.addActionListener(this);
	newColor.addActionListener(this);
	Panel buttons = new Panel(new GridLayout(2,1));
	buttons.add(newColor);
	buttons.add(seeColor);
	
	redSB = new Scrollbar(Scrollbar.VERTICAL, 0, 0, 0, 256);
	blueSB = new Scrollbar(Scrollbar.VERTICAL, 0, 0, 0, 256);
	greenSB = new Scrollbar(Scrollbar.VERTICAL, 0, 0, 0, 256);
	alphaSB = new Scrollbar(Scrollbar.VERTICAL, 0, 0, 0, 256);
	showRedSB = new Scrollbar(Scrollbar.VERTICAL, 0, 0, 0, 256);
	showBlueSB = new Scrollbar(Scrollbar.VERTICAL, 0, 0, 0, 256);
	showGreenSB = new Scrollbar(Scrollbar.VERTICAL, 0, 0, 0, 256);
	showAlphaSB = new Scrollbar(Scrollbar.VERTICAL, 0, 0, 0, 256);
	showRedSB.setEnabled(false);
	showBlueSB.setEnabled(false);
	showGreenSB.setEnabled(false);
	showAlphaSB.setEnabled(false);
	redSB.setBackground(Color.RED);
	blueSB.setBackground(Color.BLUE);
	greenSB.setBackground(Color.GREEN);
	alphaSB.setBackground(Color.GRAY);
	redSB.addAdjustmentListener(this);
	blueSB.addAdjustmentListener(this);
	greenSB.addAdjustmentListener(this);
	alphaSB.addAdjustmentListener(this);
	
	redTF = new TextField("0");
	blueTF = new TextField("0");
	greenTF = new TextField("0");
	alphaTF = new TextField("0");
	showRedTF = new TextField();
	showBlueTF = new TextField();
	showGreenTF = new TextField();
	showAlphaTF = new TextField();
	redTF.setBackground(new Color(204, 0, 0));
	redTF.setForeground(new Color(255, 255, 255));
	greenTF.setBackground(new Color(0, 204, 0));
	greenTF.setForeground(new Color(255, 255, 255));
	blueTF.setBackground(new Color(0, 0, 204));
	blueTF.setForeground(new Color(255, 255, 255));
	alphaTF.setBackground(new Color(96, 96, 96));
	alphaTF.setForeground(new Color(255, 255, 255));
	showRedTF.setBackground(new Color(255, 102, 102));
	showRedSB.setBackground(new Color(255, 153, 153));
	showGreenTF.setBackground(new Color(102, 255, 102));
	showGreenSB.setBackground(new Color(153, 255, 153));
	showBlueTF.setBackground(new Color(102, 102, 255));
	showBlueSB.setBackground(new Color(153, 153, 255));
	showAlphaTF.setBackground(new Color(192, 192, 192));
	showAlphaSB.setBackground(new Color(224, 224, 224));
	showRedTF.setVisible(false);
	showBlueTF.setVisible(false);
	showGreenTF.setVisible(false);
	showAlphaTF.setVisible(false);
	showRedSB.setVisible(false);
	showBlueSB.setVisible(false);
	showGreenSB.setVisible(false);
	showAlphaSB.setVisible(false);
	redTF.setEditable(false);
	blueTF.setEditable(false);
	greenTF.setEditable(false);
	alphaTF.setEditable(false);
	showRedTF.setEditable(false);
	showBlueTF.setEditable(false);
	showGreenTF.setEditable(false);
	showAlphaTF.setEditable(false);
	redTF.addActionListener(this);
	blueTF.addActionListener(this);
	greenTF.addActionListener(this);
	alphaTF.addActionListener(this);
	
	Panel pRed = new Panel(new BorderLayout());
	Panel pRedTF = new Panel(new GridLayout());Panel pRedSB = new Panel(new GridLayout());
	pRedTF.add(redTF);
	pRedTF.add(showRedTF);
	pRedSB.add(redSB);
	pRedSB.add(showRedSB);
	pRed.add(pRedTF, BorderLayout.NORTH);
	pRed.add(pRedSB, BorderLayout.CENTER);
	
	Panel pGreen = new Panel(new BorderLayout());
	Panel pGreenTF = new Panel(new GridLayout());
	Panel pGreenSB = new Panel(new GridLayout());
	pGreenTF.add(greenTF);
	pGreenTF.add(showGreenTF);
	pGreenSB.add(greenSB);
	pGreenSB.add(showGreenSB);
	pGreen.add(pGreenTF, BorderLayout.NORTH);
	pGreen.add(pGreenSB, BorderLayout.CENTER);
	
	Panel pBlue = new Panel(new BorderLayout());
	Panel pBlueTF = new Panel(new GridLayout());
	Panel pBlueSB = new Panel(new GridLayout());
	pBlueTF.add(blueTF);
	pBlueTF.add(showBlueTF);
	pBlueSB.add(blueSB);
	pBlueSB.add(showBlueSB);
	pBlue.add(pBlueTF, BorderLayout.NORTH);
	pBlue.add(pBlueSB, BorderLayout.CENTER);
	
	pAlpha = new Panel(new BorderLayout());
	Panel pAlphaTF = new Panel(new GridLayout());
	Panel pAlphaSB = new Panel(new GridLayout());
	pAlphaTF.add(alphaTF);
	pAlphaTF.add(showAlphaTF);
	pAlphaSB.add(alphaSB);
	pAlphaSB.add(showAlphaSB);
	pAlpha.add(pAlphaTF, BorderLayout.NORTH);
	pAlpha.add(pAlphaSB, BorderLayout.CENTER);
	slider = new Panel(new GridLayout(1, 0, 10, 0));
	slider.add(buttons);
	slider.add(pRed);
	slider.add(pGreen);
	slider.add(pBlue);
	
	Panel options = new Panel();
	addAlpha = new Checkbox("Add transpanrency slider");
	addBackground = new Checkbox("Add contrast effect");
	addAlpha.addItemListener(this);
	addBackground.addItemListener(this);
	addAlpha.setFont(new Font("", Font.BOLD, 15));
	addBackground.setFont(new Font("", Font.BOLD, 15));
	Label easy = new Label("Too Easy?");
        easy.setFont(new Font("", Font.BOLD, 15));
	options.add(easy);
	options.add(addAlpha);
	options.add(addBackground);
	
	
	add(slider, BorderLayout.CENTER);
	add(options, BorderLayout.SOUTH);
	add(top, BorderLayout.NORTH);
	

    }
    
    private Color getNewColor(){
	if (showAlpha) 
	    return new Color( (float) Math.random(), (float) Math.random(), (float) Math.random(), (float) Math.random());
	else
	    return new Color( (float) Math.random(), (float) Math.random(), (float) Math.random());
    }
    
    private void paintColors () {
	c1Color = getNewColor();
	c2Color = getNewColor();
	
	if (showBackground) {
	    g = c1.getGraphics();
	    g.setColor(c1Color.brighter());
	    g.fillRect(0, 0, getWidth(), getHeight());
	    g = c2.getGraphics();
	    g.setColor(c1Color.darker());
	    g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	g = c1.getGraphics();
	g.setColor(c1Color);
	g.fill3DRect(0 + offset, 0 + offset, c1.getWidth() - 2 * offset, c1.getHeight() - 2 * offset, true);
	g = c2.getGraphics();
	g.setColor(c2Color);
	g.fill3DRect(0 + offset, 0 + offset, c2.getWidth() - 2 * offset, c2.getHeight() - 2 * offset, true);
	
	redSB.setValue(c2Color.getRed());
	redTF.setText(c2Color.getRed() + "");
	greenSB.setValue(c2Color.getGreen());
	greenTF.setText(c2Color.getGreen() + "");
	blueSB.setValue(c2Color.getBlue());
	blueTF.setText(c2Color.getBlue() + "");
	alphaSB.setValue(c2Color.getAlpha());
	alphaTF.setText(c2Color.getAlpha() + "");
	
	showRedTF.setText(0 + "");
	showRedSB.setValue(0);
	showGreenTF.setText(0 + "");
	showGreenSB.setValue(0);
	showBlueTF.setText(0 + "");
	showBlueSB.setValue(0);
	showAlphaTF.setText(0 + "");
	showAlphaSB.setValue(0);
	
	showRedTF.setVisible(false);
	showBlueTF.setVisible(false);
	showGreenTF.setVisible(false);
	showAlphaTF.setVisible(false);
	showRedSB.setVisible(false);
	showBlueSB.setVisible(false);
	showGreenSB.setVisible(false);
	showAlphaSB.setVisible(false);
	
    }
    
    public void itemStateChanged(ItemEvent e){
	if (e.getSource() == addAlpha)
	    if (e.getStateChange() == e.SELECTED) {
		slider.add(pAlpha);
		showAlpha = true;
		validate();
	    }
	    else  {
		slider.remove(pAlpha);
		showAlpha = false;
		validate();
	    }
	
	if (e.getSource() == addBackground)
	    if (e.getStateChange() == e.SELECTED) {
		showBackground = true;
		offset = 20;
	    } else { 
		showBackground = false;
		offset = 0;
	    }
	
    }
    
    public void adjustmentValueChanged(AdjustmentEvent e) {
	if (e.getSource() == redSB) {
	    
	    c2Color = new Color(redSB.getValue(), c2Color.getGreen(), c2Color.getBlue(), c2Color.getAlpha());
	    redTF.setText(redSB.getValue() + "");
	    
	} else if (e.getSource() == greenSB) {
	    
	    c2Color = new Color(c2Color.getRed(), greenSB.getValue(), c2Color.getBlue(), c2Color.getAlpha());
	    greenTF.setText(greenSB.getValue() + "");
	    
	} else if (e.getSource() == blueSB) {
	    
	    c2Color = new Color(c2Color.getRed(), c2Color.getGreen(), blueSB.getValue(), c2Color.getAlpha());
	    blueTF.setText(blueSB.getValue() + "");
	    
	} else if (e.getSource() == alphaSB) {
	    
	    c2Color = new Color(c2Color.getRed(), c2Color.getGreen(), c2Color.getBlue(), alphaSB.getValue());
	    alphaTF.setText(alphaSB.getValue() + "");
	}
	
	g.setColor(c2Color);
	g.fill3DRect(0 + offset, 0 + offset, c2.getWidth() - 2 * offset, c2.getHeight() - 2 * offset, true);
	validate();
    }
    
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == newColor) {
	    paintColors();
	}
	
	if (e.getSource() == seeColor) {
	    
	    showRedTF.setText(c1Color.getRed() + "");
	    showRedSB.setValue(c1Color.getRed());
	    showGreenTF.setText(c1Color.getGreen() + "");
	    showGreenSB.setValue(c1Color.getGreen());
	    showBlueTF.setText(c1Color.getBlue() + "");
	    showBlueSB.setValue(c1Color.getBlue());
	    showAlphaTF.setText(c1Color.getAlpha() + "");
	    showAlphaSB.setValue(c1Color.getAlpha());
	    
	    showRedTF.setVisible(true);
	    showBlueTF.setVisible(true);
	    showGreenTF.setVisible(true);
	    showAlphaTF.setVisible(true);
	    showRedSB.setVisible(true);
	    showBlueSB.setVisible(true);
	    showGreenSB.setVisible(true);
	    showAlphaSB.setVisible(true);
	    
	}
	
	
    }
    
    int offset;
    Graphics g;
    Color c1Color, c2Color;
    boolean showAlpha, showBackground;
    Panel pAlpha, slider, c1, c2;
    Scrollbar redSB, blueSB, greenSB, alphaSB, showRedSB, showBlueSB, showGreenSB, showAlphaSB;
    Checkbox addAlpha, addBackground;
    Button seeColor, newColor;
    TextField redTF, blueTF, greenTF, alphaTF, showRedTF, showBlueTF, showGreenTF, showAlphaTF;
}