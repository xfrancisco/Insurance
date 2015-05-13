package org.insurance.utils;

import java.text.MessageFormat;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

@Component
public class PropertiesUtils {

	@Resource(name = "codesMessagesErrors")
	private Properties errorMessagesProperties;

	public String getMessage(String messageKey, Object[] messageArgs, String defaultMessage) {
		String message = null;
		if (!Strings.nullToEmpty(messageKey).trim().isEmpty()) {
			message = errorMessagesProperties.getProperty(messageKey);
			if (message != null && messageArgs != null) {
				message = MessageFormat.format(message, messageArgs);
			}
		}
		return message != null ? message : defaultMessage;
	}
}