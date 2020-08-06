package dev.kamlesh.paymenthelper.models.createprofile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateProfileRequest {

    @SerializedName("merchantCustomerId")
    @Expose
    public String merchantCustomerId;
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
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("nationality")
    @Expose
    public String nationality;
    @SerializedName("cellPhone")
    @Expose
    public String cellPhone;

}

