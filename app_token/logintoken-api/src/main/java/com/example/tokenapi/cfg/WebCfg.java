package com.example.tokenapi.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Clase que configura las políticas de Cross-Origin Resource Sharing (CORS) que es una medida de seguridad en los
// navegadores web que controla cómo las aplicaciones web en un dominio acceden a los recursos en otro dominio.
@Configuration  // La clase se marca de configuración que contiene métodos que se ejecutan al configurar la aplicación.
public class WebCfg implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Define la ruta de mapeo CORS en este caso aplica a todas las rutas de la aplicación.
                .allowedOrigins("*")    // Permite todas las solicitudes de cualquier origen. Cualquier dominio puede acceder a los recursos de esta aplicación.
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Especifica los métodos permitidos en las solicitudes CORS.
                .allowedHeaders("*")    // Permite que se envíen todos los encabezados en las solicitudes CORS.
                .allowCredentials(false)    // Indica que las solicitudes no pueden incluir credenciales como (cookies o encabezados de autenticación).
                .maxAge(3600);  // Tiempo máximo que una respuesta CORS se puede almacenar en caché en el navegador.
    }
}
