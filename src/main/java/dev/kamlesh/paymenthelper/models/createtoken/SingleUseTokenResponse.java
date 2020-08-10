
package dev.kamlesh.paymenthelper.models.createtoken;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SingleUseTokenResponse {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("merchantRefNum")
    @Expose
    public String merchantRefNum;
    @SerializedName("profileId")
    @Expose
    public String profileId;
    @SerializedName("timeToLiveSeconds")
    @Expose
    public Integer timeToLiveSeconds;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("singleUseCustomerToken")
    @Expose
    public String singleUseCustomerToken;
    @SerializedName("paymentTypes")
    @Expose
    public List<String> paymentTypes = null;
    @SerializedName("locale")
    @Expose
    public String locale;
    @SerializedName("firstName")
    @Expose
    public String firstName;
    @SerializedName("middleName")
    @Expose
    public String middleName;
    @SerializedName("lastName")
    @Expose
    public String lastName;
    @SerializedName("dateOfBirth")
    @Expose
    public DateOfBirth dateOfBirth;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("ip")
    @Expose
    public String ip;
    @SerializedName("nationality")
    @Expose
    public String nationality;
    @SerializedName("addresses")
    @Expose
    public List<Address> addresses = null;
    @SerializedName("paymentHandles")
    @Expose
    public List<PaymentHandle> paymentHandles = null;

}
