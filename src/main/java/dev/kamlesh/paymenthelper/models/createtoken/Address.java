
package dev.kamlesh.paymenthelper.models.createtoken;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("nickName")
    @Expose
    public String nickName;
    @SerializedName("street")
    @Expose
    public String street;
    @SerializedName("street2")
    @Expose
    public String street2;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("zip")
    @Expose
    public String zip;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("defaultShippingAddressIndicator")
    @Expose
    public Boolean defaultShippingAddressIndicator;

}
