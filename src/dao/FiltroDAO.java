package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import configs.ConexaoUtil;
import dto.FiltroDTO;


public class FiltroDAO {

	//inserir topicos no BD
	public void insert(FiltroDTO filtroDTO) {
		
		try {
			// ativa conexão com BD
			Connection connection = ConexaoUtil.getInstance().getConnection();
			// código sql a ser executado
			// o ? será trocado, em tempo de execução, pelo valor a ser inserido no BD
			String sql = "INSERT INTO filtro (id_acao, id_user, id_topico, valor_min, valor_max"
					+ ") VALUES (?, ?, ?, ?, ?)";
			// realiza uma ponte entre o java e o BD
			PreparedStatement statement = connection.prepareStatement(sql);
			// faz a alteração do ? da variavel sql para o valor a ser passado para o insert
			// primeiro parâmetro indica qual o ponto de interrogação será alterado (1 é o
			// primeiro, 2 é o segundo...)
			// segundo parâmetro é o valor a ser inserido
			statement.setInt(1, filtroDTO.getId_acao());
			statement.setInt(2, filtroDTO.getId_user());
			statement.setInt(3, filtroDTO.getId_topico());
			statement.setDouble(4, filtroDTO.getValor_min());
			statement.setDouble(5, filtroDTO.getValor_max());
			// Executar o comando sql com os devidos valores
			statement.execute();
			// fechar conexao com bd
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	//Recupera filtro do BD
    public FiltroDTO selectFiltro(int id_topico){
    	PreparedStatement st;
    	FiltroDTO filtro = null;

    	try {   
			// ativa conexão com BD
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
    		st = connection.prepareStatement("SELECT * FROM filtro WHERE id=? limit 1");
    		
    		st.setInt(1, id_topico);            
    		try (ResultSet res = st.executeQuery()) {
    			if (res.next())
    			{
    				filtro = new FiltroDTO(res.getInt("id_topico"), res.getInt("id_user"),
    						res.getInt("id_acao"), res.getDouble("valor_min"), res.getDouble("valor_max"));
    				
    			}
    		}
    		st.close();            
    		return filtro;
    	} catch (SQLException | ClassNotFoundException ex) {
    		Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
    	}    	
    	
    	return filtro;

    }		
	
}
