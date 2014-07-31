package de.regis;

import de.regis.job.AddressExtractorJob;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.ExecutorService;

/**
 * @author danix
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "de.regis")
@EnableBatchProcessing
@Profile("production")
@ConfigurationProperties
@EnableAsync
public class Main {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;


    @Bean
    protected AddressExtractorJob addressExtractorJob() {
        return new AddressExtractorJob();
    }

    @Bean
    protected Tasklet tasklet() {
        return (contribution, context) -> {
            addressExtractorJob().run();
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Job job() throws Exception {
        return this.jobs.get("job").start(step1()).build();
    }

    @Bean
    protected Step step1() throws Exception {
        return this.steps.get("step1").tasklet(tasklet()).build();
    }


    @Bean
    public ExecutorService executorService() throws Exception {
        ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean = new ThreadPoolExecutorFactoryBean();
        threadPoolExecutorFactoryBean.setCorePoolSize(5);
        threadPoolExecutorFactoryBean.setMaxPoolSize(10);
        return threadPoolExecutorFactoryBean.getObject();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}
