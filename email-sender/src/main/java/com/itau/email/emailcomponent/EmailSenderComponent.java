package com.itau.email.emailcomponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.itau.email.dto.EmailDTO;
import com.itau.email.utils.EmailUtils;

import lombok.SneakyThrows;

/**
 * @author luis
 */
@Component
public class EmailSenderComponent {

	@Autowired private JavaMailSender emailSender;
	@Autowired private EmailContentBuilderComponent mailContentBuilder;

	/**
	 * Method responsible for assembling and sending the email.
	 * @param emailDTO contains all informations to send the e-mail.
	 */
	@SneakyThrows
	public void sendEmailMessage(final EmailDTO emailDTO) {
		try {
			final MimeMessagePreparator messagePreparator = mimeMessage -> {
				final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
				final String content = mailContentBuilder.build(emailDTO);
				messageHelper.setText(content, true);
				messageHelper.setTo(emailDTO.getClientEmail());
				messageHelper.setSubject(EmailUtils.takeFirstContentTitle(content));
			};
			emailSender.send(messagePreparator);
		} catch (final Exception exception) {
			throw new Exception("ERROR SENDING EMAIL TO CLIENT" + emailDTO.getClientEmail() + ", TEMPLATE: " + emailDTO.getEmailTemplate() + ", WITH PARAMS:" + emailDTO.getParams(), exception);
		}
	}
}