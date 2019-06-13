package telran.nikaion.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import telran.nikaion.dto.ServiceReturnCode;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class NikaionNotFoundExeption extends RuntimeException{

	public NikaionNotFoundExeption(ServiceReturnCode message) {
		super(message.toString());
	}
}

