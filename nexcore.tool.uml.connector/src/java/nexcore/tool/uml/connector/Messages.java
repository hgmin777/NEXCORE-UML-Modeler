/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.connector;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	/**
	 * BUNDLE_NAME
	 */
	private static final String BUNDLE_NAME = "nexcore.tool.uml.connector.messages"; //$NON-NLS-1$
	/**
	 * ServiceMonitor_AUTHENTICATION_SERVICE_CHECK
	 */
	public static String ServiceMonitor_AUTHENTICATION_SERVICE_CHECK;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	/**
	 * Messages
	 */
	private Messages() {
	}
}
