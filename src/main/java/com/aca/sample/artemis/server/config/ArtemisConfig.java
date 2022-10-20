package com.aca.sample.artemis.server.config;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.core.remoting.impl.netty.NettyAcceptorFactory;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisConfigurationCustomizer;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ArtemisConfig extends Properties implements ArtemisConfigurationCustomizer {

    @Override
    public void customize(org.apache.activemq.artemis.core.config.Configuration configuration) {
        // Allow Artemis to accept tcp connections (Default port localhost:61616)
             Map<String, Object> params = GetParam();
        try {
 
            configuration.addAcceptorConfiguration(new TransportConfiguration(NettyAcceptorFactory.class.getName(), params));
            
        } catch (Exception e) {
            log.error("Cannot set host/port for Artemis Server", e);
        } 
        
        System.out.println("************************* ACTIVE APP PROPERTIES ******************************");
        System.out.println(params.toString());
        System.out.println("******************************************************************************");
    }
}
