package app.getfraldas.DTO;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fprado on 22/08/18
 */

@Entity
public class UsuarioPushAppControleDTO implements Serializable {

    @Index
    private boolean pushTerminated;
    private List<Filter> filterList;

    @Id
    private Long id;

    public boolean isPushTerminated(boolean b) {
        return pushTerminated;
    }

    public void setPushTerminated(boolean pushTerminated) {
        this.pushTerminated = pushTerminated;
    }

    public List<Filter> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<Filter> filterList) {
        this.filterList = filterList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
