/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.documentselector;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class DocumentSelectorUtil {

	public static String getCKEditorFuncNum(HttpServletRequest request) {
		String ckEditorFuncNum = ParamUtil.getString(
			request, "CKEditorFuncNum");

		HttpServletRequest originalRequest =
			PortalUtil.getOriginalServletRequest(request);

		return ParamUtil.getString(
			originalRequest, "CKEditorFuncNum", ckEditorFuncNum);
	}

}