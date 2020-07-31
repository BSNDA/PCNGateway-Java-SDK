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
     * 初始化HTTPSClient
     *
     * @return 返回当前实例
     * @throws Exception
     */
    public CloseableHttpClient init(String keystorePath) throws Exception {

        this.prepareCertificate(keystorePath);
        this.regist();

        return this.client;
    }

    /**
     * 准备证书验证
     *
     * @throws Exception
     */
    public abstract void prepareCertificate(String keystorePath) throws Exception;

    /**
     * 注册协议和端口, 此方法也可以被子类重写
     */
    protected void regist() {
        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", this.connectionSocketFactory)
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);

        // 创建自定义的httpclient对象
        this.client = HttpClients.custom().setConnectionManager(connManager).build();
    }
}