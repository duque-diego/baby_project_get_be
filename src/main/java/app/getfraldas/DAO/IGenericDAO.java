package app.getfraldas.DAO;


import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.Key;




import java.util.List;
import java.util.Map;

public interface IGenericDAO<T> {

    Key<T> put(T entity);
    Map<Key<T>, T> putAll(Iterable<T> entities);
    void delete(T entity);
    void deleteKey(Key<T> entityKey);
    void deleteAll(Iterable<T> entities);
    void deleteKeys(Iterable<Key<T>> keys);
    T get(Long id) throws EntityNotFoundException;
    T get(Key<T> key) throws EntityNotFoundException;
    T get(String id) throws EntityNotFoundException;
    List<T> listByFilter(Query.Filter filtro);
    List<T> listAll();
    QueryResultIterator<T> iterator();

    /**
     * Convenience method to get all objects matching a single property
     *
     * @param propName
     * @param propValue
     * @return T matching Object
     */
    T getByProperty(String propName, Object propValue);
    List<T> listByProperty(String propName, Object propValue);
    List<Key<T>> listKeysByProperty(String propName, Object propValue);
}

