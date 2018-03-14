/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;


/**
 *
 * @author oleksandr.antsyferov
 */

public class SellerAgent extends SelfReportingAgent{

    protected void setup() {
        super.setup();

        register("pong", "tennis");
        
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();
                if (null != message) {
                    ACLMessage response = message.createReply();
                    response.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                    response.setContent("pong");
                    send(response);
                } else {
                    block();
                }

            }
        });

        //doDelete();
    }
    
    protected void takeDown() {
        super.takeDown();
    
    }
    
}
