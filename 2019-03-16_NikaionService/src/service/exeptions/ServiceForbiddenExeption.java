package service.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import service.dto.ServiceReturnCode;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ServiceForbiddenExeption extends RuntimeException{
	public ServiceForbiddenExeption(ServiceReturnCode message) {
		super(message.toString());
	}
}
