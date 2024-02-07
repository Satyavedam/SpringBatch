package springBatch.example.SpringBatchdemo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import springBatch.example.SpringBatchdemo.entity.Manager;
import springBatch.example.SpringBatchdemo.repository.ManagerRepo;



@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ManagerRepo managerRepo;

    @Bean
    public FlatFileItemReader<Manager> reader() {
        FlatFileItemReader<Manager> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new ClassPathResource("ManagerDetails.csv"));
        itemReader.setName("BatchReader");
        itemReader.setLinesToSkip(1); // Skip header line
        itemReader.setLineMapper(lineMapper());
        
        // Set reader to non-strict mode
        itemReader.setStrict(false);

        return itemReader;
    }


    private LineMapper<Manager> lineMapper() {
        DefaultLineMapper<Manager> lineMapper = new DefaultLineMapper<>(); // extract values from the csv file

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("ManagerId","ManagerName","Email");

        BeanWrapperFieldSetMapper<Manager> fieldSetMapper = new BeanWrapperFieldSetMapper<>(); // it will map from target class which is employee
        fieldSetMapper.setTargetType(Manager.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

    @Bean
    public ManagerProcessor processor() {
        return new ManagerProcessor();
    }

    @Bean
    public RepositoryItemWriter<Manager> writer() {
        RepositoryItemWriter<Manager> writer = new RepositoryItemWriter<>();
        writer.setRepository(managerRepo);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("csv-step").<Manager, Manager>chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job runJob() {
        return jobBuilderFactory.get("importManager")
                .flow(step1()).end().build();
    }
}
