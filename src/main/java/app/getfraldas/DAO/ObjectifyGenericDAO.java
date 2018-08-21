package app.getfraldas.DAO;

import app.getfraldas.service.OfyService;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.cmd.Query;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public abstract class ObjectifyGenericDAO<T> implements IGenericDAO<T> {

    private static final int CONSTANTE_3 = 3;

    private static final String DEVE_SER_STRING = "] deve ser String";

    private static final String RAWTYPES = "rawtypes";

    private static final int PAGE_SIZE = 1000;

    static final int BAD_MODIFIERS = Modifier.FINAL | Modifier.STATIC | Modifier.TRANSIENT;

    private Set<Key<T>> keysFinal;

    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public ObjectifyGenericDAO() {
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * We've got to get the associated domain class somehow
     *
     * @param clazz
     */
    protected ObjectifyGenericDAO(Class<T> clazz) {this.clazz = clazz;}

    @Override
    public Key<T> put(T entity) {
        return ofy().save().entity(entity).now();
    }

    @Override
    public Map<Key<T>, T> putAll(Iterable<T> entities) {
        return ofy().save().entities(entities).now();
    }

    @Override
    public void delete(T entity) {
        ofy().delete().entity(entity).now();
    }

    @Override
    public void deleteKey(Key<T> entityKey) {
        ofy().delete().entity(entityKey);
    }

    @Override
    public void deleteAll(Iterable<T> entities) {
        ofy().delete().entities(entities);
    }

    @Override
    public void deleteKeys(Iterable<Key<T>> keys) {
        ofy().delete().entities(keys);
    }

    @Override
    public T get(Long id) {
        ofy().clear();
        return ofy().transactionless().load().type(this.clazz).id(id).now();
    }

    @Override
    public T get(Key<T> key) {
        ofy().clear();
        return  ofy().transactionless().load().type(this.clazz).filterKey(key).first().now();
    }

    @Override
    public T get(String key) {
        ofy().clear();
        return  ofy().transactionless().load().type(this.clazz).filterKey(key).first().now();
    }

    /*
     * Get ALL entities of type <T> in the datastore. DANGEROUS!! INEFFICIENT!!
     * Only doing this for dev/debugging purposes.
     */
    @Override
    public List<T> listAll() {
        ofy().clear();
        return ofy().transactionless().load().type(this.clazz).list();
    }

    @Override
    public QueryResultIterator<T> iterator() {
        ofy().clear();
        return ofy().transactionless().load().type(this.clazz).iterator();
    }

    @Override
    public List<T> listByFilter(com.google.appengine.api.datastore.Query.Filter filter) {
        return ofy().transactionless().load().type(this.clazz).filter(filter).list();
    }

    /*
     * Get ALL entities of type <T> in the datastore. DANGEROUS!! INEFFICIENT!!
     * Only doing this for dev/debugging purposes.
     */
    public List<T> listAllOrderBy(String fieldName) {
        ofy().clear();
        return ofy().transactionless().load().type(this.clazz).order(fieldName).list();
    }

    /**
     * Convenience method to get all objects matching a single property
     *
     * @param propName
     * @param propValue
     * @return T matching Object
     */
    @Override
    public T getByProperty(String propName, Object propValue) {
        ofy().clear();
        return ofy().transactionless().load().type(this.clazz).filter(propName,propValue).first().now();
    }

    @Override
    public List<T> listByProperty(String propName, Object propValue) {
        ofy().clear();
        return ofy().transactionless().load().type(this.clazz).filter(propName,propValue).list();
    }

    public List<T> listByAncestor(Key<?> key) {
        ofy().clear();
        return ofy().transactionless().load().type(this.clazz).ancestor(key).list();
    }

    public int listCountByAncestor(Key<?> key) {
        ofy().clear();
        return ofy().transactionless().load().type(this.clazz).ancestor(key).keys().list().size();
    }

    public List<T> listByPropertyOrder(String propName, Object propValue, String order) {
        ofy().clear();
        return ofy().transactionless().load().type(this.clazz).filter(propName,propValue).order(order).list();
    }

    @Override
    public List<Key<T>> listKeysByProperty(String propName, Object propValue) {
        ofy().clear();
        return ofy().transactionless().load().type(this.clazz).filter(propName,propValue).keys().list();
    }

    protected List<T> asList(Iterable<T> iterable) {
        ArrayList<T> list = new ArrayList<>();
        for (T t : iterable) {
            list.add(t);
        }
        return list;
    }

    protected List<Key<T>> asKeyList(Iterable<Key<T>> iterableKeys) {
        ArrayList<Key<T>> keys = new ArrayList<>();
        for (Key<T> key : iterableKeys) {
            keys.add(key);
        }
        return keys;
    }

    protected Query<T> where(Query<T> queryP, Object... args) {
        Query<T> query = queryP;
        if (args.length % CONSTANTE_3 != 0 || args.length == 0) {
            throw new IllegalArgumentException(
                    "Numero de argumentos inválidos. Deve ser (Query{, chave, operação, valor}+).");
        }

        for (int i = 0; i < args.length; i += CONSTANTE_3) {
            String chave;
            String operacao;
            Object valor;

            if (args[i + 2] != null) {
                if (!(args[i] instanceof String)) {
                    throw new IllegalArgumentException("Chave [" + i + DEVE_SER_STRING);
                }
                if (!(args[i + 1] instanceof String)) {
                    throw new IllegalArgumentException("Operação [" + i + 1 + DEVE_SER_STRING);
                }
                if (args[i + 2] instanceof String && StringUtils.isEmpty((String) args[i + 2])) {
                    continue;
                }

                chave = (String) args[i];
                operacao = (String) args[i + 1];
                valor = args[i + 2];

                if (!StringUtils.isEmpty(operacao)) {
                    operacao = " " + operacao;
                }

                query = query.filter(chave + operacao, valor);
            }
        }

        return query;
    }

    protected Query<T> getQuery() {
        ofy().clear();
        return ofy().transactionless().load().type(this.clazz);
    }

    private List<Field> getAllFields() {
        List<Field> fieldList = new LinkedList<>();

        if (clazz == null) {
            return fieldList;
        }

        return this.getAllFields(clazz);
    }

    /**
     * Retorna all fields.
     *
     * @param classe
     *            classe
     * @return all fields
     */
    @SuppressWarnings({ "unchecked", RAWTYPES })
    private List<Field> getAllFields(Class classe) {
        List<Field> fieldList = new LinkedList<>();

        if (classe == null) {
            return fieldList;
        }

        for (Class<T> temp = classe; temp != null && !temp.equals(Object.class); temp = (Class<T>) temp
                .getSuperclass()) {
            fieldList.addAll(Arrays.asList(temp.getDeclaredFields()));
        }

        return fieldList;
    }

    public Objectify ofy() {
        return OfyService.ofy();
    }

    protected List<T> queryWithPaging(Query<T> query) {

        List<T> returnList = new ArrayList<>();
        List<T> pageList;

        int offset = 0;
        do {

            pageList = query.limit(PAGE_SIZE).offset(offset).list();
            returnList.addAll(pageList);

            offset += PAGE_SIZE;

        } while (pageList.size() == PAGE_SIZE);

        return returnList;
    }

    protected List<T> whereByAnd(Object... args) {

        ArrayList list = new ArrayList(Arrays.asList(args));
        this.keysFinal = new HashSet<>();
        List<Key<T>> listKey = new ArrayList<>();
        Query<T> query = getQuery();

        for (Object object : list) {
            if (object instanceof ObjectifyGenericDAO.TwoConditions) {
                listKey = execTwoCondition(object, query);
            } else if (object instanceof ObjectifyGenericDAO.OneConditions) {
                listKey = execOneCondition(object, query);
            }
            if(!addSet(keysFinal, listKey)) {
                break;
            }
        }
        return searchByKeys();
    }

   private boolean addSet(Set set, List<Key<T>> listKey){
        if(listKey.isEmpty()) {
            return endSearch();
        }

        if(set.isEmpty()) {
            set.addAll(listKey);
        }else{
            set.retainAll(listKey);
            if(set.isEmpty()) {
                return endSearch();
            }
        }

        return true;
   }

   private List<Key<T>> execTwoCondition(Object object, Query<T> query){
       TwoConditions twoConditions = (TwoConditions) object;
       ofy().clear();
       return query.filter(twoConditions.property + " " + twoConditions.conditionOne, twoConditions.valueOne).
               filter(twoConditions.property + " " + twoConditions.conditionTwo, twoConditions.valueTwo).keys().list();
   }

   private List<Key<T>> execOneCondition(Object object, Query<T> query){
       OneConditions oneConditions = (OneConditions) object;
       ofy().clear();
       return query.filter(oneConditions.property + " " + oneConditions.conditionOne, oneConditions.valueOne).keys().list();
   }

    private List<T> searchByKeys(){
        List<T> list = new ArrayList<>();
        if(null !=  keysFinal) {
            for (Key<T> key : keysFinal) {
                list.add(get(key));
            }
        }

        return list;
    }

    private boolean endSearch(){
        this.keysFinal = null;
        return false;
    }

    protected TwoConditions getPropertiesTwoConditions(String property, String conditionOne, Object valueOne,
                                                       String conditionTwo, Object valueTwo){
        return new TwoConditions(property, conditionOne, valueOne, conditionTwo, valueTwo);
    }

    protected OneConditions getPropertiesOneConditions(String property, String conditionOne, Object valueOne){
        return new OneConditions(property, conditionOne, valueOne);
    }


    class TwoConditions extends OneConditions {
        protected String conditionTwo;
        protected Object valueTwo;

        public TwoConditions(String property, String conditionOne, Object valueOne,
                             String conditionTwo, Object valueTwo){
            super(property, conditionOne, valueOne);
            this.conditionTwo = conditionTwo;
            this.valueTwo =valueTwo;
        }
    }

    class OneConditions {
        protected String property;
        protected String conditionOne;
        protected Object valueOne;

        public OneConditions(String property, String conditionOne, Object valueOne){
            this.property = property;
            this.conditionOne = conditionOne;
            this.valueOne = valueOne;
        }
    }

}
