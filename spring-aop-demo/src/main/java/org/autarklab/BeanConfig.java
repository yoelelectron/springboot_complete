package org.autarklab;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "org.autarklab")
@EnableAspectJAutoProxy
public class BeanConfig {
}
