package server.start;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("server")
@SpringBootApplication
public class StartServer {
    @Bean
    public SessionFactory createSessionFactory() {
        SessionFactory sessionFactory = null;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            StandardServiceRegistryBuilder.destroy(registry);
        }

        return sessionFactory;
    }
    public static void main(String[] args){
        SpringApplication.run(StartServer.class, args);
    }
}
