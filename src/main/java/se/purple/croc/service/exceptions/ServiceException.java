package se.purple.croc.service.exceptions;


public class ServiceException extends RuntimeException {
	public ServiceException(String message) {
		super(message);
	}
}