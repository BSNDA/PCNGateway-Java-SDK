package com.bsnbase.sdk.util.common;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public abstract class HTTPSClient extends HttpClientBuilder {
    private CloseableHttpClient client;
    protected ConnectionSocketFactory connectionSocketFactory;

    /**
     * Initialize HTTPSClient
     *
     * @return return to the current instance
     * @throws Exception
     */
    public CloseableHttpClient init(String keystorePath) throws Exception {

        this.prepareCertificate(keystorePath);
        this.regist();

        return this.client;
    }

    /**
     * prepare for cert registration
     *
     * @throws Exception
     */
    public abstract void prepareCertificate(String keystorePath) throws Exception;

    /**
     * register protocol and interface, this method can be reeritten in sub-class. 
     */
    protected void regist() {
        // Setup the object that handles the socket connection factory corresponding to the protocol HTTP and HTTPS 
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", this.connectionSocketFactory)
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);

        // create a custim httpclient object 
        this.client = HttpClients.custom().setConnectionManager(connManager).build();
    }
}