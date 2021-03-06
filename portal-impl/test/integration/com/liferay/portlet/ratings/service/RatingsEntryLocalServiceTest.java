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

package com.liferay.portlet.ratings.service;

import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.MainServletTestRule;
import com.liferay.portal.util.test.GroupTestUtil;
import com.liferay.portal.util.test.RandomTestUtil;
import com.liferay.portal.util.test.ServiceContextTestUtil;
import com.liferay.portal.util.test.TestPropsValues;
import com.liferay.portlet.ratings.EntryScoreException;
import com.liferay.portlet.ratings.model.RatingsEntry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Sergio González
 */
public class RatingsEntryLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), MainServletTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testRatingScore0IsValidScore() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		RatingsEntry ratingsEntry = RatingsEntryLocalServiceUtil.updateEntry(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomLong(), 0, serviceContext);

		Assert.assertEquals(0, ratingsEntry.getScore(), 0.001);
	}

	@Test
	public void testRatingScore1IsValidScore() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		RatingsEntry ratingsEntry = RatingsEntryLocalServiceUtil.updateEntry(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomLong(), 1, serviceContext);

		Assert.assertEquals(1, ratingsEntry.getScore(), 0.001);
	}

	@Test(expected = EntryScoreException.class)
	public void testRatingScoreGreaterThan1IsInvalidScore() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		RatingsEntryLocalServiceUtil.updateEntry(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomLong(), 4, serviceContext);
	}

	@Test(expected = EntryScoreException.class)
	public void testRatingScoreLessThan0IsInvalidScore() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		RatingsEntryLocalServiceUtil.updateEntry(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomLong(), -1, serviceContext);
	}

	@DeleteAfterTestRun
	private Group _group;

}