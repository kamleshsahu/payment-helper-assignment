
package dev.kamlesh.paymenthelper.models.createtoken;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Card {

    @SerializedName("lastDigits")
    @Expose
    public String lastDigits;
    @SerializedName("cardExpiry")
    @Expose
    public CardExpiry cardExpiry;
    @SerializedName("cardBin")
    @Expose
    public String cardBin;
    @SerializedName("cardType")
    @Expose
    public String cardType;
    @SerializedName("holderName")
    @Expose
    public String holderName;

}
