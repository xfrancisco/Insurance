package org.mfi.aspects;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.inject.Inject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.mfi.annotations.NameSetter;
import org.mfi.service.info.IPersonInfo;
import org.mfi.webservices.ResponseWrapper;

/*@Aspect
@Component*/
public class NameSetterAspect {

	@Inject
	private IPersonInfo personInfo;

	@AfterReturning(pointcut = "execution(* org.mfi.webservices.*.*(..))", returning = "result")
	public void setNames(JoinPoint joinPoint, Object result) {
		ResponseWrapper<?> responseWrapper = (ResponseWrapper<?>) result;
		Object data = responseWrapper.getData();
		inspect(data);
	}

	private void inspect(Object o) {

		if (o instanceof List) {
			inspectList((List<?>) o);
		}
		for (Field field : o.getClass().getDeclaredFields()) {
			String fieldName = firstLetterUp(field.getName());
			try {
				if (field.isAnnotationPresent(NameSetter.class)) {
					set(o, fieldName);
				} else {
					Method method = o.getClass().getMethod("get" + fieldName);
					inspect(method.invoke(o));
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}

	}

	private void inspectList(List<?> list) {
		for (Object object : list) {
			inspect(object);
		}
	}

	private void set(Object object, String fieldName) {
		try {
			Method method = object.getClass().getMethod("get" + fieldName);
			long numcli = (Long) method.invoke(object);
			method = object.getClass().getMethod("set" + fieldName + "Label", new Class[] { String.class });
			String name = null;
			try {
				Class<?> finderClass = Class.forName("org.mfi.aspects.SetterMainClass");
				Object setterClassObject = finderClass.newInstance();
				Method finderClassServiceSetterMethodObject = finderClass.getMethod("setPersonInfo", IPersonInfo.class);
				finderClassServiceSetterMethodObject.invoke(setterClassObject, personInfo);
				Method finderMethodObject = finderClass.getMethod("getPersonLabel", Long.class);
				name = (String) finderMethodObject.invoke(setterClassObject, numcli);

			} catch (Exception e) {
				e.printStackTrace();
			}
			method.invoke(object, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String firstLetterUp(String string) {
		char first = Character.toUpperCase(string.charAt(0));
		string = first + string.substring(1);
		return string;
	}
}
