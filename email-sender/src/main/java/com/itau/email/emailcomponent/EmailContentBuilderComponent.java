package com.itau.email.emailcomponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.itau.email.dto.EmailDTO;

/**
 * @author luis
 */
@Component
public class EmailContentBuilderComponent {

	@Autowired private TemplateEngine templateEngine;

	/**
	 * Method responsible for changing the parameters in the chosen mail sending template.
	 * @param emailDTO contains the informations of parameters.
	 * @return Text that will be used in the body of the email to send
	 */
	public String build(final EmailDTO emailDTO) {
		final Context context = new Context();
		emailDTO.getParams().entrySet().forEach(entry -> context.setVariable(entry.getKey(), entry.getValue()));
		return templateEngine.process(emailDTO.getEmailTemplate(), context);
	}
}