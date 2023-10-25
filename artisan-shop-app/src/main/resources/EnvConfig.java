import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {

    @Value("${SPRING_DATASOURCE_URL}")
    private String dataSourceUrl;

    @Value("${SPRING_DATASOURCE_USERNAME}")
    private String dataSourceUsername;

    @Value("${SPRING_DATASOURCE_PASSWORD}")
    private String dataSourcePassword;

    @Value("${SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT}")
    private String hibernateDialect;

    @Value("${SPRING_JPA_SHOW_SQL}")
    private boolean showSql;

    @Value("${SPRING_JPA_HIBERNATE_DDL_AUTO}")
    private String ddlAuto;

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                .directory(".")
                .load();
    }
}
