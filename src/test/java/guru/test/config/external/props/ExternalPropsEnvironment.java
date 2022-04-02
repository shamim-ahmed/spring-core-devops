package guru.test.config.external.props;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import guru.springframework.test.jms.FakeJmsBroker;

@Configuration
@PropertySource("classpath:testing.properties")
public class ExternalPropsEnvironment {
    @Autowired
    private Environment env;

    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        FakeJmsBroker broker = new FakeJmsBroker();
        broker.setUrl(env.getProperty("guru.jms.server"));
        broker.setPort(env.getRequiredProperty("guru.jms.port", Integer.class));
        broker.setUser(env.getProperty("guru.jms.user"));
        broker.setPassword(env.getProperty("guru.jms.password"));

        return broker;
    }
}
