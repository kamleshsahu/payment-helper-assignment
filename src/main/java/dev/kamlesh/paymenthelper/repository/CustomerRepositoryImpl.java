package dev.kamlesh.paymenthelper.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dev.kamlesh.paymenthelper.models.CustomersMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{
    private final DynamoDBMapper mapper;

    @Autowired
    public CustomerRepositoryImpl(DynamoDBMapper dynamoDBMapper) {
        this.mapper=dynamoDBMapper;
    }

    @Override
    public String getPaySafeCustomerId(String id) {
        CustomersMap item =  mapper.load(CustomersMap.class,id);
        if(item!=null){
            return item.getCustomerId();
        }
        return null;
    }

    @Override
    public void addPaySafeCustomerId(String id, String paysafeCustomerId) {
        CustomersMap item = new CustomersMap();
        item.setCustomerId(paysafeCustomerId);
        item.setEmailId(id);
        mapper.save(item);
    }
}
