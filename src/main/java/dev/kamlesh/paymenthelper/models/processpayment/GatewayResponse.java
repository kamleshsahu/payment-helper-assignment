package dev.kamlesh.paymenthelper.models.processpayment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GatewayResponse {

    @SerializedName("authCode")
    @Expose
    public String authCode;
    @SerializedName("avsResponse")
    @Expose
    public String avsResponse;
    @SerializedName("cvvVerification")
    @Expose
    public String cvvVerification;

}
