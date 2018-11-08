package net.licenta;

public class Constants {
  
  private Constants() {
  }
  
  public static final long EXPIRATION_TIME = 840_000_000; // 10 days
  public static final String SECRET = "{noop}ThisIsASecret";
  public static final String TOKEN_PREFIX = "Bearer";
  public static final String HEADER_STRING = "Authorization";
  
  public static final String MESSAGE_BUNDLE = "messages";
  public static final String NOT_FOUND = "Not found";
  public static final String BAD_REQUEST = "Bad request";
  
  public static final String BUNDLE_ROLE_FIND_BY_CODE = "role.find.ByCode";
  public static final String BUNDLE_USER_ID_NOT_FOUND = "user.find.username";
  public static final String BUNDLE_PRESCRIPTION_CREATE_NO_DRUGS = "prescription.drugs.notFound";

}
