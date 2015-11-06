/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.figure;

import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantColorRegistry;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.figure</li>
 * <li>설 명 : LifeLineNameFigure</li>
 * <li>작성일 : 2009. 12. 17.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class LifeLineNameFigure extends RectangleFigure {

    /** label */
    protected CollapsedLabel nameLabel;

    /**
     * nameLabel2
     */
    protected Label nameLabel2;

    /**
     * stereptypeLabel
     */
    protected Label stereptypeLabel;

    /**
     * name
     */
    protected String name;

    /**
     * LifeLineNameFigure
     */
    protected LifeLineNameFigure() {

    }

    /**
     * LifeLineNameFigure
     * @param name
     */
    public LifeLineNameFigure(String name) {
        this.stereptypeLabel = new Label(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
        this.add(this.stereptypeLabel);

        nameLabel = new CollapsedLabel(name);
        this.nameLabel2 = new Label(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
        this.nameLabel.setChildLabel(this.nameLabel2);
        nameLabel.setIcon(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_LIFELINE));

        ToolbarLayout layout = new ToolbarLayout();
        layout.setSpacing(2);
        layout.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);
        setLayoutManager(layout);

        setOpaque(true);
        setBackgroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.White));
        setBorder(new MarginBorder(6));
        add(nameLabel);
        this.add(this.nameLabel2);

    }

    /**
     * getNameLabel2
     *  
     * @return Label
     */
    public Label getNameLabel2() {
        return nameLabel2;
    }

    /**
     * setStereotype
     *  
     * @param stereotype void
     */
    public void setStereotype(String stereotype) {
        this.stereptypeLabel.setText(stereotype);
        // if(null == stereotype || "".equals(stereotype)){
        // this.getChildren().remove(this.stereptypeLabel);
        // }else{
        // if(! this.getChildren().contains(this.stereptypeLabel)){
        // this.add(this.stereptypeLabel,0);
        // }
        // }
        // setSelfSize();
    }

    /**
     * getName
     *  
     * @return String
     */
    public String getName() {
        return nameLabel.getText();
    }

    /**
     * setName
     *  
     * @param name void
     */
    public void setName(String name) {
        this.name = name;
        nameLabel.setText(name);
        // if(0 < this.nameLabel2.getText().length()){
        // if(!this.getChildren().contains(this.nameLabel2)){
        // this.add(this.nameLabel2);
        // }else{
        //                
        // }
        // }else{
        // if(this.getChildren().contains(this.nameLabel2)){
        // this.getChildren().remove(this.nameLabel2);
        // }
        // }
        // setSelfSize();
    }

    /**
     * getLabel
     *  
     * @return CollapsedLabel
     */
    public CollapsedLabel getLabel() {
        return nameLabel;
    }

    /**
     * getStereoTypeLabel
     *  
     * @return Label
     */
    public Label getStereoTypeLabel() {
        return stereptypeLabel;
    }

    // protected void setSelfSize(){
    // if(0 < this.nameLabel2.getText().length()){
    // if(!this.getChildren().contains(this.nameLabel2)){
    // this.add(this.nameLabel2);
    // }else{
    //                
    // }
    // }else{
    // if(this.getChildren().contains(this.nameLabel2)){
    // this.getChildren().remove(this.nameLabel2);
    // }
    // }
    //        
    // int size = this.getChildren().size()-1;
    // //this.setSize(this.getSize().width,FigureConstant.FIGURE_LIFELINE_HEAD_HEIGHT+
    // size*12+2);
    // }

}
