package cn.envisions.tucaoba.security.auth.relam;

import cn.hutool.crypto.digest.MD5;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

public class CustomCredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken,
                                      AuthenticationInfo authenticationInfo) {

        String inputToken = (String) authenticationToken.getCredentials();

        String token = (String) authenticationInfo.getCredentials();

        //String hashedInputPassword = hashPassword(inputPassword);

        return token.equalsIgnoreCase(inputToken);
    }
    //进行加密比较
    private String hashPassword(String password) {
        return MD5.create().digestHex(password);
    }
}
