/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2015 Ricardo Mariaca
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

package net.sf.dynamicreports.jasper.transformation;

import net.sf.dynamicreports.design.definition.DRIDesignGroup;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignGroup;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class GroupTransform {
	private JasperTransformAccessor accessor;

	public GroupTransform(JasperTransformAccessor accessor) {
		this.accessor = accessor;
	}

	public void transform() {
		for (DRIDesignGroup group : accessor.getReport().getGroups()) {
			addGroup(group);
		}
	}

	private void addGroup(DRIDesignGroup group) {
		try {
			JRDesignGroup jrGroup = group(group);
			accessor.getDesign().addGroup(jrGroup);
		} catch (JRException e) {
			throw new JasperDesignException("Registration failed for group \"" + group.getName() + "\"", e);
		}
	}

	private JRDesignGroup group(DRIDesignGroup group) {
		JRDesignGroup jrGroup = new JRDesignGroup();
		jrGroup.setName(group.getName());
		jrGroup.setReprintHeaderOnEachPage(group.isReprintHeaderOnEachPage());
		jrGroup.setStartNewColumn(group.isStartInNewColumn());
		jrGroup.setStartNewPage(group.isStartInNewPage());
		jrGroup.setResetPageNumber(group.isResetPageNumber());
		if (group.getMinHeightToStartNewPage() != null) {
			jrGroup.setMinHeightToStartNewPage(group.getMinHeightToStartNewPage());
		}
		jrGroup.setFooterPosition(ConstantTransform.groupFooterPosition(group.getFooterPosition()));
		jrGroup.setKeepTogether(group.isKeepTogether());
		return jrGroup;
	}

	public void transformExpressions() {
		for (DRIDesignGroup group : accessor.getReport().getGroups()) {
			getGroup(group).setExpression(accessor.getExpressionTransform().getExpression(group.getGroupExpression()));
		}
	}

	public JRDesignGroup getGroup(DRIDesignGroup group) {
		JRDesignGroup jrGroup = (JRDesignGroup) accessor.getDesign().getGroupsMap().get(group.getName());
		if (jrGroup == null)
			throw new JasperDesignException("Group " + group.getName() + " is not registered");
		return jrGroup;
	}
}
