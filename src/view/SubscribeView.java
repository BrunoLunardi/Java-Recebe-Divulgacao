package view;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.FiltroDAO;
import dao.TopicoDAO;
import dto.FiltroDTO;
import dto.TopicoDTO;
//import pub_sub.Publisher;

public class SubscribeView extends JFrame  {

	private JPanel contentPane;
	private JTextField textValorMin, textValorMax;
	private JComboBox jcombobox, jcbbAcoes;
//	//objeto para executar sql de insert no bd
	TopicoDAO topicoDAO = new TopicoDAO();
//	//cria objeto do tipo TopicoDTO com o nome do topico passado
	TopicoDTO topicoDTO;
	
	/**
	 * Create the frame.
	 */
	public SubscribeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tópico:");
		lblNewLabel.setBounds(12, 15, 322, 15);
		contentPane.add(lblNewLabel);
		
		jcombobox = new JComboBox();
		/////////////////JComboBox recebe dados do BD, tabela topicos
		try {
			for(TopicoDTO t: topicoDAO.listaTopicos()) {
				jcombobox.addItem(t.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		jcombobox.setBounds(12, 42, 426, 19);
		contentPane.add(jcombobox);
		
		////////////////////////////////FILTROS/////////////////////
		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setBounds(12, 70, 322, 15);
		contentPane.add(lblFiltro);

		JLabel lblAcao = new JLabel("Ação:");
		lblAcao.setBounds(12, 90, 322, 15);
		contentPane.add(lblAcao);
		
		jcbbAcoes = new JComboBox();
		/////////////////JComboBox recebe dados do BD, tabela topicos
		try {
			for(TopicoDTO t: topicoDAO.listaTopicos()) {
				jcbbAcoes.addItem("1");
				//jcbbAcoes.addItem(t.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		jcbbAcoes.setBounds(12, 110, 426, 19);
		contentPane.add(jcbbAcoes);		
		
		
		JLabel lblValorMin = new JLabel("Valor Minimo:");
		lblValorMin.setBounds(12, 130, 322, 15);
		contentPane.add(lblValorMin);

		textValorMin = new JTextField("0");
		textValorMin.setBounds(12, 150, 426, 19);
		contentPane.add(textValorMin);
		textValorMin.setColumns(10);
		
		JLabel lblValorMax = new JLabel("Valor Máximo:");
		lblValorMax.setBounds(12, 170, 322, 15);
		contentPane.add(lblValorMax);

		textValorMax = new JTextField("0");
		textValorMax.setBounds(12, 190, 426, 19);
		contentPane.add(textValorMax);
		textValorMax.setColumns(10);	
		
		JButton btnInscrever = new JButton("Inscrever");
		
		btnInscrever.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//chamada para validar campos para aceitarem apenas número
				if( ValidarCampoNumerico(textValorMin) && ValidarCampoNumerico(textValorMax) ) { 
					System.out.println(textValorMin.getText() 
							//+ " " + 
							//topicoSelecionado + " " 
							);
				}				
				
				//////////////////////////INSERT NO FILTRO BD
				String topicoSelecionado = (String) jcombobox.getSelectedItem();
				String array[] = new String[2];
				//split para pegar id do topico
				array = topicoSelecionado.split("-");				
				int id_user = 1;
				int id_acao = 1;
				FiltroDTO filtro = new FiltroDTO(Integer.parseInt(array[0]), 
						1, 
						Integer.parseInt(jcbbAcoes.getSelectedItem().toString()),
						Double.parseDouble(textValorMin.getText()), 
						Double.parseDouble(textValorMax.getText()));
				
				//objeto para executar sql de insert no bd
				FiltroDAO filtroDAO = new FiltroDAO();
				//metodo para realizar insert no BD
				filtroDAO.insert(filtro);
				
				Main frame = new Main();
				frame.setVisible(true);
				dispose();				
				
			}
		});		

		btnInscrever.setBounds(242, 230, 170, 25);
		contentPane.add(btnInscrever);
		
		JButton btnCancelar = new JButton("Cancelar");
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main frame = new Main();
				frame.setVisible(true);
				dispose();
			}
		});				
		
		btnCancelar.setBounds(53, 230, 114, 25);
		contentPane.add(btnCancelar);
	}	
		
	//Método para validar JTextField como número
	public boolean ValidarCampoNumerico(JTextField TextoCampo) {
		Double valor;
		if (TextoCampo.getText().length() != 0){
			try {
				valor = Double.parseDouble(TextoCampo.getText());
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "Digite um valor válido!" ,"Erro de valor",
						JOptionPane.INFORMATION_MESSAGE);
				TextoCampo.grabFocus();
				return false;
			}
		}else {
			JOptionPane.showMessageDialog(null, "Digite um número" ,"Erro de valor",
					JOptionPane.INFORMATION_MESSAGE);
			TextoCampo.grabFocus();			
			return false;
		}
		return true;
	}	
	
	
}
