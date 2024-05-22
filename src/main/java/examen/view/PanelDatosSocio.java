package examen.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JToolBar;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import examen.controller.ControladorEquipo;
import examen.controller.ControladorSocio;
import examen.model.Equipo;
import examen.model.Socio;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class PanelDatosSocio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfNombre;
	private JTextField jtfPrimerApellido;
	private JTextField jtfSegundoApellido;
	private JSlider slider;
	private JLabel lblYearsCant;
	private JFormattedTextField jftfFecha;
	private JCheckBox jchkbActivo;
	private JComboBox<Equipo> jcbEquipo;
	private Socio currentSocio = null;

	/**
	 * Create the panel.
	 */
	public PanelDatosSocio() {
		setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);

		JButton btnFirst = new JButton("");
		btnFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showEntry( (Socio) ControladorSocio.getInstance().findFirst());
			}
		});
		btnFirst.setIcon(new ImageIcon(PanelDatosSocio.class.getResource("/examen/res/gotostart.png")));
		toolBar.add(btnFirst);

		JButton btnPrevious = new JButton("");
		btnPrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showEntry( (Socio) ControladorSocio.getInstance().findPrevious(currentSocio.getId()));
			}
		});
		btnPrevious.setIcon(new ImageIcon(PanelDatosSocio.class.getResource("/examen/res/previous.png")));
		toolBar.add(btnPrevious);

		JButton btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showEntry( (Socio) ControladorSocio.getInstance().findNext(currentSocio.getId()));
			}
		});
		btnNext.setIcon(new ImageIcon(PanelDatosSocio.class.getResource("/examen/res/next.png")));
		toolBar.add(btnNext);

		JButton btnLast = new JButton("");
		btnLast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showEntry( (Socio) ControladorSocio.getInstance().findLast());
			}
		});
		btnLast.setIcon(new ImageIcon(PanelDatosSocio.class.getResource("/examen/res/gotoend.png")));
		toolBar.add(btnLast);

		JButton btnNew = new JButton("Nuevo");
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newEntry();
			}
		});
		btnNew.setIcon(new ImageIcon(PanelDatosSocio.class.getResource("/examen/res/nuevo.png")));
		toolBar.add(btnNew);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveEntry();
			}
		});
		btnGuardar.setIcon(new ImageIcon(PanelDatosSocio.class.getResource("/examen/res/guardar.png")));
		toolBar.add(btnGuardar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteEntry();
			}
		});
		btnEliminar.setIcon(new ImageIcon(PanelDatosSocio.class.getResource("/examen/res/eliminar.png")));
		toolBar.add(btnEliminar);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("Gestión de Socios");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		jtfNombre = new JTextField();
		GridBagConstraints gbc_jtfNombre = new GridBagConstraints();
		gbc_jtfNombre.gridwidth = 2;
		gbc_jtfNombre.insets = new Insets(0, 0, 5, 0);
		gbc_jtfNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfNombre.gridx = 1;
		gbc_jtfNombre.gridy = 1;
		panel.add(jtfNombre, gbc_jtfNombre);
		jtfNombre.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Primer apellido:");
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 0;
		gbc_lblNewLabel_1_1.gridy = 2;
		panel.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);

		jtfPrimerApellido = new JTextField();
		jtfPrimerApellido.setColumns(10);
		GridBagConstraints gbc_jtfPrimerApellido = new GridBagConstraints();
		gbc_jtfPrimerApellido.gridwidth = 2;
		gbc_jtfPrimerApellido.insets = new Insets(0, 0, 5, 0);
		gbc_jtfPrimerApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfPrimerApellido.gridx = 1;
		gbc_jtfPrimerApellido.gridy = 2;
		panel.add(jtfPrimerApellido, gbc_jtfPrimerApellido);

		JLabel lblNewLabel_1_2 = new JLabel("Segundo apellido:");
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 0;
		gbc_lblNewLabel_1_2.gridy = 3;
		panel.add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);

		jtfSegundoApellido = new JTextField();
		jtfSegundoApellido.setColumns(10);
		GridBagConstraints gbc_jtfSegundoApellido = new GridBagConstraints();
		gbc_jtfSegundoApellido.gridwidth = 2;
		gbc_jtfSegundoApellido.insets = new Insets(0, 0, 5, 0);
		gbc_jtfSegundoApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfSegundoApellido.gridx = 1;
		gbc_jtfSegundoApellido.gridy = 3;
		panel.add(jtfSegundoApellido, gbc_jtfSegundoApellido);

		JLabel lblNewLabel_1_2_1 = new JLabel("Fecha de nacimiento:");
		GridBagConstraints gbc_lblNewLabel_1_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_2_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2_1.gridx = 0;
		gbc_lblNewLabel_1_2_1.gridy = 4;
		panel.add(lblNewLabel_1_2_1, gbc_lblNewLabel_1_2_1);

		jftfFecha = new JFormattedTextField();
		GridBagConstraints gbc_jftfFecha = new GridBagConstraints();
		gbc_jftfFecha.gridwidth = 2;
		gbc_jftfFecha.insets = new Insets(0, 0, 5, 0);
		gbc_jftfFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_jftfFecha.gridx = 1;
		gbc_jftfFecha.gridy = 4;
		panel.add(jftfFecha, gbc_jftfFecha);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("Antigüedad (años):");
		GridBagConstraints gbc_lblNewLabel_1_2_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_2_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_2_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2_1_1.gridx = 0;
		gbc_lblNewLabel_1_2_1_1.gridy = 5;
		panel.add(lblNewLabel_1_2_1_1, gbc_lblNewLabel_1_2_1_1);

		slider = new JSlider();
		slider.setMaximum(200);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 5;
		panel.add(slider, gbc_slider);

		lblYearsCant = new JLabel("New label");
		GridBagConstraints gbc_lblYearsCant = new GridBagConstraints();
		gbc_lblYearsCant.insets = new Insets(0, 0, 5, 0);
		gbc_lblYearsCant.gridx = 2;
		gbc_lblYearsCant.gridy = 5;
		panel.add(lblYearsCant, gbc_lblYearsCant);

		jchkbActivo = new JCheckBox("Socio en activo");
		GridBagConstraints gbc_jchkbActivo = new GridBagConstraints();
		gbc_jchkbActivo.anchor = GridBagConstraints.WEST;
		gbc_jchkbActivo.insets = new Insets(0, 0, 5, 5);
		gbc_jchkbActivo.gridx = 1;
		gbc_jchkbActivo.gridy = 6;
		panel.add(jchkbActivo, gbc_jchkbActivo);

		JLabel lblNewLabel_2 = new JLabel("Equipo:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 7;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		jcbEquipo = new JComboBox<Equipo>();
		GridBagConstraints gbc_jcbEquipo = new GridBagConstraints();
		gbc_jcbEquipo.gridwidth = 2;
		gbc_jcbEquipo.insets = new Insets(0, 0, 0, 5);
		gbc_jcbEquipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbEquipo.gridx = 1;
		gbc_jcbEquipo.gridy = 7;
		panel.add(jcbEquipo, gbc_jcbEquipo);
		
		/*
		 * Precarga de componentes y datos. 
		 */
		
		addControlCustomizableBehaviours();
		
		// Primer Registro.
		showEntry( (Socio) ControladorSocio.getInstance().findFirst());

	}
	
	/**
	 * 
	 */
	private void newEntry() {
		
		this.currentSocio = new Socio();
		this.currentSocio.setId(0);
		this.currentSocio.setEquipo(ControladorEquipo.getInstance().findById(1));
		
//		this.jtfNombre.setText("");
//		this.jtfPrimerApellido.setText("");
//		this.jtfSegundoApellido.setText("");
//		this.jftfFecha.setText("");
//		this.slider.setValue(0);
//		this.jchkbActivo.setSelected(false);
		
		showEntry(currentSocio);
		
	}
	
	/**
	 * 
	 */
	private void deleteEntry() {
		try {
			String respuestas[] = new String[] {"Sí", "No"};
			int opcionElegida = JOptionPane.showOptionDialog(
					null, 
					"¿Realmente desea eliminar el socio?", 
					"Eliminación de socio", 
			        JOptionPane.DEFAULT_OPTION, 
			        JOptionPane.WARNING_MESSAGE, 
			        null, respuestas, 
			        respuestas[1]);
		    
			// Puntero para registro anterior o siguiente.
			Socio socioActual = null;
			
			if(opcionElegida == 0) {
				ControladorSocio.getInstance().remove(currentSocio);
		    	  
		    	  // Decido qué registro voy a mostrar en pantalla.
		    	  // Voy a comprobar si existe un anterior, si existe lo muestro
		    	  // Si no existe anterior compruebo si existe siguiente, 
		    	  // si existe lo muestro. En caso contrario, no quedan registros
		    	  // así que muestro en blanco la pantalla
		    	  socioActual = ControladorSocio.getInstance().findPrevious(this.currentSocio.getId());
		    	  if (socioActual != null) { // Existe un anterior, lo muestro
		    		  showEntry(socioActual);
		    	  }
		    	  else {
		    		  socioActual = ControladorSocio.getInstance().findNext(this.currentSocio.getId());
			    	  if (socioActual != null) { // Existe un anterior, lo muestro
			    		  
			    		  // Sobreescribimos el puntero.
			    		  this.currentSocio = socioActual;
			    		  
			    		  showEntry(socioActual);
			    	  }
		    		  else { // No quedan registros en la tabla
		    			  newEntry();
		    		  }
		    	  }
		      }
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	/**
	 * 
	 */
	private void saveEntry() {
		
		this.currentSocio.setNombre(this.jtfNombre.getText());
		this.currentSocio.setApellido1(this.jtfPrimerApellido.getText());
		this.currentSocio.setApellido2(this.jtfSegundoApellido.getText());
		this.currentSocio.setFechaNacimiento(GuiUtils.getDateFromFormattedString("dd/MM/yyyy",
				this.jftfFecha.getText()));
		this.currentSocio.setAntiguedadAnios( ((Number) this.slider.getValue()).intValue());
		this.currentSocio.setActivo(this.jchkbActivo.isSelected());
		this.currentSocio.setEquipo( (Equipo) this.jcbEquipo.getSelectedItem() );
		
		try {
			ControladorSocio.getInstance().save(currentSocio);
			JOptionPane.showMessageDialog(null, "Socio guardado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "NO se ha guardado el socio. ERROR");
		}
		
	}
	
	/**
	 * 
	 * @param s
	 */
	private void showEntry(Socio socio) {
		
		if (socio != null) {
			this.currentSocio = socio;
			this.jtfNombre.setText(currentSocio.getNombre());
			this.jtfPrimerApellido.setText(currentSocio.getApellido1());
			this.jtfSegundoApellido.setText(currentSocio.getApellido2());
			this.jftfFecha.setText(GuiUtils.getFormattedStringFromDate("dd/MM/yyyy",
					currentSocio.getFechaNacimiento()));
			
			this.slider.setValue( ((Number) currentSocio.getAntiguedadAnios()).intValue() );
			showSliderAntiguedadDescriptor();
			
			this.jchkbActivo.setSelected(currentSocio.getActivo());
			
			selectEquipo();
			
		}
		
	}

	/**
	 * 
	 */
	private void selectEquipo() {
		for (int i = 0; i < this.jcbEquipo.getItemCount(); i++) {
			if (this.jcbEquipo.getItemAt(i).getId()
					== this.currentSocio.getEquipo().getId()) {
				this.jcbEquipo.setSelectedIndex(i);
			}
		}
	}

	/**
	 * 
	 */
	private void addControlCustomizableBehaviours() {

		// Comportamiento de la Fecha de nacimiento.
		this.jftfFecha.setFormatterFactory(new AbstractFormatterFactory() {
			@Override
			public AbstractFormatter getFormatter(JFormattedTextField tf) {
				return new JFormattedTextField.AbstractFormatter() {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

					@Override
					public String valueToString(Object value) throws ParseException {
						if (value != null && value instanceof Date) {
							jftfFecha.setBackground(Color.WHITE);
							return sdf.format(((Date) value));
						}
						return "";
					}

					@Override
					public Object stringToValue(String text) throws ParseException {
						try {
							return sdf.parse(text);
						} catch (Exception e) {
							jftfFecha.setBackground(Color.RED);
							JOptionPane.showMessageDialog(null, "Error en la fecha");
							return null;
						}
					}
				};
			}
		});
		
		// Cambio el label al ajustar valor.
		this.slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				showSliderAntiguedadDescriptor();
			}
		});
		
		// Agregamos los equipos al JComboBox.
		findAllEquipos();

	}
	
	/**
	 * 
	 */
	private void showSliderAntiguedadDescriptor() {
		this.lblYearsCant.setText(this.slider.getValue() + " años");
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
