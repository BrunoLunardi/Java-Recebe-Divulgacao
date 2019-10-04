package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import configs.ConexaoUtil;
import dto.FiltroDTO;
import dto.TopicoDTO;

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
	
}
