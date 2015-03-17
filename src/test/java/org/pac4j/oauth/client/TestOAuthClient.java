package org.pac4j.oauth.client;

import org.pac4j.core.client.BaseClient;
import org.pac4j.core.client.Protocol;
import org.pac4j.core.client.TestClient;
import org.pac4j.core.util.TestsHelper;

@SuppressWarnings("rawtypes")
public abstract class TestOAuthClient extends TestClient {

    private static final int CONNECT_TIMEOUT = 135;

    private static final int READ_TIMEOUT = 2896;

    private static final String PROXY_HOST = "proxyHost";

    private static final int PROXY_PORT = 12345;

    public void testMissingKey() {
        final BaseOAuthClient client = (BaseOAuthClient) getClient();
        client.setKey(null);
        TestsHelper.initShouldFail(client, "key cannot be blank");
    }

    public void testMissingSecret() {
        final BaseOAuthClient client = (BaseOAuthClient) getClient();
        client.setSecret(null);
        TestsHelper.initShouldFail(client, "secret cannot be blank");
    }

    @Override
    protected BaseClient internalTestClone(final BaseClient oldBaseClient) {
        BaseOAuthClient oldClient = (BaseOAuthClient) oldBaseClient;
        oldClient.setKey(KEY);
        oldClient.setSecret(SECRET);
        oldClient.setConnectTimeout(CONNECT_TIMEOUT);
        oldClient.setReadTimeout(READ_TIMEOUT);
        oldClient.setProxyHost(PROXY_HOST);
        oldClient.setProxyPort(PROXY_PORT);
        BaseOAuthClient client = (BaseOAuthClient) super.internalTestClone(oldClient);
        assertEquals(oldClient.getKey(), client.getKey());
        assertEquals(oldClient.getSecret(), client.getSecret());
        assertEquals(oldClient.getConnectTimeout(), client.getConnectTimeout());
        assertEquals(oldClient.getReadTimeout(), client.getReadTimeout());
        assertEquals(oldClient.getProxyHost(), client.getProxyHost());
        assertEquals(oldClient.getProxyPort(), client.getProxyPort());
        return client;
    }

    public void testClone() {
        internalTestClone((BaseClient) getClient());
    }

    @Override
    protected Protocol getProtocol() {
        return Protocol.OAUTH;
    }
}
