package com.vulog.payment.exception;

import com.vulog.payment.bean.alipay.asynch.AlipayNotification;

public class AlipayNotificationValidationException extends RuntimeException {

	private static final long serialVersionUID = -7842251223561640341L;

	private final AlipayNotification alipayNotification;
	private final String notification;

	public AlipayNotificationValidationException(String message, AlipayNotification not) {
		super(message);
		this.alipayNotification = not;
		this.notification = "";
	}

	public AlipayNotificationValidationException(String message, Throwable cause, String not) {
		super(message, cause);
		this.notification = not;
		this.alipayNotification = null;
	}

	public String getNotification() {
		return notification;
	}

	public AlipayNotification getAlipayNotification() {
		return alipayNotification;
	}
}
