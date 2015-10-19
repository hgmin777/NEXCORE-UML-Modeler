/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.type;

import org.eclipse.uml2.uml.VisibilityKind;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.type</li>
 * <li>설  명 : VisibilityType</li>
 * <li>작성일 : 2009. 12. 8.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public enum VisibilityType {

    /**
     * PUBLIC
     */
    PUBLIC, /**
     * PRIVATE
     */
    PRIVATE, /**
     * PROTECTED
     */
    PROTECTED, /**
     * PACKAGE
     */
    PACKAGE;

    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        switch (this) {
            case PUBLIC:
                return VisibilityKind.PUBLIC_LITERAL.toString();
            case PRIVATE:
                return VisibilityKind.PRIVATE_LITERAL.toString();
            case PROTECTED:
                return VisibilityKind.PROTECTED_LITERAL.toString();
            case PACKAGE:
                return VisibilityKind.PACKAGE_LITERAL.toString();
            default:
                return ""; //$NON-NLS-1$
        }
    }
}
