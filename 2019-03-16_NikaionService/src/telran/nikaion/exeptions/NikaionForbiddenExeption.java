package telran.nikaion.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import telran.nikaion.dto.ServiceReturnCode;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.FORBIDDEN)
public class NikaionForbiddenExeption extends RuntimeException{
	public NikaionForbiddenExeption(ServiceReturnCode message) {
		super(message.toString());
	}
}
