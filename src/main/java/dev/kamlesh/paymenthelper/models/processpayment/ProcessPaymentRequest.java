package dev.kamlesh.paymenthelper.models.processpayment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProcessPaymentRequest {

    @SerializedName("merchantRefNum")
    @Expose
    public String merchantRefNum;
    @SerializedName("amount")
    @Expose
    public Integer amount;
    @SerializedName("currencyCode")
    @Expose
    public String currencyCode;
    @SerializedName("dupCheck")
    @Expose
    public Boolean dupCheck;
    @SerializedName("settleWithAuth")
    @Expose
    public Boolean settleWithAuth;
    @SerializedName("paymentHandleToken")
    @Expose
    public String paymentHandleToken;
//    @SerializedName("customerIp")
//    @Expose
//    public String customerIp;
//    @SerializedName("description")
//    @Expose
//    public String description;
    @SerializedName("merchantCustomerId")
    @Expose
    public String merchantCustomerId;

}