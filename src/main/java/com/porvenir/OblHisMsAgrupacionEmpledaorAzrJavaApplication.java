
package com.porvenir;

//import com.porvenir.log.config.ConfigEventHub;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.context.annotation.Import;

//@EnableCaching | Se puede habilitar en caso de que el servicio requiera cache
//@EnableScheduling | Se puede habilitar en caso de que el servicio requiera cache
//@Import(ConfigEventHub.class)
@EnableFeignClients
@SpringBootApplication
public class OblHisMsAgrupacionEmpledaorAzrJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(OblHisMsAgrupacionEmpledaorAzrJavaApplication.class, args);
    }
}
