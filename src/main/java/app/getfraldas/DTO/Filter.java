
package app.getfraldas.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filter {

    @SerializedName("field")
    @Expose
    private String field;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("relation")
    @Expose
    private String relation;
    @SerializedName("value")
    @Expose
    private String value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
