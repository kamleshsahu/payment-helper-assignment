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
public class SingleUseTokenRequest {

    @SerializedName("merchantRefNum")
    @Expose
    public String merchantRefNum;
    @SerializedName("paymentTypes")
    @Expose
    public List<String> paymentTypes = null;

}