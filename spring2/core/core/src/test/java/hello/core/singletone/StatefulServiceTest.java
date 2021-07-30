package hello.core.singletone;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        
        int priceA = statefulService1.order("userA", 10000);
        int priceB = statefulService2.order("userB", 20000);

        System.out.println("price = " + priceA);

        assertThat(priceB).isEqualTo(20000);
    }
    
    static class TestConfig{
        
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}