package ins.framework.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

public class JavaMailServiceImpl implements MailService, InitializingBean {
	private static final Log logger = LogFactory
			.getLog(JavaMailServiceImpl.class);
	protected JavaMailSender mailSender;
	private FreeMarkerConfigurer mailTemplateEngine;

	public JavaMailServiceImpl() {
		this.mailTemplateEngine = null;
	}

	public void setMailTemplateEngine(FreeMarkerConfigurer mailTemplateEngine) {
		this.mailTemplateEngine = mailTemplateEngine;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void send(SimpleMailMessage msg) {
		try {
			this.mailSender.send(msg);
		} catch (MailException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void send(String from, String to, String subject, String text) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(from);
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(text);
		send(msg);
	}

	public void send(SimpleMailMessage msg, String templateName,
			Map<String, Object> model) {
		String content = generateEmailContent(templateName, model);
		MimeMessage mimeMsg = this.mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true,
					"utf-8");

			helper.setTo(msg.getTo());
			helper.setSubject(msg.getSubject());
			helper.setFrom(msg.getFrom());
			helper.setText(content, true);
		} catch (MessagingException ex) {
			logger.error(ex.getMessage(), ex);
		}
		this.mailSender.send(mimeMsg);
	}

	public String generateEmailContent(String templateName,
			Map<String, Object> map) {
		try {
			Template t = this.mailTemplateEngine.getConfiguration()
					.getTemplate(templateName);

			return FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
		} catch (TemplateException e) {
			logger.error("Error while processing FreeMarker template ", e);
		} catch (FileNotFoundException e) {
			logger.error("Error while open template file ", e);
		} catch (IOException e) {
			logger.error("Error while generate Email Content ", e);
		}
		return null;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.mailSender, "未注入MailSender");
	}

	public MimeMessageHelper createMimeMessageHelper() {
		MimeMessage message = this.mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(message, true, "GBK");
		} catch (MessagingException me) {
			throw new IllegalStateException(me);
		}
		return helper;
	}

	public void send(MimeMessage mimeMessage) {
		this.mailSender.send(mimeMessage);
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.mail.JavaMailServiceImpl JD-Core Version: 0.5.4
 */