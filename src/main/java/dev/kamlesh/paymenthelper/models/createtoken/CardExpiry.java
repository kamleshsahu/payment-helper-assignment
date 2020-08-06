
package dev.kamlesh.paymenthelper.models.createtoken;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardExpiry {

    @SerializedName("month")
    @Expose
    public String month;
    @SerializedName("year")
    @Expose
    public String year;

}
