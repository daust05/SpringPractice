package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletoneBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Singleton.class);

        Singleton singletonBean1 = ac.getBean(Singleton.class);
        Singleton singletonBean2 = ac.getBean(Singleton.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);

        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    }

    @Scope("singleton")
    static class Singleton {
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }
        @PreDestroy
        public void close(){
            System.out.println("SingletonBean.close");
        }
    }
}
