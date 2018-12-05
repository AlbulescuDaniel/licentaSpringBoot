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
  public static final String EMAIL_ERROR = "Email error";

  public static final String BUNDLE_ROLE_FIND_BY_CODE = "role.find.ByCode";
  public static final String BUNDLE_USER_DO_NOT_FOUND = "user.find.username";
  public static final String BUNDLE_PRESCRIPTION_CREATE_NO_DRUGS = "prescription.drugs.notFound";
  public static final String BUNDLE_PATIENT_DO_NOT_EXIST = "prescription.drugs.notFound";
  public static final String BUNDLE_PATIENT_NAME_DO_NOT_EXIST = "patient.name.notFound";
  public static final String BUNDLE_EMAIL_SEND_FAILED = "email.send.failed";
}
