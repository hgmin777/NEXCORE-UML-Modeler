/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.property.part.factory;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.property.part.factory</li>
 * <li>설 명 : SectionPartFactoryAdapter</li>
 * <li>작성일 : 2010. 2. 9.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class SectionPartFactoryAdapter implements SectionPartFactory {

    /**
     * tabbedPropertySheetPage
     */
    protected TabbedPropertySheetPage tabbedPropertySheetPage;

    /**
     * getWidgetFactory
     *  
     * @return TabbedPropertySheetWidgetFactory
     */
    protected TabbedPropertySheetWidgetFactory getWidgetFactory() {
        return tabbedPropertySheetPage.getWidgetFactory();
    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createAbstractPart(org.eclipse.swt.widgets.Composite)
     */
    public void createAbstractPart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createAggregationPart(org.eclipse.swt.widgets.Composite)
     */
    public void createAggregationPart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createArgumentSignaturePart(org.eclipse.swt.widgets.Composite)
     */
    public void createArgumentSignaturePart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createArgumentsPart(org.eclipse.swt.widgets.Composite)
     */
    public void createArgumentsPart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createDocumentPart(org.eclipse.swt.widgets.Composite)
     */
    public void createDocumentPart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createFileNamePart(org.eclipse.swt.widgets.Composite)
     */
    public void createFileNamePart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createIsNavigablePart(org.eclipse.swt.widgets.Composite)
     */
    public void createIsNavigablePart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createLabelPart(org.eclipse.swt.widgets.Composite)
     */
    public void createLabelPart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createLeafPart(org.eclipse.swt.widgets.Composite)
     */
    public void createLeafPart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createMessageSortTypePart(org.eclipse.swt.widgets.Composite)
     */
    public void createMessageSortTypePart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createMultiplicityPart(org.eclipse.swt.widgets.Composite)
     */
    public void createMultiplicityPart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createNamePart(org.eclipse.swt.widgets.Composite)
     */
    public void createNamePart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createOperationPart(org.eclipse.swt.widgets.Composite)
     */
    public void createOperationPart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createParts(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    public void createParts(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        this.tabbedPropertySheetPage = tabbedPropertySheetPage;
        this.createParts(parent);
    }

    /**
     * createParts
     *  
     * @param parent void
     */
    public void createParts(Composite parent) {

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createPropertyPart(org.eclipse.swt.widgets.Composite)
     */
    public void createPropertyPart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createRepresentsPart(org.eclipse.swt.widgets.Composite)
     */
    public void createRepresentsPart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createRolePart(org.eclipse.swt.widgets.Composite)
     */
    public void createRolePart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createSignaturePart(org.eclipse.swt.widgets.Composite)
     */
    public void createSignaturePart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createSpecificationPart(org.eclipse.swt.widgets.Composite)
     */
    public void createSpecificationPart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createStandardPart(org.eclipse.swt.widgets.Composite)
     */
    public void createStandardPart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createStereoTypePart(org.eclipse.swt.widgets.Composite)
     */
    public void createStereoTypePart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createSubstitutionPart(org.eclipse.swt.widgets.Composite)
     */
    public void createSubstitutionPart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createTypePart(org.eclipse.swt.widgets.Composite)
     */
    public void createTypePart(Composite parent) {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactory#createVisibilityPart(org.eclipse.swt.widgets.Composite)
     */
    public void createVisibilityPart(Composite parent) {
        // TODO Auto-generated method stub

    }

}
