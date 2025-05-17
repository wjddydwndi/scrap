package com.scrapy.service.login;

import com.scrapy.common.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    //private final EncryptUtil encryptUtil;

    private final String key = "scrapyencryptedkey";


    @Override
    public void login(String userId, String id, String password) {

        // 1. 파라미터 체크
        // 2. 비밀번호 복호화
        // 3. user 확인. *비밀번호 + 아이디

        // 4. token 발급.
        // 5. request_log 기록.

        // db조회시, redis update, AOP

        try {
            String decrypedPassword = EncryptUtil.decrypt(password, key);

            String encryptedPassword = EncryptUtil.bcryptHash(decrypedPassword);

            // db 조회


        } catch (NoSuchAlgorithmException e) {
            // 지정한 암호화 알고리즘을 사용할 수 없을 떄 발생.
        } catch (InvalidKeyException e) {
            // 키가 유효하지 않음.
        } catch (NoSuchPaddingException e) {
            // 패딩 방식이 잘못됐을 떄 발생.
        } catch (BadPaddingException e) {
            // 잘못된 패딩일 떄 발생.
        } catch (IllegalBlockSizeException e) {
            // 잘못된 블록 크기를 사용할 때 발생.
        }
    }

    @Override
    public void logout(String userId) {

        // 1. request_log 기록.
        // 2. token 무효화

    }


}