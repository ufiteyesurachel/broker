package net.enjoy.springboot.BrokerMS.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromAddress;

    // Sending normal simple email
//    public void sendEmail(String to, String subject, String body) {
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom("Tasker Project Fame <" + fromAddress + ">");
//            message.setTo(to);
//            message.setSubject(subject);
//            message.setText(body);
//            mailSender.send(message);
//            log.info("Email sent successfully! To: {}", to);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }

    // Send email with template
    @Async
    public void sendEmail(String to, String subject, Context context){
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

            helper.setFrom("Broker Management System <" + fromAddress + ">");
            helper.setSubject(subject);
            helper.setTo(to);

            String htmlContent = templateEngine.process("notification-email", context);
            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
            log.info("Email sent successfully, to: {}", to);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
