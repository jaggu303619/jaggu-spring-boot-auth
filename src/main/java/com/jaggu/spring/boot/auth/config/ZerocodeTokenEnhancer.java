package com.jaggu.spring.boot.auth.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.jaggu.spring.boot.auth.model.ZerocodeUser;

public class ZerocodeTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		final Map<String, Object> additionalInfo = new HashMap<>();
		ZerocodeUser user = (ZerocodeUser)authentication.getUserAuthentication().getPrincipal();
		additionalInfo.put("firstname", user.getFirstName());
		additionalInfo.put("lastname", user.getLastName());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}
}
