import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class Rompecabeza {

	private JFrame frame;
	private JPanel panelBotones;
	private JLabel tiempoNum;
	
	private JButton[][] botones= new JButton[4][4];
	private JButton[][] botonesReiniciar= new JButton[4][4];
	private JButton btnIniciar, btnPausar, btnReiniciarTablero;
	private Timer tiempo;
	private int centecimas = 0, segundos = 0, minutos = 0, horas = 	0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rompecabeza window = new Rompecabeza();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Rompecabeza() {
		initialize();
		tiempo = new Timer(10,accion);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 598, 510);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		agregarPaneles();
		
		panelBotones= new JPanel();
		panelBotones.setBackground(new Color(217, 178, 127));
		frame.getContentPane().add(panelBotones, BorderLayout.CENTER);
		panelBotones.setLayout(new GridLayout(4, 6, 0, 0));
		
		agregarbotones();
	}
	
	public void agregarPaneles() {
		JPanel panelNorth = new JPanel();
		panelNorth.setBackground(new Color(217, 178, 127));
		frame.getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		JPanel espacio = new JPanel();
		espacio.setBackground(new Color(217, 178, 127));
		panelNorth.add(espacio);
		
		JPanel espacio2 = new JPanel();
		espacio2.setBackground(new Color(217, 178, 127));
		espacio.add(espacio2);
		
		JPanel panelWest = new JPanel();
		panelWest.setBackground(new Color(217, 178, 127));
		frame.getContentPane().add(panelWest, BorderLayout.WEST);
		
		JPanel espacio22 = new JPanel();
		espacio22.setBackground(new Color(217, 178, 127));
		panelWest.add(espacio22);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(new Color(217, 178, 127));
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		JPanel espacio_1 = new JPanel();
		espacio_1.setBackground(new Color(217, 178, 127));
		panelSouth.add(espacio_1);
		
		JPanel espacio2_1 = new JPanel();
		espacio2_1.setBackground(new Color(217, 178, 127));
		espacio_1.add(espacio2_1);
		
		panelEast();
	}
	
	public void panelEast() {
		JPanel panelEast = new JPanel();
		panelEast.setBackground(new Color(217, 178, 127));
		frame.getContentPane().add(panelEast, BorderLayout.EAST);
		panelEast.setLayout(new GridLayout(9, 2, 0, 0));
		
		JPanel panelTiempo = new JPanel();
		panelTiempo.setBackground(new Color(217, 178, 127));
		panelEast.add(panelTiempo);
		panelTiempo.setLayout(new BorderLayout(0, 0));
		
		agregarEspacioWest(panelTiempo);
		agregarEspacioEast(panelTiempo);
		
		JLabel tiempoTexto = new JLabel("TIEMPO");
		panelTiempo.add(tiempoTexto, BorderLayout.CENTER);
		tiempoTexto.setHorizontalAlignment(SwingConstants.CENTER);
		tiempoTexto.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		JPanel vacio1 = new JPanel();
		vacio1.setBackground(new Color(217, 178, 127));
		panelEast.add(vacio1);
		vacio1.setLayout(new BorderLayout(0, 0));
		
		agregarEspacioWest(vacio1);
		agregarEspacioEast(vacio1);
		
		JPanel cronometro = new JPanel();
		cronometro.setBackground(new Color(217, 178, 127));
		panelEast.add(cronometro);
		cronometro.setLayout(new BorderLayout(0, 0));
		
		agregarEspacioWest(cronometro);
		agregarEspacioEast(cronometro);
		
		tiempoNum = new JLabel("00:00:00:00");
		tiempoNum.setHorizontalAlignment(SwingConstants.CENTER);
		tiempoNum.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		cronometro.add(tiempoNum);
		
		JPanel vacio2 = new JPanel();
		vacio2.setBackground(new Color(217, 178, 127));
		panelEast.add(vacio2);
		vacio2.setLayout(new BorderLayout(0, 0));
		
		agregarEspacioWest(vacio2);
		agregarEspacioEast(vacio2);
		
		JPanel iniciar = new JPanel();
		iniciar.setBackground(new Color(217, 178, 127));
		panelEast.add(iniciar);
		iniciar.setLayout(new BorderLayout(0, 0));
		
		agregarEspacioWest(agregarEspacioWest(iniciar));
		agregarEspacioEast(agregarEspacioEast(iniciar));
		
		btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desbloquearBotones();
				tiempo.start();
				btnIniciar.setEnabled(false);
				btnIniciar.setText("Reanudar");
				btnPausar.setEnabled(true);
				btnReiniciarTablero.setEnabled(true);
			}
		});
		
		btnIniciar.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		iniciar.add(btnIniciar, BorderLayout.CENTER);
		
		JPanel pausar = new JPanel();
		pausar.setBackground(new Color(217, 178, 127));
		panelEast.add(pausar);
		pausar.setLayout(new BorderLayout(0, 0));
		
		agregarEspacioWest(agregarEspacioWest(pausar));
		agregarEspacioEast(agregarEspacioEast(pausar));
		
		btnPausar = new JButton("Pausar");
		btnPausar.setEnabled(false);
		btnPausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tiempo.stop();
				btnIniciar.setEnabled(true);
				btnPausar.setEnabled(false);
				bloquearTablero();
			}
		});
		btnPausar.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		pausar.add(btnPausar, BorderLayout.CENTER);
		
		JPanel detener = new JPanel();
		detener.setBackground(new Color(217, 178, 127));
		panelEast.add(detener);
		detener.setLayout(new BorderLayout(0, 0));
		
		btnReiniciarTablero = new JButton("Reiniciar Tablero");
		btnReiniciarTablero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciarTiempo();
				cambiarTablero();
				bloquearTablero();
			}
		});
		btnReiniciarTablero.setEnabled(false);
		btnReiniciarTablero.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		detener.add(btnReiniciarTablero, BorderLayout.CENTER);
		
		agregarEspacioWest(agregarEspacioWest(detener));
		agregarEspacioEast(agregarEspacioEast(detener));
		
		JPanel vacio2_1 = new JPanel();
		vacio2_1.setBackground(new Color(217, 178, 127));
		panelEast.add(vacio2_1);
		vacio2_1.setLayout(new BorderLayout(0, 0));
		
		JPanel vacio3 = new JPanel();
		vacio3.setBackground(new Color(217, 178, 127));
		panelEast.add(vacio3);
		vacio3.setLayout(new BorderLayout(0, 0));
		
		agregarEspacioWest(vacio3);
		agregarEspacioEast(vacio3);
		btnNuevoTablero = new JButton("Nuevo Tablero");
		vacio3.add(btnNuevoTablero, BorderLayout.CENTER);
		btnNuevoTablero.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnNuevoTablero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarTexto();
				reiniciarTiempo();
				bloquearTablero();
			}
		});
	}

	public JPanel agregarEspacioEast(JPanel panel ) {
		JPanel espacio = new JPanel();
		espacio.setBackground(new Color(217, 178, 127));
		panel.add(espacio, BorderLayout.EAST);
		return espacio;
	}
	
	public JPanel agregarEspacioWest(JPanel panel ) {
		JPanel espacio = new JPanel();
		espacio.setBackground(new Color(217, 178, 127));
		panel.add(espacio, BorderLayout.WEST);
		return espacio;
	}
	
	
	int i,j;
	public void agregarbotones() {
		for(i = 0; i < 4; i++) { //filas
			for(j = 0; j < 4; j++) {  //columnas
				botones[i][j] = new JButton();
				botonesReiniciar[i][j] = new JButton();
				botones[i][j].setFont(new Font("Times New Roman", Font.PLAIN, 50));
				panelBotones.add(botones[i][j]);
				
				int k = i;
				int l = j;
				botones[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						verificar(botones[k][l]);
						rompecabezaCompleto();
						
					}
				});
			}
		}
		agregarTexto();
		
	}
	
	public void agregarTexto() {
		List<Integer> numeros = new ArrayList<Integer>();
        for (int i = 1; i <= 16; i++) {
        	numeros.add(i);
        }
        Collections.shuffle(numeros);
        JButton[][] aux= new JButton[4][4];
		int cont = 0;
		for(int i = 0; i < 4; i++) { 
			for(int j = 0; j < 4; j++) {  
				botones[i][j].setEnabled(false);
				botones[i][j].setText(""+ numeros.get(cont));
				//botones[i][j].setForeground();
				aux[i][j] = botones[i][j];
				cont++;
				if(botones[i][j].getText().equals("16")) {
                	botones[i][j].setText("");
                }	
				
				botonesReiniciar[i][j].setText(aux[i][j].getText());
			}
		}
		actualizarFrame();
	}
	
	public void bloquearTablero() {
		for(int i = 0; i < 4; i++) { 
			for(int j = 0; j < 4; j++) {  
				botones[i][j].setEnabled(false);
			}
		}
	}
	
	public void actualizarFrame() {
		frame.repaint();
		frame.revalidate();
	}
	
	public int[] ubicacion(JButton botonSeleccionado) {
		int[] posicion = new int[2];
		for(int i = 0; i < botones.length; i++) { 
			for(int j = 0; j < botones[0].length; j++) {  
				if(botones[i][j] == botonSeleccionado){
					posicion[0]=i;
					posicion[1]=j;
					break;
				}
			}
		}
		return posicion;	
	}
	
	public JButton botonVacio() {
		JButton vacio = new JButton();
		for(int i = 0; i < botones.length; i++) { 
			for(int j = 0; j < botones[0].length; j++) {  
				
				if(botones[i][j].getText().equals("")) {
					vacio = botones[i][j];
				    botones[i][j] = vacio;
					break;
				}
			}
		}
		return vacio;
	}
	
	public void desbloquearBotones() {
		for(i = 0; i < 4; i++) { 
			for(j = 0; j < 4; j++) { 
				botones[i][j].setEnabled(true);
			}
		}
	}
	
	public void verificar(JButton boton) {
		int [] ubiBoton= ubicacion(boton);
		int [] ubiBotonVacio= ubicacion(botonVacio());
		
		if(ubiBoton[0] == ubiBotonVacio[0]) {  //IZQUIERDA
			if(ubiBotonVacio[1]+1 == ubiBoton[1]) {
				cambiar(botonVacio(),boton);
				
			}
			if(ubiBotonVacio[1]-1 == ubiBoton[1]) {  //DERECHA
				cambiar(botonVacio(),boton);
			}
		}
		if(ubiBoton[1] == ubiBotonVacio[1]) {    //ARRIBA
			if(ubiBotonVacio[0]+1 == ubiBoton[0]){
				cambiar(botonVacio(),boton);			
			}
			if(ubiBotonVacio[0]-1 == ubiBoton[0]){   //ABAJO
				cambiar(botonVacio(),boton);
			}
		}
	}
	
	public boolean validarFinalizado() {
		boolean ordenados = false;
		int cont = 0, contOrdenados = 0;
		for(int i = 0; i < botones.length; i++) {
			for(int j = 0; j < botones[0].length; j++) {  
				cont++;
				if(botones[i][j].getText().equals(""+cont)){
					contOrdenados++;
				}
			}
		}
		if(contOrdenados == 15){
			ordenados = true;
		}
		return ordenados;
	}
	
	public void rompecabezaCompleto() {
		 if(validarFinalizado()) {
			 tiempo.stop();
			 JOptionPane.showMessageDialog(null, "¡Rompecabezas Completo!" + "\nTiempo de armado: " + tiempoNum.getText());
			 reiniciarTiempo();
			 agregarTexto();
		 }
	}
	
	public void cambiar(JButton boton1, JButton boton2) {
		boton1.setText(boton2.getText());
		boton2.setText("");
		actualizarFrame();
	}	
	public void cambiarTablero() {
		for(i = 0; i < 4; i++) { 
			for(j = 0; j < 4; j++) {  
				botones[i][j].setText(botonesReiniciar[i][j].getText());
			}
		}
		actualizarFrame();
	}
	public void actualizarTiempo() {
		String tiempo = (horas <= 9?"0":"")+ horas +":" +(minutos <= 9?"0":"")+ 
				minutos +":"+(segundos <= 9?"0":"")+ segundos +":"+
				(centecimas <= 9?"0":"")+ centecimas;
		tiempoNum.setText(tiempo);
	}
	
	public void reiniciarTiempo() {
		if(tiempo.isRunning()) {
			tiempo.stop();
		}
		centecimas = 0;
		segundos = 0;
		minutos = 0;
		horas = 0;
		
		actualizarTiempo();
		
		btnIniciar.setText("Iniciar");
		btnIniciar.setEnabled(true);
		btnPausar.setEnabled(false);
		btnReiniciarTablero.setEnabled(false);
	}
	private ActionListener accion = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			centecimas++;
			
			if(centecimas == 100) {
				segundos++;
				centecimas = 0;
			}
			
			if(segundos == 60) {
				minutos++;
				segundos = 0;
			}
			
			if(minutos == 60) {
				horas++;
				minutos = 0;
			}
			
			if(horas == 24) {
				horas = 0;
			}
			actualizarTiempo();	
			actualizarFrame();
		}	
	};
	private JButton btnNuevoTablero;
	
}