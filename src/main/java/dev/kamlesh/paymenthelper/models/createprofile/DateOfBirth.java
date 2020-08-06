package dev.kamlesh.paymenthelper.models.createprofile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DateOfBirth {

    @SerializedName("year")
    @Expose
    public Integer year;
    @SerializedName("month")
    @Expose
    public Integer month;
    @SerializedName("day")
    @Expose
    public Integer day;

}
