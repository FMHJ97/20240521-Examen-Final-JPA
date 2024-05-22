package examen.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import examen.controller.ControladorEquipo;
import examen.model.Equipo;

public class PanelClasificacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnReset;
	private JScrollPane scrollPane;
	private JButton btnArriba;
	private JButton btnAbajo;
	private JButton btnEliminar;
	private JList<Equipo> jListEquipos;
	private List<Equipo> teamList = null;
	private DefaultListModel<Equipo> dlm = null;

	/**
	 * Create the panel.
	 */
	public PanelClasificacion() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Clasificación");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{90, 0};
		gbl_panel.rowHeights = new int[]{25, 25, 25, 25, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showTeamsOnList();
			}
		});
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.fill = GridBagConstraints.VERTICAL;
		gbc_btnReset.anchor = GridBagConstraints.WEST;
		gbc_btnReset.insets = new Insets(0, 0, 5, 0);
		gbc_btnReset.gridx = 0;
		gbc_btnReset.gridy = 0;
		panel.add(btnReset, gbc_btnReset);
		
		btnArriba = new JButton("Arriba");
		btnArriba.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selectedIndex = jListEquipos.getSelectedIndex();
				
				if (selectedIndex != -1) {
					
					if (selectedIndex > 0) {
						
						Equipo team = (Equipo) dlm.getElementAt(selectedIndex);
						Equipo teamAnterior = (Equipo) dlm.getElementAt(selectedIndex - 1);
						
						dlm.setElementAt(team, selectedIndex - 1);
						dlm.setElementAt(teamAnterior, selectedIndex);
						
						jListEquipos.setSelectedValue(team, true);
					}
					
				} else {
					JOptionPane.showMessageDialog(null,
							"Seleccione un equipo de la lista");
				}

			}
		});
		
		GridBagConstraints gbc_btnArriba = new GridBagConstraints();
		gbc_btnArriba.anchor = GridBagConstraints.WEST;
		gbc_btnArriba.insets = new Insets(0, 0, 5, 0);
		gbc_btnArriba.gridx = 0;
		gbc_btnArriba.gridy = 1;
		panel.add(btnArriba, gbc_btnArriba);
		
		btnAbajo = new JButton("Abajo");
		btnAbajo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selectedIndex = jListEquipos.getSelectedIndex();
				
				if (selectedIndex != -1) {
					
					if ((dlm.getSize() - 1) > selectedIndex) {
						Equipo team = (Equipo) jListEquipos.getSelectedValue();
						Equipo teamAnterior = (Equipo) dlm.getElementAt(selectedIndex + 1);
						
						dlm.setElementAt(team, selectedIndex + 1);
						dlm.setElementAt(teamAnterior, selectedIndex);
						
						jListEquipos.setSelectedValue(team, true);
					}
					
				} else {
					JOptionPane.showMessageDialog(null,
							"Seleccione un equipo de la lista");
				}
				
			}
		});
		
		GridBagConstraints gbc_btnAbajo = new GridBagConstraints();
		gbc_btnAbajo.anchor = GridBagConstraints.WEST;
		gbc_btnAbajo.insets = new Insets(0, 0, 5, 0);
		gbc_btnAbajo.gridx = 0;
		gbc_btnAbajo.gridy = 2;
		panel.add(btnAbajo, gbc_btnAbajo);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					dlm.removeElementAt(jListEquipos.getSelectedIndex());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Seleccione un equipo de la lista");
				}
				
			}
		});
		
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.anchor = GridBagConstraints.WEST;
		gbc_btnEliminar.gridx = 0;
		gbc_btnEliminar.gridy = 3;
		panel.add(btnEliminar, gbc_btnEliminar);

		/**
		 * Precargamos datos.
		 */
		
		showTeamsOnList();
		
	}
	
	/**
	 * 
	 * @param equipos
	 * @return
	 */
	private DefaultListModel<Equipo> getDeaultListModelEquipo(List<Equipo> equipos) {
		
		// Agregamos a todos los equipos al modelo de la JList.
		dlm = new DefaultListModel<Equipo>();
		dlm.addAll(equipos);
		return dlm;
		
	}
	
	/**
	 * 
	 */
	private void showTeamsOnList() {
		
		this.teamList = (List<Equipo>) ControladorEquipo.getInstance().findAll();
		
		// Contruimos la JList con su respectivo modelo.
		dlm = getDeaultListModelEquipo(teamList);
		jListEquipos = new JList<Equipo>(dlm);
		jListEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.scrollPane.setViewportView(jListEquipos);
		
	}
	
}
