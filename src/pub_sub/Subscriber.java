package pub_sub;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Subscriber  {
	
	public void startListener() throws Exception {
		
		Connection connection = null;
		
        // Producer (produtor da mensagem)
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://localhost:61616");
        
        //cria conexão
        connection = connectionFactory.createConnection();
        //cria sessão
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);        
		
        //cria tópico para enviar mensagem nele
        Topic topic = session.createTopic("produto");    
        
        // Consumer1 subscribes to customerTopic
        MessageConsumer consumer1 = session.createConsumer(topic);
        //Listener para "ouvir" envento para receber mensagem
        consumer1.setMessageListener(new ConsumerMessageListener("Consumer1"));        
        
        //inicia conexão para envio de mensagem
        connection.start(); 		
		
	}

}
