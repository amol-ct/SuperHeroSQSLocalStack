package com.cleartax.training_superheroes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqsConfig {

    @Value("${sqs.queue.name}")
    private String queueName;

    @Value("${sqs.queue.url}")
    private String queueUrl;

    @Value("${sqs.queue.region}")
    private String region;

    @Value("${sqs.queue.access-key}")
    private String accessKey;

    @Value("${sqs.queue.secret-key}")
    private String secretKey;

    @Value("${sqs.queue.session-token}")
    private String sessionToken;

    public SqsConfig() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SqsConfig;
    }

    public String getQueueName() {
        return this.queueName;
    }

    public String getQueueUrl() {
        return this.queueUrl;
    }

    public String getRegion() {
        return this.region;
    }

    public String getAccessKey() {
        return this.accessKey;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public String getSessionToken() {
        return this.sessionToken;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public void setQueueUrl(String queueUrl) {
        this.queueUrl = queueUrl;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SqsConfig)) return false;
        final SqsConfig other = (SqsConfig) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$queueName = this.getQueueName();
        final Object other$queueName = other.getQueueName();
        if (this$queueName == null ? other$queueName != null : !this$queueName.equals(other$queueName)) return false;
        final Object this$queueUrl = this.getQueueUrl();
        final Object other$queueUrl = other.getQueueUrl();
        if (this$queueUrl == null ? other$queueUrl != null : !this$queueUrl.equals(other$queueUrl)) return false;
        final Object this$region = this.getRegion();
        final Object other$region = other.getRegion();
        if (this$region == null ? other$region != null : !this$region.equals(other$region)) return false;
        final Object this$accessKey = this.getAccessKey();
        final Object other$accessKey = other.getAccessKey();
        if (this$accessKey == null ? other$accessKey != null : !this$accessKey.equals(other$accessKey)) return false;
        final Object this$secretKey = this.getSecretKey();
        final Object other$secretKey = other.getSecretKey();
        if (this$secretKey == null ? other$secretKey != null : !this$secretKey.equals(other$secretKey)) return false;
        final Object this$sessionToken = this.getSessionToken();
        final Object other$sessionToken = other.getSessionToken();
        if (this$sessionToken == null ? other$sessionToken != null : !this$sessionToken.equals(other$sessionToken))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $queueName = this.getQueueName();
        result = result * PRIME + ($queueName == null ? 43 : $queueName.hashCode());
        final Object $queueUrl = this.getQueueUrl();
        result = result * PRIME + ($queueUrl == null ? 43 : $queueUrl.hashCode());
        final Object $region = this.getRegion();
        result = result * PRIME + ($region == null ? 43 : $region.hashCode());
        final Object $accessKey = this.getAccessKey();
        result = result * PRIME + ($accessKey == null ? 43 : $accessKey.hashCode());
        final Object $secretKey = this.getSecretKey();
        result = result * PRIME + ($secretKey == null ? 43 : $secretKey.hashCode());
        final Object $sessionToken = this.getSessionToken();
        result = result * PRIME + ($sessionToken == null ? 43 : $sessionToken.hashCode());
        return result;
    }

    public String toString() {
        return "SqsConfig(queueName=" + this.getQueueName() + ", queueUrl=" + this.getQueueUrl() + ", region=" + this.getRegion() + ", accessKey=" + this.getAccessKey() + ", secretKey=" + this.getSecretKey() + ", sessionToken=" + this.getSessionToken() + ")";
    }
}
