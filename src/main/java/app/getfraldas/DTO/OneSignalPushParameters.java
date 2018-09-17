
package app.getfraldas.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OneSignalPushParameters {

    @SerializedName("app_id")
    @Expose
    private String app_id;

    @SerializedName("included_segments")
    @Expose
    private List<String> included_segments;

    @SerializedName("contents")
    @Expose
    private Contents contents;

    public String getapp_id() {
        return app_id;
    }

    public void setapp_Id(String app_id) {
        this.app_id = app_id;
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

    public List<String> getIncluded_segments() {
        return included_segments;
    }

    public void setIncluded_segments(List<String> includedSegments) {
        this.included_segments = includedSegments;
    }
}
