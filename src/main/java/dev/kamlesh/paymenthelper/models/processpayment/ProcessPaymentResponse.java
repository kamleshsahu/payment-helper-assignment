package dev.kamlesh.paymenthelper.models.processpayment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class ProcessPaymentResponse {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("amount")
    @Expose
    public Integer amount;
    @SerializedName("merchantRefNum")
    @Expose
    public String merchantRefNum;
    @SerializedName("settleWithAuth")
    @Expose
    public Boolean settleWithAuth;
    @SerializedName("paymentHandleToken")
    @Expose
    public String paymentHandleToken;
    @SerializedName("txnTime")
    @Expose
    public String txnTime;
    @SerializedName("customerIp")
    @Expose
    public String customerIp;
    @SerializedName("dupCheck")
    @Expose
    public Boolean dupCheck;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("currencyCode")
    @Expose
    public String currencyCode;
    @SerializedName("paymentType")
    @Expose
    public String paymentType;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("availableToSettle")
    @Expose
    public Integer availableToSettle;
    @SerializedName("gatewayResponse")
    @Expose
    public GatewayResponse gatewayResponse;

    @SerializedName("customerId")
    @Expose
    public String customerId;

    @SerializedName("merchantCustomerId")
    @Expose
    public String merchantCustomerId;
}