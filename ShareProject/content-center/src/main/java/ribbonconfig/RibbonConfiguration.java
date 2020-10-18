package ribbonconfig;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon配置类，采用随机规则
 */

@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }

}
