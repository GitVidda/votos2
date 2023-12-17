package com.example.votos.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Configuración para permitir solicitudes Cross-Origin Resource Sharing (CORS) en la aplicación.
@Configuration
public class WebCfg implements WebMvcConfigurer {
    //Configura las políticas de CORS para permitir el acceso a los recursos desde orígenes diferentes.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Define un mapeo global para todas las rutas.
                .allowedOrigins("*") // Permite solicitudes desde cualquier origen.
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite los métodos HTTP especificados.
                .allowedHeaders("*") // Permite todas las cabeceras en las solicitudes.
                .allowCredentials(false) // No permite el envío de credenciales (por ejemplo, cookies).
                .maxAge(3600); // Define el tiempo máximo de caché para las opciones preflight (en segundos).
    }
}
