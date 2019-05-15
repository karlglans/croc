package se.purple.croc.resolver.exceptions;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.validation.ValidationError;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//public class MissingData extends RuntimeException implements GraphQLError {
//	private final int errorCode;
//
//	public MissingData(int errorCode, String errorMessage) {
//		super(errorMessage);
//
//		this.errorCode = errorCode;
//	}
//
//	@Override
//	public Map<String, Object> getExtensions() {
//		Map<String, Object> customAttributes = new LinkedHashMap<>();
//
//		customAttributes.put("errorCode", this.errorCode);
//		customAttributes.put("errorMessage", this.getMessage());
//
//		return customAttributes;
//	}
//
//	@Override
//	public List<SourceLocation> getLocations() {
//		return null;
//	}
//
//	@Override
//	public ErrorType getErrorType() {
//		return null;
//	}
//}
