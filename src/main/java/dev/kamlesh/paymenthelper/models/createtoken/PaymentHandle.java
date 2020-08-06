
package dev.kamlesh.paymenthelper.models.createtoken;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentHandle {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("usage")
    @Expose
    public String usage;
    @SerializedName("paymentType")
    @Expose
    public String paymentType;
    @SerializedName("paymentHandleToken")
    @Expose
    public String paymentHandleToken;
    @SerializedName("billingDetailsId")
    @Expose
    public String billingDetailsId;
    @SerializedName("card")
    @Expose
    public Card card;

}
