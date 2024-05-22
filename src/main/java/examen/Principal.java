package examen;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import examen.utils.Apariencia;
import examen.view.PanelClasificacion;
import examen.view.PanelDatosSocio;
import examen.view.PanelSociosEquipo;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Principal instance = null;
	
	static {
		Apariencia.setAparienciasOrdenadas(Apariencia.aparienciasOrdenadas);
	}
	
	/**
	 * Constructor.
	 */
	public Principal() {
		super("Examen Final - 20240521");
		
		this.setBounds(100, 100, 700, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		JTabbedPane jtp = new JTabbedPane();
		
		PanelDatosSocio panelDatosSocio = new PanelDatosSocio();
		PanelSociosEquipo panelSociosEquipo = new PanelSociosEquipo();
		PanelClasificacion panelClasificacion = new PanelClasificacion();
		
		jtp.addTab("Datos del Socio", panelDatosSocio);
		jtp.addTab("Socios por Equipo", panelSociosEquipo);
		jtp.addTab("Clasificación", panelClasificacion);
		
		this.getContentPane().add(jtp);
	}
	
	/**
	 * Singleton.
	 * @return
	 */
	public static Principal getInstance() {
		if (instance == null) {
			instance = new Principal();
		}
		return instance;
	}

	/**
	 * Método Principal.
	 * @param args
	 */
	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

}
