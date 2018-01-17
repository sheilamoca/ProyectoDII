package Vista;

import Controlador.Controller;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VProyecto extends JFrame{
	
	private static JButton botonEmple,botonDepart,botonInicio;
	public static CardLayout cl;
	public static JPanel panelCard;
	
	public VProyecto() {
		
		
		JPanel panelSuperior= new JPanel();
		 panelCard= new JPanel();
		JPanel panelEmple= new VistaEmple();
		JPanel panelDepart= new VistaDepart();
		JPanel panelInicial= new JPanel();
		
		ImageIcon favicon = new ImageIcon("images/favicon.png");
		this.setIconImage(favicon.getImage());
			
		
		JLabel fondoImagen= new JLabel(new ImageIcon("images/fondo.png"));
		panelInicial.add(fondoImagen);
		
		setLayout(null);
		panelSuperior.setLayout(null);
		panelCard.setLayout(new CardLayout());
		
		panelSuperior.setBounds(0, 0, 850, 70);
		panelCard.setBounds(0, 110, 850, 550);
		
		panelCard.add(panelInicial, "inicial");
		panelCard.add(panelEmple, "empleados");
		panelCard.add(panelDepart, "departamentos");
		
		cl=(CardLayout) panelCard.getLayout();
		cl.show(panelCard, "inicial");
		
		ImageIcon iconoEmple= new ImageIcon("images/empleados.png");
		ImageIcon iconoDepart= new ImageIcon("images/iconoDepart.png");
		ImageIcon iconoIni= new ImageIcon("images/iconoInicio.png");
		
		botonEmple= new JButton("Empleados");
		botonEmple.setIcon(iconoEmple);
		botonDepart= new JButton("Departamentos");
		botonDepart.setIcon(iconoDepart);
		botonInicio= new JButton("");
		botonInicio.setIcon(iconoIni);
		
		botonInicio.setBounds(10, 20, 50, 50);
		botonEmple.setBounds(70, 20, 350, 50);
		botonDepart.setBounds(415,20, 350, 50);
		
		panelSuperior.add(botonInicio);
		panelSuperior.add(botonEmple);
		panelSuperior.add(botonDepart);
		

		add(panelSuperior);
		add(panelCard);
		
		setVisible(true);
		setSize(850, 670);
		setResizable(false);
		


		
		botonEmple.addActionListener(new ActionListener(){
	         
            @Override
            public void actionPerformed(ActionEvent e){
                cl.show(panelCard, "empleados");
            }
        });
	    
		botonDepart.addActionListener(new ActionListener(){
	         
            @Override
            public void actionPerformed(ActionEvent e){
                cl.show(panelCard, "departamentos");
            }
        });
		botonInicio.addActionListener(new ActionListener(){
	         
            @Override
            public void actionPerformed(ActionEvent e){
                cl.show(panelCard, "inicial");
            }
        });
		
		addWindowListener(new WindowAdapter(){
			  public void windowClosing(WindowEvent we){
			    System.exit(0);
			  }
			});
		
		
	}
	
	public static JPanel getPanelCard() {
		return panelCard;
	}

	public static void setPanelCard(JPanel panelCard) {
		VProyecto.panelCard = panelCard;
	}

	public static CardLayout getCl() {
		return cl;
	}

	public static void setCl(CardLayout cl) {
		VProyecto.cl = cl;
	}

	
	public static void setContoller(Controller c) {
		VBuscarEmple.getButtonBUSCAR().addActionListener(c);
		VCrearEmpleado.getBoton().addActionListener(c);
		VCrearEmpleado.getCombo().addActionListener(c);
		VBorrarEmple.getButtonBorrar().addActionListener(c);
		VCrearDepart.getBtnCrear().addActionListener(c);
		VBorrarDepart.getBtnCrear().addActionListener(c);
		VBuscarDepart.getBtnBuscar().addActionListener(c);
		VBuscarDepart.getBtnBorrar().addActionListener(c);

	}
	


}
