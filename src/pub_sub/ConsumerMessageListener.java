package pub_sub;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.swing.JOptionPane;

import dao.FiltroDAO;
import dto.FiltroDTO;


public class ConsumerMessageListener implements MessageListener {
    private String consumerName;
    public ConsumerMessageListener(String consumerName) {
        this.consumerName = consumerName;
    }
 
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        	//busca filtro no BD
            try {
            	FiltroDAO selectfiltro = new FiltroDAO();
            	FiltroDTO filtro = selectfiltro.selectFiltro(1);
            	
            	///Filtra pela propriedade
                System.out.println(consumerName + " recebido " +  textMessage.getStringProperty("valor") + " " + textMessage.getStringProperty("id_acao")
                );            	
            	
                JOptionPane.showMessageDialog(null, textMessage.getStringProperty("valor") + " " + textMessage.getStringProperty("id_acao")                		
                		);            	
                System.out.println(filtro.getValor_min());
                
                if(
                	( Double.parseDouble(textMessage.getStringProperty("valor")) >= filtro.getValor_min() )
                		&&
                	( Double.parseDouble(textMessage.getStringProperty("valor")) <= filtro.getValor_max() )
                		&&
                	( Integer.parseInt(textMessage.getStringProperty("id_acao")) == filtro.getId_acao() )
                )
                {
                	///Filtra pela propriedade
                    System.out.println(consumerName + " received " + textMessage.getText()
                    + " "+ textMessage.getStringProperty("valor"));
                    JOptionPane.showMessageDialog(null, textMessage.getText());            	
                    System.out.println(filtro.getValor_min());
                }
            } catch (JMSException e) {
    			e.printStackTrace();
    		}
           
    }
 
}