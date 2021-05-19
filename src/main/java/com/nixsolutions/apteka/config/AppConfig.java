package com.nixsolutions.apteka.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan({"com.nixsolutions.apteka"})
public class AppConfig {

}
