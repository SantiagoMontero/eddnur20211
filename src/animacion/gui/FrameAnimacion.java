package animacion.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import animacion.modelo.Figura;
import animacion.vista.DibujoFigura;
import animacion.vista.IDibujo;

public class FrameAnimacion extends JFrame {

	private Figura modelo;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrameAnimacion() {
		modelo = new Figura();
		modelo.setX(100);
		modelo.setY(100);
		
		init();
	}

	private void init() {
		JMenuBar bar = new JMenuBar();
		
		JMenu menu = new JMenu("Archivo");
		
		JMenuItem item = new JMenuItem("Comenzar");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				archivo_comenzar();
			}
		});
		
		menu.add(item);
		
		bar.add(menu);
		this.setJMenuBar(bar);

		IDibujo dibujo = new DibujoFigura(modelo);
		PanelAnimacion panel = new PanelAnimacion(dibujo);
		
		PanelPosicion panelPosicion = new PanelPosicion(modelo);
		
		modelo.addObserver(panel);
		modelo.addObserver(panelPosicion);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(panel, BorderLayout.CENTER);
		this.getContentPane().add(panelPosicion, BorderLayout.SOUTH);
		
		this.pack();
	}

	protected void archivo_comenzar() {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				mover200();
			}
		});
		
		thread.start();
	}

	protected void mover200() {
		for (int i = 0; i < 200; i++) {
			modelo.setX(modelo.getX() + 2);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {;}
		}
	}
	
	
}
