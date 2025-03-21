package com.alchives.alchiveserver.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@PropertySource("classpath:mail/emailconfig.properties")
public class MailConfig implements ApplicationContextAware, EnvironmentAware {
  public  static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";
  private static final String JAVA_MAIL_FILE          = "classpath:mail/javamail.properties";
  private static final String HOST                    = "mail.server.host";
  private static final String PORT                    = "mail.server.port";
  private static final String PROTOCOL                = "mail.server.protocol";
  private static final String USERNAME                = "mail.server.username";
  private static final String PASSWORD                = "mail.server.password";

  private ApplicationContext applicationContext;
  private Environment env;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Override
  public void setEnvironment(Environment env) {
    this.env = env;
  }
  
  /*
   * SPRING + JAVAMAIL: JavaMailSender instance, configured via .properties files.
   */
  @Bean
  public JavaMailSender mailSender() throws IOException {
    final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    // Basic mail sender configuration, based on emailconfig.properties
    mailSender.setHost(this.env.getProperty(HOST));
    mailSender.setPort(Integer.parseInt(this.env.getProperty(PORT)));
    mailSender.setProtocol(this.env.getProperty(PROTOCOL));
    mailSender.setUsername(this.env.getProperty(USERNAME));
    mailSender.setPassword(this.env.getProperty(PASSWORD));
  

    // JavaMail-specific mail sender configuration, based on javamail.properties
    final Properties javaMailProperties = new Properties();
    javaMailProperties.setProperty("mail.smtp.ssl.enable", "true");
    javaMailProperties.load(this.applicationContext.getResource(JAVA_MAIL_FILE).getInputStream());
    mailSender.setJavaMailProperties(javaMailProperties);

    return mailSender;
  }

  @Bean
  public ResourceBundleMessageSource emailMessageSource() {
    final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("mail/MailMessages");
    return messageSource;
  }

  @Qualifier("email")
  @Bean
  public TemplateEngine emailTemplateEngine() {
    final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    // Resolver for HTML emails (except the editable one)
    templateEngine.addTemplateResolver(htmlTemplateResolver());
    // Message source, internationalization specific to emails
    templateEngine.setTemplateEngineMessageSource(emailMessageSource());
    return templateEngine;
  }
  
  private ITemplateResolver htmlTemplateResolver() {
    final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setOrder(Integer.valueOf(2));
    templateResolver.setPrefix("templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
    templateResolver.setCacheable(false);
    return templateResolver;
  }
}
