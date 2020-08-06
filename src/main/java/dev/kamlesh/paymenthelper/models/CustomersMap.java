package dev.kamlesh.paymenthelper.models;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@DynamoDBTable(tableName = "CustomerIdMap")
public
class CustomersMap {
    @DynamoDBHashKey(attributeName = "emailId")
    private String emailId;
    @DynamoDBAttribute
    private String customerId;

    public CustomersMap() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
