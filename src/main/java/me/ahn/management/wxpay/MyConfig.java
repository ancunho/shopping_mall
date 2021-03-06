package me.ahn.management.wxpay;


import me.ahn.management.util.PropertiesUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MyConfig extends WXPayConfig {

    private byte[] certData;

    @Override
    public String getAppID() {
        return PropertiesUtil.getProperty("stronghold.miniAppId");
    }

    @Override
    public String getMchID() {
        return PropertiesUtil.getProperty("stronghold.mchId");
    }

    @Override
    public String getKey() {
        return PropertiesUtil.getProperty("stronghold.miniAppSecret");
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo("api.mch.weixin.qq.com", false);
            }
        };

    }
}
