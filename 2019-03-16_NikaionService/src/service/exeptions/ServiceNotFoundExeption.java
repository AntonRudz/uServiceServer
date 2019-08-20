package service.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import service.dto.ServiceReturnCode;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class ServiceNotFoundExeption extends RuntimeException{

	public ServiceNotFoundExeption(ServiceReturnCode message) {
		super(message.toString());
	}
}

