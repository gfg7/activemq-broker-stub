/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aca.sample.artemis.server.config;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author nakon
 */
@Component
public class Properties {
    
    @Value("${spring.artemis.host:localhost}")
    private  String host;
    
    @Value("${spring.artemis.keyStorePath}")
    private  String keyStorePath;
    
    @Value("${spring.artemis.keyStorePassword}")
    private  String keyStorePassword;
    
     @Value("${spring.artemis.trustStorePath}")
    private  String trustStorePath;
    
    @Value("${spring.artemis.trustStorePassword}")
    private  String trustStorePassword;
    
    @Value("${spring.artemis.enabledProtocols}")
    private  String enabledProtocols;
    
     @Value("${spring.artemis.enabledCipherSuites}")
    private  String enabledCipherSuites;
     
    @Value("${spring.artemis.verifyHost:false}")
    private  boolean verifyHost;

    @Value("${spring.artemis.port:61616}")
    private  int port;
    
     @Value("${spring.artemis.needClientAuth:false}")
    private  boolean needClientAuth;
     
      @Value("${spring.artemis.sslEnabled:false}")
    private  boolean sslEnabled;
      
      @Value("${spring.artemis.batchDelay}")
    private Integer batchDelay;
      
      @Value("${spring.artemis.connectionsAllowed}")
    private  Integer connectionsAllowed;
      
      @Value("${spring.artemis.directDeliver:true}")
    private  boolean directDeliver;
      
      @Value("${spring.artemis.tcpNoDelay:true}")
    private  boolean tcpNoDelay;
      
      @Value("${spring.artemis.tcpReceiveBufferSize:32768}")
    private  Integer tcpReceiveBufferSize;
      
      @Value("${spring.artemis.tcpSendBufferSize:32768}")
    private  Integer tcpSendBufferSize;
      
        @Value("${spring.artemis.handshake-timeout}")
    private  Integer handshakeTimeout;
      
      @PostConstruct
       public Map GetParam(){
        Map<String, Object> params = new HashMap<>();
          
        params.put("host", host);
        params.put("port", port);
        
        if (enabledProtocols!=null && !enabledProtocols.isEmpty()){
                params.put("enabledProtocols", enabledProtocols);
        }
        if (connectionsAllowed!=null){
        params.put("connectionsAllowed", connectionsAllowed);        
        }
        
        if (handshakeTimeout!=null){
        params.put("handshake-timeout", handshakeTimeout);        
        }
        
        if (batchDelay!=null){
                params.put("batchDelay", batchDelay);   
        }

        params.put("directDeliver", directDeliver);
        params.put("tcpNoDelay", tcpNoDelay);
        params.put("tcpReceiveBufferSize", tcpReceiveBufferSize);
        params.put("tcpSendBufferSize", tcpSendBufferSize);

        if (keyStorePath!=null && keyStorePassword!=null && !(keyStorePath.isEmpty()  && keyStorePassword.isEmpty())){
            params.put("sslEnabled", sslEnabled);
            params.put("verifyHost", verifyHost);
            params.put("needClientAuth", needClientAuth);
            
            if (enabledCipherSuites!=null && !enabledCipherSuites.isEmpty()){
                    params.put("enabledCipherSuites", enabledCipherSuites);
        }
            params.put("keyStorePath", keyStorePath);
            params.put("keyStorePassword", keyStorePassword);
            
             if(trustStorePath!=null && trustStorePassword!=null && !(trustStorePath.isEmpty()  && trustStorePassword.isEmpty())){
                 
                params.put("trustStorePath", trustStorePath);
                params.put("trustStorePassword", trustStorePassword);
            }
        }
        return params;
    }
}
