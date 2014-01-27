import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

public class Utils {
	public static String toStringFromGetters(Object o) {
		ToStringHelper helper = Objects.toStringHelper(o);
		Class<?> c = o.getClass();
		
		for(Method method : c.getMethods()) {
			if(method.getName().startsWith("get")
					&& !"getClass".equals(method.getName())
					&& method.getParameterTypes().length == 0
					&& !method.getReturnType().equals(Void.TYPE)) {
				try {
					helper.add(method.getName().substring(3), method.invoke(o));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
		return helper.toString();
	}
}