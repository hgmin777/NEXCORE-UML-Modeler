/* 
* Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
* 
* This software is the confidential and proprietary information of SK C&C. 
* You shall not disclose such confidential information and shall use it 
* only in accordance with the terms of the license agreement you entered into 
* with SK C&C. 
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
 * try/catch가 필요한 예외처리객체를 만들때 사용한다.<br>
 */
public class BaseException extends Exception {

	private static final long serialVersionUID = -2236574923790607939L;
	
	/**
     * 메시지를 받는 생성자 - 사용 금지
     * 
     * @param message
     *          예외에 설정할 메시지 문자열. 로그에 남길 메시지 문자열을 입력한다.
	 */
	public BaseException(String message) {
		super(message);
	}
	
	/**
	 * try/catch에 잡은 Exception을 처리할 때 사용한다.
     * 메시지와 Throwable 객체를 받는 생성자
     * 
     * <pre>
     * [사용법]
     *  try{
     *     // 관련 코드
     *     ...
     *  } catch (Exception e){
     *          //에러가 발생한 위치에서 새로운 메시지를 던짐
     *          String message = "관련 자료가 없습니다.";
     *          // 메시지 문자열과 Throwable 객체를 인자로 넘김
     *          throw new BaseException(message, e);
     *  }
     * </pre>
     * 
	 * @param message
     *          예외에 설정할 메시지 문자열. 로그에 남길 메시지 문자열을 입력한다.
	 * @param cause
     *          설정할 예외 상황. Throwable을 상속받은 모든 예외가 적용될 수 있다.
	 */
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}
    
    /**
     * try/catch에 잡은 Exception을 처리할 때 사용한다.
     * Throwable 객체를 받는 생성자.
     * 
     * <pre>
     * [사용법]
     *  try{
     *     // 관련 코드
     *     ...
     *  } catch (Exception e){
     *          throw new BaseException(e);
     *  }
     * </pre>
     * 
     * @param cause
     *          설정할 추출할 예외 상황. Throwable을 상속받은 모든 예외가 적용될 수 있다.
     */
    public BaseException(Throwable cause) {
        super(cause);
    }
    
    /**
     * try/catch에 잡은 Exception을 처리할 때 사용한다.
     * 메시지와 Throwable 객체를 받는 생성자. 로그의 사용여부에 따라 로그에 에러 메시지를 남길 수 있다.
     * 
     * <pre>
     * [사용법]
     *  try{
     *     // 관련 코드
     *     ...
     *  } catch (Exception e){
     *          //에러가 발생한 위치에서 새로운 메시지를 던짐
     *          String message = "관련 자료가 없습니다.";
     *          // 메시지 문자열과 Throwable 객체와 로그 사용여부를 인자로 넘김
     *          throw new BaseException(message, e, false);
     *  }
     * </pre>
     * 
     * @param message
     *          예외에 설정할 메시지 문자열. 로그에 남길 메시지 문자열을 입력한다.
     * @param cause
     * 		    설정할 예외 상황. Throwable을 상속받은 모든 예외가 적용될 수 있다.
     * @param useLog
     *          로그 사용 여부. true 일 경우 로그에 기록되며, false 일 경우 로그에 기록되지 않는다.
     */
    public BaseException(String message, Throwable cause, boolean useLog) {
        super(message, cause);
    }
    
    /**
     * try/catch에 잡은 Exception을 처리할 때 사용한다.
     * 메시지와 Throwable 객체를 받는 생성자. 로그의 사용여부에 따라 로그에 에러 메시지를 남길 수 있다.
     * 
     * <pre>
     * [사용법]
     *  try{
     *     // 관련 코드
     *     ...
     *  } catch (Exception e){
     *          // Throwable 객체와 로그 사용여부를 인자로 넘김
     *          throw new BaseException(e, false);
     *  }
     * </pre>
     * 
     * @param cause
     * 		    설정할 예외 상황. Throwable을 상속받은 모든 예외가 적용될 수 있다.
     * @param useLog
     *          로그 사용 여부. true 일 경우 로그에 기록되며, false 일 경우 로그에 기록되지 않는다.
     */
    public BaseException(Throwable cause, boolean useLog) {
        super(cause);
    }
    
    /**
     * 메시지를 받는 생성자 
     * 
     * @param message
     *          예외에 설정할 메시지 문자열. 로그에 남길 메시지 문자열을 입력한다.
     * @param logType
     * 			로그 파일에 저장할 로그 타입을 설정한다. nexcore.tool.core.log.LogType의 상수를 참조하도록 한다. 예)LogType.SUI
	 */
	public BaseException(String message, String logType) {
		super(message);
	}
	
	/**
	 * try/catch에 잡은 Exception을 처리할 때 사용한다.
     * 메시지와 Throwable 객체를 받는 생성자
     * 
     * <pre>
     * [사용법]
     *  try{
     *     // 관련 코드
     *     ...
     *  } catch (Exception e){
     *          //에러가 발생한 위치에서 새로운 메시지를 던짐
     *          String message = "관련 자료가 없습니다.";
     *          // 메시지 문자열과 Throwable 객체와 함께 특정 LogType을 인자로 넘김
     *          throw new BaseException(message, e, LogType.UII);
     *  }
     * </pre>
     * 
	 * @param message
     *          예외에 설정할 메시지 문자열. 로그에 남길 메시지 문자열을 입력한다.
	 * @param cause
     *          설정할 예외 상황. Throwable을 상속받은 모든 예외가 적용될 수 있다.
     * @param logType
     * 			로그 파일에 저장할 로그 타입을 설정한다. nexcore.tool.core.log.LogType의 상수를 참조하도록 한다. 예)LogType.SUI
	 */
	public BaseException(String message, Throwable cause, String logType) {
		super(message, cause);
	}
    
    /**
     * try/catch에 잡은 Exception을 처리할 때 사용한다.
     * Throwable 객체를 받는 생성자.
     * 
     * <pre>
     * [사용법]
     *  try{
     *     // 관련 코드
     *     ...
     *  } catch (Exception e){
     *          // Throwable 객체와 특정 LogType을 인자로 넘김
     *          throw new BaseException(e, LogType.UII);
     *  }
     * </pre>
     * 
     * @param cause
     *          설정할 추출할 예외 상황. Throwable을 상속받은 모든 예외가 적용될 수 있다.
     * @param logType
     * 			로그 파일에 저장할 로그 타입을 설정한다. nexcore.tool.core.log.LogType의 상수를 참조하도록 한다. 예)LogType.SUI
     */
    public BaseException(Throwable cause, String logType) {
        super(cause);
    }
}
