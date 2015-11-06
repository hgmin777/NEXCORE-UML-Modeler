/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.alm.common.exception;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.core</li>
 * <li>서브 업무명 : nexcore.tool.core.exception</li>
 * <li>설  명 : BaseException</li>
 * <li>작성일 : 2009. 04. 26</li>
 * <li>작성자 : 06372</li>
 * </ul>
 * try/catch가 필요 없는 Runtime 예외처리객체를 만들때 사용한다.<br>
 */
public class BaseRuntimeException extends RuntimeException{
	private static final long serialVersionUID = -989445153423703411L;
	
	/**
     * 메시지 문자열을 받는 생성자
     * 자동으로 로그 기록을 수행한다.
     * 
     * <pre>
     * [사용법]
     *  //에러가 발생한 위치에서 새로운 메시지를 던짐
     *  String message = "관련 자료가 없습니다.";
     *  // 메시지 문자열을 인자로 넘김
     *  throw new BaseRuntimeException(message);
     * </pre>
     * 
	 * @param message 출력한 문자열
	 * @param cause Exception	
	 */
	public BaseRuntimeException(String message, Throwable cause) {
		super(cause);
	}
	
	/**
     * 메시지 문자열을 받는 생성자
     * 자동으로 로그 기록을 수행한다.
     * 
     * <pre>
     * [사용법]
     *  //에러가 발생한 위치에서 새로운 메시지를 던짐
     *  String message = "관련 자료가 없습니다.";
     *  // 메시지 문자열을 인자로 넘김
     *  throw new BaseRuntimeException(message);
     * </pre>
     * 
	 * @param message 출력한 문자열
	 */
	public BaseRuntimeException(String message) {
		super(message);
	}

    /**
     * try/catch에 잡은 Exception을 처리할 때 사용한다.
     * 자동으로 로그 기록을 수행한다.
     * 
     * <pre>
     * [사용법]
     *  try{
     *     // 관련 코드
     *     ...
     *  } catch (Exception e){
     *          //에러가 발생한 위치에서 새로운 메시지를 던짐
     *          String message = "관련 자료가 없습니다.";
     *          // Log의 타입과 메시지 문자열을 인자로 넘김
     *          throw new BaseRuntimeException(e, message, LogType.SUI);
     *  }
     * </pre>
     * 
     * @param cause Exception
     * @param message 출력한 문자열
     * @param logType nexcore.tool.core.log.LogType의 상수값 예) LogType.SUI
     */
    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * try/catch에 잡은 Exception을 처리할 때 사용한다.
     * 자동으로 로그 기록을 수행한다.
     * 
     * <pre>
     * [사용법]
     *  try{
     *     // 관련 코드
     *     ...
     *  } catch (Exception e){
     *          //에러가 발생한 위치에서 새로운 메시지를 던짐
     *          String message = "관련 자료가 없습니다.";
     *          // Log의 타입과 메시지 문자열을 인자로 넘김
     *          throw new BaseRuntimeException(e, message);
     *  }
     * </pre>
     * 
     * @param cause Exception
     * @param message 출력한 문자열
     */
	public BaseRuntimeException(Throwable cause, String message) {
		super(message, cause);
	}

	/**
     * 메시지 문자열을 받는 생성자
     * 자동으로 로그 기록을 수행한다.
     * 
     * <pre>
     * [사용법]
     *  //에러가 발생한 위치에서 새로운 메시지를 던짐
     *  String message = "관련 자료가 없습니다.";
     *  // Log의 타입과 메시지 문자열을 인자로 넘김
     *  throw new BaseRuntimeException(message, LogType.SUI);
     * </pre>
     * 
     * @param message 출력한 문자열
     * @param logType nexcore.tool.core.log.LogType의 상수값 예) LogType.SUI
     */
    public BaseRuntimeException(String message, String logType) {
        super(message);
    }
    
    /**
     * try/catch에 잡은 Exception을 처리할 때 사용한다.
     * 자동으로 로그 기록을 수행한다.
     * 
     * <pre>
     * [사용법]
     *  try{
     *     // 관련 코드
     *     ...
     *  } catch (Exception e){
     *          //에러가 발생한 위치에서 새로운 메시지를 던짐
     *          String message = "관련 자료가 없습니다.";
     *          // Log의 타입과 메시지 문자열을 인자로 넘김
     *          throw new BaseRuntimeException(e, message, LogType.SUI);
     *  }
     * </pre>
     * 
     * @param cause Exception
     * @param message 출력한 문자열
     * @param logType nexcore.tool.core.log.LogType의 상수값 예) LogType.SUI
     */
    public BaseRuntimeException(Throwable cause, String message, String logType) {
        super(message, cause);
    }
}
