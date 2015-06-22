package org.mfi.common.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.inject.Inject;

import org.mfi.annotations.NameSetter;
import org.mfi.common.IUtilService;
import org.mfi.service.info.IPersonInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class UtilService implements IUtilService {

	@Inject
	private IPersonInfo personInfo;

	@Override
	public <T> T setNames(T object) {
		inspect(object);
		return object;
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
			String name = personInfo.getPersonLabel(numcli);
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
