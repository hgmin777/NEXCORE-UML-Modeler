/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mdd.core.extension;

import java.util.Collection;

import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.mdd.core</li>
 * <li>서브 업무명 : nexcore.tool.mdd.core.extension</li>
 * <li>설 명 : IDomainModelHandler</li>
 * <li>작성일 : 2010. 11. 18.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public interface IDomainModelHandler {

    /**
     * 도메인 모델러 인스턴스 반환
     * 
     * @return IDomainModelHandler
     */
    public IDomainModelHandler getInstance();

    /**
     * 도메인 모델 핸들러 초기화
     * 
     * void
     */
    public abstract void init();

    /**
     * 도메인 모델에서 사용하는 시맨틱 모델 핸들러 등록
     * 
     * void
     */
    public void registerSemanticModelHandler();

    /**
     * 도메인 모델에서 사용하는 노테이션 모델 핸들러 등록
     * 
     * void
     */
    public void registerNotationModelHandler();

    /**
     * 도메인 모델에서 사용하는 리소스셋 초기화
     * 
     * void
     */
    public void initializeResourceSet();

    /**
     * 도메인 모델에서 사용하는 교차참조 어뎁터 초기화
     * 
     * @param resourceSet
     *            void
     */
    public void initializeCrossReferenceAdapter(ResourceSet resourceSet);

    /**
     * 도메인 모델에서 사용하는 리소스셋 리스너 초기화
     * 
     * void
     */
    public void initializeResourceSetListener();

    /**
     * 도메인 모델에서 사용하는 커맨드 스택 리스너 초기화
     * 
     * void
     */
    public void initializeCommandStackListener();

    /**
     * 도메인 모델에서 사용하는 트랜잭셔널 에디팅 도메인 초기화
     * 
     * @param resourceSet
     *            void
     */
    public void initializeTransactionalEditingDomain(ResourceSet resourceSet);

    /**
     * 도메인 모델에서 사용하는 리소스셋 리스너 등록
     * 
     * @param transactionalEditingDomain
     * @param resourceSetListener
     *            void
     */
    public void registerResourceSetListener(TransactionalEditingDomain transactionalEditingDomain,
                                            ResourceSetListener resourceSetListener);

    /**
     * 도메인 모델에서 사용하는 커맨드 스택 리스너 등록
     * 
     * @param transactionalEditingDomain
     * @param commandStackListener
     *            void
     */
    public void registerCommandStackListener(TransactionalEditingDomain transactionalEditingDomain,
                                             CommandStackListener commandStackListener);

    /**
     * 도메인 모델에서 사용하는 패키지 등록
     * 
     * void
     */
    public void registerUsablePackage();

    /**
     * 도메인 모델에서 사용하는 확장자 등록
     * 
     * void
     */
    public void registerUsableExtension();

    /**
     * 도메인 모델에서 사용하는 클립보드 초기화
     * 
     * void
     */
    public void initializeClipboard();

    /**
     * 도메인 모델의 클립보드 설정
     * 
     * void
     */
    public void setClipboard(Collection<Object> clipBoard);

    /**
     * 도메인 모델의 시맨틱 모델 핸들러 반환
     * 
     * @return ISemanticModelHandler
     */
    public ISemanticModelHandler getSemanticModelHandler();

    /**
     * 도메인 모델의 노테이션 모델 핸들러 반환
     * 
     * @return INotationModelHandler
     */
    public INotationModelHandler getNotationModelHandler();

    /**
     * 도메인 모델의 리소스셋 반환
     * 
     * @return ResourceSet
     */
    public ResourceSet getResourceSet();

    /**
     * 
     * 
     *  
     * @param uri
     * @param loadOnDemand
     * @return Resource
     */
    public Resource getResource(URI uri, boolean loadOnDemand);

    /**
     * 도메인 모델의 RM 데이터 리소스셋 반환
     * 
     * @return ResourceSet
     */
    public ResourceSet getRMDataResourceSet();

    /**
     * 도메인 모델의 교차참조 어뎁터 반환
     * 
     * @return ECrossReferenceAdapter
     */
    public ECrossReferenceAdapter getECrossReferenceAdapter();

    /**
     * 도메인 모델의 리소스셋 리스너 반환
     * 
     * @return ResourceSetListener
     */
    public ResourceSetListener getResourceSetListener();

    /**
     * 도메인 모델의 커맨드 스택 리스너 반환
     * 
     * @return CommandStackListener
     */
    public CommandStackListener getCommandStackListener();

    /**
     * 도메인 모델의 트랜잭셔널 에디팅 도메인 반환
     * 
     * @return TransactionalEditingDomain
     */
    public TransactionalEditingDomain getTransactionalEditingDomain();

    /**
     * 도메인 모델의 클립보드 반환
     * 
     * @return Collection<Object>
     */
    public Collection<Object> getClipboard();

}
