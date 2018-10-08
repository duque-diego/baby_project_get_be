package app.getfraldas.DTO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OneSignalPushParameters {

    @SerializedName("app_id")
    @Expose
    private String app_id;
    @SerializedName("filters")
    @Expose
    private List<Filter> filters = null;
    @SerializedName("contents")
    @Expose
    private Contents contents;

    public String getapp_id() {
        return app_id;
    }

    public void setapp_Id(String app_id) {
        this.app_id = app_id;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

}
