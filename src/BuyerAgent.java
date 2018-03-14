/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;


/**
 *
 * @author oleksandr.antsyferov
 */

public class BuyerAgent extends SelfReportingAgent{

    protected void setup() {
        super.setup();
        
        register("ping", "tennis");

        addBehaviour(new TickerBehaviour(this, 1000) {
            @Override
            protected void onTick() {
                pingSellers();
            }
        });
        
        //doDelete();
    }
    
    protected void pingSellers()
    {
        DFAgentDescription pongerDescription = new DFAgentDescription();
        ServiceDescription pongerService = new ServiceDescription();
        pongerService.setName("pong");
        pongerDescription.addServices(pongerService);
            
        try {
            DFAgentDescription[] pongers = DFService.search(this, pongerDescription);
            ACLMessage message = new ACLMessage(ACLMessage.PROPOSE);
            message.setContent("ping");
            
            System.out.println("Found pongers:" + pongers.length);
            
            for (DFAgentDescription agentDescription : pongers) {

                message.addReceiver(agentDescription.getName());

            }
            send(message);
        } catch (Exception e) {

        }

    }
    
    protected void takeDown() {
        super.takeDown();
    
    }
   
   
}
