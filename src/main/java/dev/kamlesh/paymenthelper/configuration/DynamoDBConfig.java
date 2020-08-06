package dev.kamlesh.paymenthelper.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {

    private final String accessKey;
    private final String secretKey;

    @Autowired
    public DynamoDBConfig(@Value("${dynamoDB.accessKey}") String accessKey,
                          @Value("${dynamoDB.secretKey}") String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(accessKey, secretKey)))
                .withRegion(Regions.US_WEST_2)
                .build();
        return new DynamoDBMapper(client, DynamoDBMapperConfig.DEFAULT);
    }
}