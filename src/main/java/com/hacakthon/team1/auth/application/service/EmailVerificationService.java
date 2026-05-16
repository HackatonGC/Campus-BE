package com.hacakthon.team1.auth.application.service;

import com.hacakthon.team1.auth.application.exception.InvalidVerificationCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private static final int CODE_LENGTH = 6;
    private static final int EXPIRE_MINUTES = 5;

    private final JavaMailSender mailSender;
    private final Map<String, VerificationEntry> store = new ConcurrentHashMap<>();

    public void sendCode(String email) {
        String code = generateCode();
        store.put(email, new VerificationEntry(code, LocalDateTime.now().plusMinutes(EXPIRE_MINUTES)));
        sendMail(email, code);
    }

    public void verifyCode(String email, String code) {
        VerificationEntry entry = store.get(email);
        if (entry == null || entry.isExpired() || !entry.code().equals(code)) {
            throw new InvalidVerificationCodeException();
        }
        store.remove(email);
    }

    private String generateCode() {
        SecureRandom random = new SecureRandom();
        int number = random.nextInt(900000) + 100000;
        return String.valueOf(number);
    }

    private void sendMail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[Campus] 이메일 인증 코드");
        message.setText("인증 코드: " + code + "\n\n유효 시간: " + EXPIRE_MINUTES + "분");
        mailSender.send(message);
    }

    private record VerificationEntry(String code, LocalDateTime expiredAt) {
        boolean isExpired() {
            return LocalDateTime.now().isAfter(expiredAt);
        }
    }
}
