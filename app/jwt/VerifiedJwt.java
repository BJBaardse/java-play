package jwt;

import java.util.Date;

public interface VerifiedJwt {
    String getHeader();
    String getPayload();
    String getIssuer();
    String getUser();
    Date getExpiresAt();
}