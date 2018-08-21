package app.getfraldas;

import org.springframework.core.annotation.Order;

import javax.servlet.annotation.WebFilter;
import javax.servlet.DispatcherType;
import com.googlecode.objectify.ObjectifyFilter;

/**
 * Created by diegods on 31/05/18.
 */

@Order(1) //optional
@WebFilter(filterName = "ObjectifyFilter", urlPatterns = "/*", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class OfyFilter extends ObjectifyFilter{
}
