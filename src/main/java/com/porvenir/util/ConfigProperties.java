package  com.porvenir.util;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor
@Getter
@Configuration
public class ConfigProperties{

    @Value("${own.params.parameterService}")
    private String parameterService;
}