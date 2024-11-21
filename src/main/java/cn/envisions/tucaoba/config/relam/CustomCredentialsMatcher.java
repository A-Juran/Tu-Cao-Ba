package cn.envisions.tucaoba.config.relam;

import cn.envisions.tucaoba.entity.domain.User;
import cn.hutool.crypto.digest.MD5;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

public class CustomCredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken,
                                      AuthenticationInfo authenticationInfo) {

        String inputPassword = new String((char[]) authenticationToken.getCredentials());

        User storedPassword = (User)authenticationInfo.getCredentials();

        String hashedInputPassword = hashPassword(inputPassword);

        return hashedInputPassword.equalsIgnoreCase(storedPassword.getPassword());
    }
    //进行加密比较
    private String hashPassword(String password) {
        return MD5.create().digestHex(password);
    }
}
