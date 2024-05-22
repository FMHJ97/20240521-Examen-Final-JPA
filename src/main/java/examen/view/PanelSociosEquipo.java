package examen.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import examen.controller.ControladorEquipo;
import examen.controller.ControladorSocio;
import examen.model.Equipo;
import examen.model.Socio;

public class PanelSociosEquipo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JComboBox<Equipo> jcbEquipo;
	private JRadioButton jrbNombre;
	private JRadioButton jrbPrimerApellido;
	private JRadioButton jrbSegundoApellido;
	private JRadioButton jrbFecha;
	private JScrollPane scrollPane;
	private List<Socio> socios;
	private DefaultTableModel dtm;
	private Socio selectedSocio;

	/**
	 * Create the panel.
	 */
	public PanelSociosEquipo() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Socios de Equipo");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Equipo:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jcbEquipo = new JComboBox<Equipo>();
		jcbEquipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showSociosInTable();
			}
		});
		GridBagConstraints gbc_jcbEquipo = new GridBagConstraints();
		gbc_jcbEquipo.insets = new Insets(0, 0, 5, 0);
		gbc_jcbEquipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbEquipo.gridx = 1;
		gbc_jcbEquipo.gridy = 1;
		add(jcbEquipo, gbc_jcbEquipo);
		
		JPanel panelBG = new JPanel();
		GridBagConstraints gbc_panelBG = new GridBagConstraints();
		gbc_panelBG.insets = new Insets(20, 0, 20, 0);
		gbc_panelBG.fill = GridBagConstraints.BOTH;
		gbc_panelBG.gridx = 1;
		gbc_panelBG.gridy = 2;
		add(panelBG, gbc_panelBG);
		GridBagLayout gbl_panelBG = new GridBagLayout();
		gbl_panelBG.columnWidths = new int[]{0, 0, 0};
		gbl_panelBG.rowHeights = new int[]{0, 0, 0};
		gbl_panelBG.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelBG.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelBG.setLayout(gbl_panelBG);
		
		jrbNombre = new JRadioButton("Ordenar por nombre");
		jrbNombre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showSociosInTable();
			}
		});
		
		GridBagConstraints gbc_jrbNombre = new GridBagConstraints();
		gbc_jrbNombre.anchor = GridBagConstraints.WEST;
		gbc_jrbNombre.insets = new Insets(0, 0, 5, 5);
		gbc_jrbNombre.gridx = 0;
		gbc_jrbNombre.gridy = 0;
		panelBG.add(jrbNombre, gbc_jrbNombre);
		
		jrbPrimerApellido = new JRadioButton("Ordenar por primer apellido");
		jrbPrimerApellido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showSociosInTable();
			}
		});
		
		GridBagConstraints gbc_jrbPrimerApellido = new GridBagConstraints();
		gbc_jrbPrimerApellido.anchor = GridBagConstraints.WEST;
		gbc_jrbPrimerApellido.insets = new Insets(0, 0, 5, 0);
		gbc_jrbPrimerApellido.gridx = 1;
		gbc_jrbPrimerApellido.gridy = 0;
		panelBG.add(jrbPrimerApellido, gbc_jrbPrimerApellido);
		
		jrbSegundoApellido = new JRadioButton("Ordenar por segundo apellido");
		jrbSegundoApellido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showSociosInTable();
			}
		});
		
		GridBagConstraints gbc_jrbSegundoApellido = new GridBagConstraints();
		gbc_jrbSegundoApellido.insets = new Insets(0, 0, 0, 5);
		gbc_jrbSegundoApellido.gridx = 0;
		gbc_jrbSegundoApellido.gridy = 1;
		panelBG.add(jrbSegundoApellido, gbc_jrbSegundoApellido);
		
		jrbFecha = new JRadioButton("Ordenar por fecha de nacimiento");
		jrbFecha.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showSociosInTable();
			}
		});
		GridBagConstraints gbc_jrbFecha = new GridBagConstraints();
		gbc_jrbFecha.gridx = 1;
		gbc_jrbFecha.gridy = 1;
		panelBG.add(jrbFecha, gbc_jrbFecha);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		/*
		 * Precarga datos
		 */
		
		addControlCustomizableBehaviours();

		showSociosInTable();
	}
	
	/**
	 * 
	 */
	private void showSociosInTable() {
		
		String tableColumnOrder = (this.jrbNombre.isSelected())? "nombre"
				: (this.jrbPrimerApellido.isSelected())? "apellido1"
				: (this.jrbSegundoApellido.isSelected())? "apellido2"
				: "fechaNacimiento";

		// Limpiamos la lista por cada vuelta.
		if (socios != null) {
			socios.clear();
		}
		
		socios = (List<Socio>) ControladorSocio.getInstance().findByOrderBy(
				"idEquipo", tableColumnOrder,
				((Equipo) this.jcbEquipo.getSelectedItem()).getId());
		
		
		// Convierto datos de lista a matriz
		Object m[][] = new Object[socios.size()][4];
		for (int i = 0; i < m.length; i++) {
			m[i][0] = socios.get(i).getNombre();
			m[i][1] = socios.get(i).getApellido1();
			m[i][2] = socios.get(i).getApellido2();
			m[i][3] = GuiUtils.getFormattedStringFromDate(
					"dd/MM/yyyy", socios.get(i).getFechaNacimiento());
			
		}
		
		// Creo una tabla con los datos anteriores, con un DefaultTableModel que no permita editar filas
		dtm = new DefaultTableModel(m, 
				new String[] {"Nombre", "Primer Apellido", "Segundo Apellido", "Fecha de nacimiento"}) {			
			/**
			 * La sobreescritura de este método nos permite controlar qué celdas queremos que sean editables
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		this.table = new JTable(dtm);
		// Limitamos el modo selección de filas a una única selección.
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
				if (e.getClickCount() > 1) {
					
					// Obtenemos el socio seleccioando de la tabla.
					selectedSocio = (Socio) socios.get(table.getSelectedRow());
					
					JOptionPane.showMessageDialog(null,
							"Has seleccionado a " + selectedSocio.getNombre()
							+ " con id: " + selectedSocio.getId());
				}
				
			}
			
		});
		
		this.scrollPane.setViewportView(table);
		
	}
	
	/**
	 * 
	 */
	private void addControlCustomizableBehaviours() {
		
		findAllEquipos();
		
		// Comportamiento excluyente en los JRadioButton
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrbNombre);
		bg.add(jrbPrimerApellido);
		bg.add(jrbSegundoApellido);
		bg.add(jrbFecha);
		jrbNombre.setSelected(true);
		
	}
	
	/**
	 * 
	 */
	private void findAllEquipos() {
		
		List<Equipo> equipos = (List<Equipo>) ControladorEquipo
				.getInstance().findAll();
		
		for (Equipo equipo : equipos) {
			this.jcbEquipo.addItem(equipo);
		}
		
	}

}
