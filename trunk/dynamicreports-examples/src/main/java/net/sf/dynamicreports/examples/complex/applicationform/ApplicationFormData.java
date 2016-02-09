/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2016 Ricardo Mariaca
 * http://www.dynamicreports.org
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.dynamicreports.examples.complex.applicationform;

import java.util.Calendar;

import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class ApplicationFormData {
	private ApplicationForm applicationForm;

	public ApplicationFormData() {
		applicationForm = createApplicationForm();
	}

	private ApplicationForm createApplicationForm() {
		ApplicationForm applicationForm = new ApplicationForm();
		applicationForm.setFirstName("Mary");
		applicationForm.setLastName("Patterson");
		Calendar c = Calendar.getInstance();
		c.set(1990, 3, 11);
		applicationForm.setDateOfBirth(c.getTime());
		applicationForm.setGender(Gender.FEMALE);
		applicationForm.setMaritalStatus(MaritalStatus.MARRIED);
		applicationForm.setAddress("151 Pompton St.");
		applicationForm.setCity("Washington");
		applicationForm.setPostalCode("09820");
		applicationForm.setTelephone("12-744-14682");
		applicationForm.setMobile("259-182572");
		applicationForm.setEmail("mpatterson@noemail.com");

		return applicationForm;
	}

	public ApplicationForm getApplicationForm() {
		return applicationForm;
	}

	public JRDataSource createDataSource() {
		return null;
	}
}
