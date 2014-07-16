import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class TrafficSignal extends Applet {
    
    public static void main(String [] args) {
	Frame f = new Frame();
	Applet applet = new TrafficSignal();
	applet.init();
	f.add(applet);
	f.setSize(500, 500);
	f.setVisible(true);
    }
    
    public void init(){
	setLayout(new BorderLayout());
	
	Panel buttonPanel = new Panel(new GridLayout(0,1));
	
	Button buttons[] = {new Button("Normal"), new Button("Stop"), new Button("Caution"), new Button("Right Turn"), new Button("Off")};
	for (Button b : buttons)
	    buttonPanel.add(b);
	add(buttonPanel, BorderLayout.WEST);
	
	SignalCanvas signalCanvas = new SignalCanvas(buttons);
	add(signalCanvas, BorderLayout.CENTER);
	
	new SignalThread(signalCanvas).start();
    }
}

class SignalCanvas extends Canvas implements ActionListener {
    SignalCanvas(Button buttons []) {
	this.buttons = buttons;
	for (Button b : buttons)
	    b.addActionListener(this);
    }
    
    public void paint (Graphics g) {
	int signalTop = getHeight() / 2 - height / 2, signalEdge = getWidth() / 2 - width / 2;
	setBackground(Color.BLACK);
	g.setColor(Color.ORANGE);
	g.fillRect(signalEdge, signalTop, width, height);
	for (int i = 0; i < 3; i ++) {
	    g.setColor(colors[i]);
	    g.fillOval(signalEdge + margin, signalTop + margin + i * (lightDiam + margin), lightDiam, lightDiam);
	}
	
	if (buttonPressed == "Right Turn") {
	    int arrowLength = (lightDiam -10) * 2 / 3;
	    g.setColor(Color.GREEN);
	    g.fillRect(signalEdge + 2 * margin, signalTop + 3 * margin + 2 * lightDiam + (lightDiam / 2 - 5), arrowLength, 10);
	    int xPoints[] = {lightDiam / 2, lightDiam / 2 + 10, lightDiam - 8, lightDiam / 2 + 10, lightDiam / 2, lightDiam - margin - 14};
	    int yPoints[] ={lightDiam / 2 - arrowLength / 2, lightDiam / 2 - arrowLength / 2, lightDiam / 2, lightDiam / 2 + arrowLength / 2, lightDiam / 2 + arrowLength / 2, lightDiam / 2};
	    
	    for(int i = 0; i < xPoints.length; i ++) {
		xPoints[i] += signalEdge + 2 * margin;
		yPoints[i] += signalTop + 3 * margin + 2 * lightDiam;
	    }
	    
	    g.fillPolygon(xPoints, yPoints, xPoints.length);
	    
	}
	
    }
    public void actionPerformed(ActionEvent e){
	Button source = (Button)e.getSource();
	buttonPressed = source.getLabel();
	if(buttonPressed == "Off" || buttonPressed == "Right Turn")
	    repaint();
    }
    
    public String getButtonPressed() {
	return buttonPressed;
    }
    
    public void turnOn(int i) {
	if (i < 0 || i > 2)
	    throw new IllegalArgumentException("A Traffic signal only has three lights (0-2)");
	colors[i] = onColors[i];
	repaint();
    }
    
    public void turnOff(int i) {
	if (i < 0 || i > 2)
	    throw new IllegalArgumentException("A Traffic signal only has three lights (0-2)");
	colors[i] = Color.BLACK;
	repaint();
    }
    
    String buttonPressed = "Off";
    Color onColors[] = {Color.RED, Color.YELLOW, Color.GREEN};
    Color colors[] = {Color.BLACK, Color.BLACK, Color.BLACK};
    int height = 170, width = 60, lightDiam = 50, margin = 5;
    Button buttons[];
}

class SignalThread extends Thread {
    SignalThread(SignalCanvas signalCanvas){
	this.signalCanvas = signalCanvas;
    }
    public void run() {
	while(true) {
	    System.out.println("");
	    if(signalCanvas.getButtonPressed() == "Normal") 
		normalSignal();      
	    
	    if(signalCanvas.getButtonPressed() == "Stop") 
		stopSignal();
	    
	    if(signalCanvas.getButtonPressed() == "Caution")
		cautionSignal(); 
	}
    }
    
    private void normalSignal() {
	signalCanvas.turnOn(2);
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) { }   
	
	signalCanvas.turnOn(1);
	signalCanvas.turnOff(2);
	try {
	    Thread.sleep(400);
	} catch (InterruptedException e) { }         
	
	signalCanvas.turnOn(0);
	signalCanvas.turnOff(1);
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) { }   
	
	signalCanvas.turnOff(0); 
    }
    
    private void stopSignal() {
	signalCanvas.turnOn(0);
	try {
	    Thread.sleep(900);
	} catch (InterruptedException e) { }  
	signalCanvas.turnOff(0);
	try {
	    Thread.sleep(900);
	} catch (InterruptedException e) { }  
    }
    
    private void cautionSignal() {
	signalCanvas.turnOn(1);
	try {
	    Thread.sleep(900);
	} catch (InterruptedException e) { }  
	signalCanvas.turnOff(1);
	try {
	    Thread.sleep(900);
	} catch (InterruptedException e) { }  
    }
    
    SignalCanvas signalCanvas;
}