/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.explorer.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : ImportIOAction</li>
 * <li>작성일 : 2011. 10. 20.</li>
 * <li>작성자 : 강석찬</li>
 * </ul>
 */
public class ImportIoAction extends CommonActionDelegate {

    /** 선택한 객체들 */
    List<Element> selectedElementList;

    /** 선택한 객체 */
    private EObject firstSelectedEObject;

    /**
     * 
     */
    public ImportIoAction() {
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
        // Model model;
        //
        // if (!(firstSelectedEObject instanceof Model)) {
        // Package pack = (Package) firstSelectedEObject;
        // model = pack.getModel();
        // } else {
        // model = (Model) firstSelectedEObject;
        // }

        Package pack = (Package) firstSelectedEObject;

        Shell shell = UiCorePlugin.getShell();
        FileDialog dialog = new FileDialog(shell, SWT.MULTI);

        dialog.setFilterNames(new String[] { UICoreConstant.EXCEL_IO_IMPORT_XLS }); //$NON-NLS-1$
        dialog.setFilterExtensions(new String[] { UICoreConstant.EXCEL_IO_IMPORT_XLS }); //$NON-NLS-1$

        dialog.open();
        String filePath = dialog.getFilterPath();
        String[] fileNames = dialog.getFileNames();

        ImportIoCommand cmd = new ImportIoCommand(pack, filePath, fileNames);

//        ProjectResourceSetListenerController.getInstance().fireSkipChangeEvent(true);
//        ((UMLDiagramCommandStack) DomainRegistry.getUMLDomain().getCommandStackListener()).execute(cmd);
//        ProjectResourceSetListenerController.getInstance().fireSkipChangeEvent(false);
        
        // 2012.05.07 modified by nspark  Transaction.OPTION_NO_NOTIFICATIONS  true 적용
        ((UMLDiagramCommandStack) DomainRegistry.getUMLDomain().getCommandStackListener()).execute(cmd, Boolean.TRUE);
        
        ProjectUtil.refreshNodeInExplorer(pack);
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @SuppressWarnings("unchecked")
    public void selectionChanged(IAction action, ISelection selection) {
        if (!(selection instanceof IStructuredSelection)) {
            return;
        }

        IStructuredSelection structureSelection = (IStructuredSelection) selection;
        Object pickedUpObject;
        ITreeNode node;

        selectedElementList = new ArrayList<Element>();

        for (Iterator<Object> structureSelectionIterator = structureSelection.iterator(); structureSelectionIterator.hasNext();) {
            pickedUpObject = structureSelectionIterator.next();

            if (pickedUpObject instanceof ITreeNode) {
                node = (ITreeNode) pickedUpObject;

                selectedElementList.add((Element) node.getEObject());

                if (selectedElementList.size() == 1) {
                    firstSelectedEObject = (Element) node.getEObject();
                }
            }
        }

    }

    public class ImportIoCommand extends Command {

        /** io를 import할 모델 */
        private Package pack;

        private String[] fileNames;

        private String filePath;

        public ImportIoCommand(Package pack, String filePath, String[] fileNames) {
            this.pack = pack;
            this.filePath = filePath;
            this.fileNames = fileNames;
        }

        /**
         * @see org.eclipse.gef.commands.Command#execute()
         */
        @Override
        public void execute() {
            if (fileNames.length == 0) {
                return;
            }

            int successNum = 0;
            int errorNum = 0;
            String errMsg = UICoreConstant.EMPTY_STRING;

//            for (String fileName : fileNames) {
//                try {
//
//                    fileName = filePath + UICoreConstant.PROJECT_CONSTANTS__SEPARATOR + fileName;
//
//                    File file = new File(fileName);
//                    if (!file.exists() || (fileName == null)) {
//                        errorNum++;
//                        errMsg = errMsg + UICoreConstant.PROJECT_CONSTANTS__NEW_LINE + fileName;
//                        continue;
//                    }
//
//                    ExcelManager excelMngr = new ExcelManager(UiCorePlugin.getShell());
//                    excelMngr.setExcelFilePath(fileName);
//                    boolean bRead = excelMngr.beginRead(fileName);
//                    if (!bRead) {
//                        errorNum++;
//                        errMsg = errMsg + UICoreConstant.PROJECT_CONSTANTS__NEW_LINE + fileName;
//                        continue;
//                    }
//
//                    Workbook workBook = excelMngr.getWorkBook();
//                    if (workBook.getWorksheets().size() < 1) {
//                        errorNum++;
//                        errMsg = errMsg + UICoreConstant.PROJECT_CONSTANTS__NEW_LINE + fileName;
//                        continue;
//                    }
//
//                    Worksheet columnsSheet = workBook.getWorksheets().getSheet(0);
//                    // String columnsSheetName = columnsSheet.getName();
//                    // if ((columnsSheet == null) ||
//                    // (!columnsSheetName.equals(UICoreConstant.EXCEL_IO_INTERFACE_DEFINITION)))
//                    // {
//                    if (columnsSheet == null) {
//                        errorNum++;
//                        errMsg = errMsg + UICoreConstant.PROJECT_CONSTANTS__NEW_LINE + fileName;
//                        continue;
//                    }
//
//                    int colCnt = columnsSheet.getCells().getMaxColumn();
//                    int rowCnt = columnsSheet.getCells().getMaxRow();
//
//                    Cell tempCell = null;
//                    String cellContent = null;
//
//                    String componentId = null;
//                    String methodId = null;
//                    boolean flag = false;
//                    int tableHeaderIdx = 0;
//                    int rowIdx = 0;
//
//                    while (!flag) {
//                        for (int colIdx = 0; colIdx <= colCnt; colIdx++) {
//                            cellContent = columnsSheet.getCells().getCell(rowIdx, colIdx).getStringValue().trim();
//
//                            if (!flag) {
//                                if (cellContent.equals(UICoreConstant.EXCEL_IO_IMPORT_COMPONENT_ID)) {
//                                    componentId = columnsSheet.getCells()
//                                        .getCell(rowIdx, colIdx + 1)
//                                        .getStringValue()
//                                        .trim();
//                                }
//                                if (cellContent.equals(UICoreConstant.EXCEL_IO_IMPORT_METHOD_ID)) {
//                                    methodId = columnsSheet.getCells()
//                                        .getCell(rowIdx, colIdx + 1)
//                                        .getStringValue()
//                                        .trim();
//                                }
//                                if (cellContent.equals(UICoreConstant.EXCEL_IO_IMPORT_SEQ)) {
//                                    flag = true;
//                                    tableHeaderIdx = rowIdx;
//                                }
//                            }
//                        }
//                        rowIdx++;
//                    }
//
//                    int seqIdx = 0;
//                    int ioIdx = 1;
//                    int recordEngIdx = 2;
//                    int recordKorIdx = 3;
//                    int multiplicityIdx = 4;
//                    int idEngIdx = 5;
//                    int idKorIdx = 6;
//                    int typeIdx = 7;
//                    int lengthIdx = 8;
//                    int dataIdx = 10;
//                    int encryptedIdx = 13;
//
//                    // 1. 선택된 패키지(모델)하위 패키지를 모두 뒤져서 componentId와 이름같은걸 찾아냄
//                    // 1-1. 같은게 없는경우 3으로 넘어가서 선택된 패키지하위 operation을 검색함
//                    // 2. 그 패키지 하위 biz 패키지를 찾음
//                    // 3. biz패키지 하위 operation중에 methodId랑 이름같은걸 찾음
//                    // 4. 그 operation이 속한 class의 package를 찾아 그 하위에 io폴더를 생성함
//
//                    EObjectCondition condition = new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getPackage());
//                    SELECT statement = new SELECT(new FROM(this.pack), new WHERE(condition));
//                    IQueryResult result = statement.execute();
//
//                    Package pack = null;
//                    flag = false;
//                    for (EObject obj : result) {
//                        pack = (Package) obj;
//                        if (pack.getName().equalsIgnoreCase(componentId)) {
//                            flag = true;
//                            break;
//                        }
//                    }
//
//                    if (flag) {
//                        flag = false;
//                        for (EObject obj : pack.getOwnedMembers()) {
//                            if (obj instanceof Package) {
//                                if (((Package) obj).getName().equals(UICoreConstant.EXCEL_IO_IMPORT_BIZ)) {
//                                    pack = (Package) obj;
//                                    flag = true;
//                                    break;
//                                }
//                            }
//                        }
//
//                        if (!flag) {
//                            errorNum++;
//                            errMsg = errMsg + UICoreConstant.PROJECT_CONSTANTS__NEW_LINE + fileName;
//                            continue;
//                        }
//                    } else {
//                        pack = this.pack;
//                    }
//
//                    condition = new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getOperation());
//                    statement = new SELECT(new FROM(pack), new WHERE(condition));
//                    result = statement.execute();
//
//                    flag = false;
//                    String ioPrefix = UiCorePlugin.getDefault()
//                        .getPreferenceStore()
//                        .getString(ManagerConstant.PREFERENCE_COMPONENT_REFACTOR_IO_IMPORT);
//                    String[] prefixs = ioPrefix.split(UICoreConstant.PROJECT_CONSTANTS__COMMA.trim());
//
//                    Operation op = null;
//                    for (EObject obj : result) {
//                        op = (Operation) obj;
//                        if (op.getName().equalsIgnoreCase(methodId)) {
//                            String packName = op.getClass_().getPackage().getName();
//
//                            for (String prefix : prefixs) {
//                                prefix = prefix.trim();
//                                if (prefix != UICoreConstant.EMPTY_STRING) {
//                                    if (packName.substring(0, prefix.length()).equals(prefix)) {
//                                        flag = true;
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                        if (flag) {
//                            break;
//                        }
//                    }
//
//                    if (!flag) {
//                        errorNum++;
//                        errMsg = errMsg + UICoreConstant.PROJECT_CONSTANTS__NEW_LINE + fileName;
//                        continue;
//                    }
//
//                    pack = op.getClass_().getPackage();
//
//                    // operation명은 모두 대문자로
//                    methodId = methodId.toUpperCase();
//
//                    Package ioPackage = UMLFactory.eINSTANCE.createPackage();
//                    ioPackage.setName(UICoreConstant.EXCEL_IO_IMPORT_IO);
//                    Class inClass = UMLFactory.eINSTANCE.createClass();
//                    Class outClass = UMLFactory.eINSTANCE.createClass();
//                    ioPackage = (Package) addChild(pack, ioPackage);
//                    inClass.setName(methodId + UICoreConstant.EXCEL_IO_IMPORT_REQ);
//                    outClass.setName(methodId + UICoreConstant.EXCEL_IO_IMPORT_RES);
//                    inClass = (Class) addChild(ioPackage, inClass);
//                    outClass = (Class) addChild(ioPackage, outClass);
//
//                    for (Iterator<Stereotype> it = inClass.getApplicableStereotypes().iterator(); it.hasNext();) {
//                        Stereotype stereotype = it.next();
//                        if (stereotype.getName().equals(UICoreConstant.EXCEL_IO_IMPORT_IN)) {
//                            applyStereotype(inClass, stereotype);
//                        }
//                        if (stereotype.getName().equals(UICoreConstant.EXCEL_IO_IMPORT_OUT)) {
//                            applyStereotype(outClass, stereotype);
//                        }
//                    }
//                    Stereotype ioStereotype = null;
//                    List<String> recordNameList = new ArrayList<String>();
//                    List<Class> recordClassList = new ArrayList<Class>();
//                    for (rowIdx = tableHeaderIdx + 1; rowIdx <= rowCnt; rowIdx++) {
//                        tempCell = columnsSheet.getCells().getCell(rowIdx, seqIdx);
//                        cellContent = tempCell.getStringValue().trim();
//                        if (cellContent != UICoreConstant.EMPTY_STRING) {
//                            String io = columnsSheet.getCells().getCell(rowIdx, ioIdx).getStringValue().trim();
//                            String recordEng = columnsSheet.getCells()
//                                .getCell(rowIdx, recordEngIdx)
//                                .getStringValue()
//                                .trim();
//                            String recordKor = columnsSheet.getCells()
//                                .getCell(rowIdx, recordKorIdx)
//                                .getStringValue()
//                                .trim();
//
//                            String ref = null;
//                            String idEng = columnsSheet.getCells().getCell(rowIdx, idEngIdx).getStringValue().trim();
//                            String multiplicity = columnsSheet.getCells()
//                                .getCell(rowIdx, multiplicityIdx)
//                                .getStringValue()
//                                .trim();
//                            String idKor = columnsSheet.getCells().getCell(rowIdx, idKorIdx).getStringValue().trim();
//                            String type = columnsSheet.getCells().getCell(rowIdx, typeIdx).getStringValue().trim();
//                            String length = columnsSheet.getCells().getCell(rowIdx, lengthIdx).getStringValue().trim();
//                            String data = columnsSheet.getCells().getCell(rowIdx, dataIdx).getStringValue().trim();
//                            String encrypted = columnsSheet.getCells()
//                                .getCell(rowIdx, encryptedIdx)
//                                .getStringValue()
//                                .trim();
//
//                            // idEng = "";
//
//                            Property property = UMLFactory.eINSTANCE.createProperty();
//                            property.setVisibility(VisibilityKind.PRIVATE_LITERAL);
//                            property.setName(idEng);
//
//                            // property 추가시 구간구분이 있으면 (recordEng이 존재하면) Class,
//                            // Property하나 추가
//                            if (!(recordEng == null || UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING.equals(recordEng))) {
//                                if ((!recordNameList.contains(recordEng))) {
//                                    recordNameList.add(recordEng);
//                                    Class clazz = UMLFactory.eINSTANCE.createClass();
//                                    clazz.setName(methodId + StringUtil.toUpperCaseAtFirstChar(recordEng));
//                                    clazz = (Class) addChild(ioPackage, clazz);
//                                    recordClassList.add(clazz);
//                                    property = (Property) addChild(clazz, property);
//
//                                    // recordSet 추가
//                                    Property newProperty = UMLFactory.eINSTANCE.createProperty();
//                                    newProperty.setName(recordEng);
//
//                                    for (Iterator<Stereotype> it = clazz.getApplicableStereotypes().iterator(); it.hasNext();) {
//                                        Stereotype stereotype = it.next();
//                                        if ((io.equals(UICoreConstant.EXCEL_IO_IMPORT_I))
//                                            && (stereotype.getName().equals(UICoreConstant.EXCEL_IO_IMPORT_IN))) {
//                                            applyStereotype(clazz, stereotype);
//                                            newProperty = (Property) addChild(inClass, newProperty);
//                                        }
//                                        if ((io.equals(UICoreConstant.EXCEL_IO_IMPORT_O))
//                                            && (stereotype.getName().equals(UICoreConstant.EXCEL_IO_IMPORT_OUT))) {
//                                            applyStereotype(clazz, stereotype);
//                                            newProperty = (Property) addChild(outClass, newProperty);
//                                        }
//                                    }
//
//                                    newProperty.setVisibility(VisibilityKind.PRIVATE_LITERAL);
//                                    newProperty.setType(clazz);
//
//                                    // 구간반복횟수
//                                    if (StringUtil.isDigit(multiplicity)) {
//                                        int value = new Integer(multiplicity).intValue();
//                                        newProperty.setLower(value);
//                                        newProperty.setUpper(value);
//                                    } else {
//                                        newProperty.setLower(1);
//                                        newProperty.setUpper(1);
//                                        ref = multiplicity;
//                                    }
//
//                                    for (Iterator<Stereotype> it = newProperty.getApplicableStereotypes().iterator(); it.hasNext();) {
//                                        Stereotype s = it.next();
//                                        if (s.getName().equals(UICoreConstant.EXCEL_IO_IMPORT_IO)) {
//                                            ioStereotype = s;
//                                            applyStereotype(newProperty, s);
//
//                                            for (Iterator<Property> it2 = ioStereotype.getAllAttributes().iterator(); it2.hasNext();) {
//                                                Property p = (Property) it2.next();
//                                                String name = p.getName();
//
//                                                if (name.equals(UICoreConstant.EXCEL_IO_IMPORT_REF)) {
//                                                    ProfileUtil.setValueOfProperty(newProperty, p, ref);
//                                                }
//
//                                            }
//                                        }
//                                    }
//                                } else {
//                                    for (Iterator<Class> it = recordClassList.iterator(); it.hasNext();) {
//                                        Class clazz = (Class) it.next();
//                                        if (clazz.getName().equals(methodId
//                                            + StringUtil.toUpperCaseAtFirstChar(recordEng))) {
//                                            property = (Property) addChild(clazz, property);
//                                        }
//                                    }
//                                }
//                            } else {
//                                if (io.equals(UICoreConstant.EXCEL_IO_IMPORT_I)) {
//                                    property = (Property) addChild(inClass, property);
//                                } else {
//                                    property = (Property) addChild(outClass, property);
//                                }
//                            }
//
//                            for (Iterator<Stereotype> it = property.getApplicableStereotypes().iterator(); it.hasNext();) {
//                                Stereotype s = it.next();
//                                if (s.getName().equals(UICoreConstant.EXCEL_IO_IMPORT_IO)) {
//                                    ioStereotype = s;
//                                    applyStereotype(property, s);
//                                    Property refProperty = null;
//                                    ref = null;
//
//                                    for (Iterator<Property> it2 = ioStereotype.getAllAttributes().iterator(); it2.hasNext();) {
//                                        Property p = (Property) it2.next();
//                                        String name = p.getName();
//
//                                        if (name.equals(UICoreConstant.EXCEL_IO_IMPORT_REF)) {
//                                            refProperty = p;
//                                        }
//                                        if (name.equals(UICoreConstant.EXCEL_IO_IMPORT_KONAME)) {
//                                            ProfileUtil.setValueOfProperty(property, p, idKor);
//                                        }
//                                        if (name.equals(UICoreConstant.EXCEL_IO_IMPORT_ENNAME)) {
//                                            ProfileUtil.setValueOfProperty(property, p, idEng);
//                                        }
//                                        if (name.equals(UICoreConstant.EXCEL_IO_IMPORT_LENGTH_ENG)) {
//                                            String num = StringUtil.toNumber(length);
//                                            if (!(num == null || UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING.equals(num))) {
//                                                double len = Double.parseDouble(length);
//                                                if (!length.equals(num)) {
//                                                    ref = length;
//                                                    length = String.valueOf(Math.round(len));
//                                                }
//                                                ProfileUtil.setValueOfProperty(property, p, length);
//                                            }
//                                        }
//                                        if (name.equals(UICoreConstant.EXCEL_IO_IMPORT_DATA_ENG)) {
//                                            ProfileUtil.setValueOfProperty(property, p, data);
//                                        }
//                                        if (name.equals(UICoreConstant.EXCEL_IO_IMPORT_ENCRYPT_ENG)) {
//                                            ProfileUtil.setValueOfProperty(property, p, new Boolean(encrypted));
//                                        }
//                                        if (name.equals(UICoreConstant.EXCEL_IO_IMPORT_DATA_TYPE)) {
//                                            EnumerationLiteral literal = getType(p, type);
//                                            if (literal != null) {
//                                                ProfileUtil.setValueOfProperty(property, p, getType(p, type));
//                                            } else {
//                                                errorNum++;
//                                                successNum--;
//                                                errMsg = errMsg + UICoreConstant.PROJECT_CONSTANTS__NEW_LINE + fileName;
//                                                continue;
//                                            }
//                                        }
//                                    }
//
//                                    if (ref != null) {
//                                        ProfileUtil.setValueOfProperty(property, refProperty, ref);
//                                    } else {
//                                        ProfileUtil.setValueOfProperty(property,
//                                            refProperty,
//                                            UICoreConstant.EMPTY_STRING);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    successNum++;
//                } catch (Exception e) {
//                    Log.error(e);
//                    errorNum++;
//                    errMsg = errMsg + UICoreConstant.PROJECT_CONSTANTS__NEW_LINE + fileName;
//                }
//
//            }

            if (errorNum != 0) {
                errMsg = UICoreConstant.PROJECT_CONSTANTS__NEW_LINE + UICoreConstant.PROJECT_CONSTANTS__NEW_LINE
                    + UICoreConstant.PROJECT_CONSTANTS__HYPHEN + UICoreConstant.EMPTY_STRING
                    + UMLMessage.LABEL_IO_IMPORT_ERROR_FILENAME + errMsg;
            }

            MessageDialog.openInformation(ProjectExplorerPlugin.getShell(),
                UMLMessage.LABEL_IO_IMPORT,
                UMLMessage.LABEL_IO_IMPORT_SUCCESS + UICoreConstant.PROJECT_CONSTANTS__COLON + successNum
                    + UICoreConstant.PROJECT_CONSTANTS__BLANK + UMLMessage.LABEL_IO_IMPORT_ERROR
                    + UICoreConstant.PROJECT_CONSTANTS__COLON + errorNum + errMsg);
        }

        private void applyStereotype(Element object, Stereotype s) {
            if (!object.isStereotypeApplied(s)) {
                object.applyStereotype(s);
            }
        }

        private NamedElement addChild(NamedElement parent, NamedElement child) {
            // duplicate check
            boolean isDup = false;
            NamedElement newChild = null;

            if (parent instanceof Package) {
                if (child instanceof Package) {
                    for (Iterator<PackageableElement> it = ((Package) parent).getPackagedElements().iterator(); it.hasNext();) {
                        PackageableElement element = (PackageableElement) it.next();
                        if (element instanceof Package) {
                            if (((Package) element).getName().equals(child.getName())) {
                                isDup = true;
                                newChild = element;
                            }
                        }
                    }
                    if (!isDup) {
                        ((Package) parent).getPackagedElements().add((Package) child);
                        newChild = child;
                    }
                } else if (child instanceof Class) {
                    for (Iterator<PackageableElement> it = ((Package) parent).getPackagedElements().iterator(); it.hasNext();) {
                        PackageableElement element = (PackageableElement) it.next();
                        if (element instanceof Class) {
                            if (((Class) element).getName().equals(child.getName())) {
                                isDup = true;
                                newChild = element;
                            }
                        }
                    }
                    if (!isDup) {
                        ((Package) parent).getPackagedElements().add((Class) child);
                        newChild = child;
                    }
                }
            } else if (parent instanceof Class) {
                if (child instanceof Property) {
                    for (Iterator<Property> it = ((Class) parent).getOwnedAttributes().iterator(); it.hasNext();) {
                        Property element = (Property) it.next();
                        if (element instanceof Property) {
                            if (((Property) element).getName().equals(child.getName())) {
                                isDup = true;
                                newChild = element;
                            }
                        }
                    }
                }
                if (!isDup) {
                    ((Class) parent).getOwnedAttributes().add((Property) child);
                    newChild = child;
                }
            }
            return newChild;

        }

        private EnumerationLiteral getType(Property p, String type) {
            if ((type.equals(UICoreConstant.EXCEL_IO_IMPORT_TYPE_INT16))
                || (type.equals(UICoreConstant.EXCEL_IO_IMPORT_TYPE_INTEGER))) {
                type = UICoreConstant.EXCEL_IO_IMPORT_TYPE_INT;
            }

            if (type.equals(UICoreConstant.EXCEL_IO_IMPORT_TYPE_BIG_DECIMAL)) {
                type = UICoreConstant.EXCEL_IO_IMPORT_TYPE_DECIMAL;
            }

            if (type.equals(UICoreConstant.EXCEL_IO_IMPORT_TYPE_BYTE_ARRAY)) {
                type = UICoreConstant.EXCEL_IO_IMPORT_TYPE_BINARY;
            }

            EnumerationLiteral value = null;
            Object[] enumerationList = ((Enumeration) ((Property) p).getType()).getOwnedLiterals().toArray();

            for (Object enumeration : enumerationList) {
                EnumerationLiteral e = (EnumerationLiteral) enumeration;
                if (e.getName().equals(type)) {
                    value = e;
                }
            }

            return value;

            // byteArray, varString => 없음 (default : string으로 함)
            // int16, integer => int
            // string,long,float,double,decimal,varBinary => 존재

        }
    }
}
