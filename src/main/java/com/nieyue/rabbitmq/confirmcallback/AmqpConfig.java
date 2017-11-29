package com.nieyue.rabbitmq.confirmcallback;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
/**
 * 配置
 * @author 聂跃
 * @date 2017年5月31日
 */
@Configuration  
public class AmqpConfig {
	@Value("${myPugin.rabbitmq.DIRECT_EXCHANGE}")
    public  String DIRECT_EXCHANGE ;  
	@Value("${myPugin.rabbitmq.DIRECT_ROUTINGKEY}")
    public String DIRECT_ROUTINGKEY;  
	@Value("${myPugin.rabbitmq.DIRECT_QUEUE}")
    public  String DIRECT_QUEUE; 
	
	
    @Autowired
    ConnectionFactory  connectionFactory ;
    /** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */  
    @Bean  
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  
    public RabbitTemplate rabbitTemplate() {  
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);  
        return rabbitTemplate;  
    } 
	 /** 设置交换机类型  */  
    @Bean  
    public DirectExchange DIRECTExchange() {  
        /** 
         * DirectExchange:按照routingkey分发到指定队列 
         * TopicExchange:多关键字匹配 
         * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念 
         * HeadersExchange ：通过添加属性key-value匹配 
         */  
    	DirectExchange de = new DirectExchange(DIRECT_EXCHANGE);
        return de;
    }  
    @Bean  
    public Queue DIRECTQueue() {  
        return new Queue(DIRECT_QUEUE);  
    } 
    @Bean  
    public Binding binding() {  
        /** 将队列绑定到交换机 */  
        return BindingBuilder.bind(DIRECTQueue()).to(DIRECTExchange()).with(DIRECT_ROUTINGKEY);  
    } 

}
