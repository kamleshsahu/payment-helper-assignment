
package dev.kamlesh.paymenthelper.models.createtoken;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DateOfBirth {

    @SerializedName("day")
    @Expose
    public Integer day;
    @SerializedName("month")
    @Expose
    public Integer month;
    @SerializedName("year")
    @Expose
    public Integer year;

}
