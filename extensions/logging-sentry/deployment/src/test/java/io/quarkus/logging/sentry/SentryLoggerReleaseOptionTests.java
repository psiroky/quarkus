package io.quarkus.logging.sentry;

import static io.quarkus.logging.sentry.SentryLoggerTest.getSentryHandler;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.logging.Handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusUnitTest;
import io.sentry.Sentry;

public class SentryLoggerReleaseOptionTests {

    @RegisterExtension
    static final QuarkusUnitTest config = new QuarkusUnitTest()
            .setAllowTestClassOutsideDeployment(true)
            .withConfigurationResource("application-sentry-logger-release-option.properties");

    @Test
    public void sentryLoggerEnvironmentOptionTest() {
        final Handler sentryHandler = getSentryHandler();
        assertThat(sentryHandler).isNotNull();
        assertThat(sentryHandler).extracting("options").extracting("release").isEqualTo("releaseABC");
        assertThat(Sentry.isEnabled()).isTrue();
    }

}
