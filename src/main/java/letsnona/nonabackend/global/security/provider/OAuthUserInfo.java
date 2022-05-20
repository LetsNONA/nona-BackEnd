package letsnona.nonabackend.global.security.provider;

public interface OAuthUserInfo {
    String getProviderId();

    String getProvider();

    String getEmail();

    String getName();
}
