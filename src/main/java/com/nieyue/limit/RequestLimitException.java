package com.nieyue.limit;

import org.springframework.stereotype.Component;

/**
 * 请求过快异常
 * @author 聂跃
 * @date 2017年5月22日
 */
@Component
public class RequestLimitException extends Exception {
	private static final long serialVersionUID = 1364225358754654702L;
}
