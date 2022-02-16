package com.camunda.demo.springboot.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camunda.demo.springboot.ProcessConstants;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Component
public class SendShipGoodsAmqpAdapter implements JavaDelegate {
  private static Logger LOGGER = Logger.getAnonymousLogger();

  @Autowired
  protected RabbitTemplate rabbitTemplate;
  
  @Override
  public void execute(DelegateExecution ctx) throws Exception {
    String orderId = (String) ctx.getVariable(ProcessConstants.VAR_NAME_orderId);    
    
    String exchange = "shipping";
    String routingKey = "createShipment";
    LOGGER.info("call from send message to Ship Goods " + LocalDateTime.now());
    rabbitTemplate.convertAndSend("order_sample_queue", orderId);
  }

}
