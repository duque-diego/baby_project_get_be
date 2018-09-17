
package app.getfraldas.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contents {

    @SerializedName("en")
    @Expose
    private String en;

    public String getEn() {
        return en;
    }

    public void setEn(String pt) {
        this.en = pt;
    }

}
