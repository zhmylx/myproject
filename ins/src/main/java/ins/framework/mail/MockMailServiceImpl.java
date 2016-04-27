package ins.framework.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

public class MockMailServiceImpl implements MailService {
	private static final Log logger = LogFactory
			.getLog(MockMailServiceImpl.class);
	private FreeMarkerConfigurer mailTemplateEngine;

	public MockMailServiceImpl() {
		this.mailTemplateEngine = null;
	}

	public void setMailTemplateEngine(FreeMarkerConfigurer mailTemplateEngine) {
		this.mailTemplateEngine = mailTemplateEngine;
	}

	public void send(SimpleMailMessage msg) {
		StringBuffer mail = new StringBuffer(100);
		mail.append(getMailHeader(msg)).append('\n');
		mail.append(msg.getText());
		logger.info(mail.toString());
	}

	public void send(SimpleMailMessage msg, String templateName,
			Map<String, Object> model) {
		StringBuffer mail = new StringBuffer(100);
		mail.append(getMailHeader(msg)).append('\n');
		mail.append(generateEmailContent(templateName, model));
		logger.info(mail.toString());
	}

	public void send(String from, String to, String subject, String text) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(from);
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(text);
		send(msg);
	}

	protected String getMailHeader(SimpleMailMessage msg) {
		StringBuffer header = new StringBuffer(100);
		header.append("To: ");
		for (String to : msg.getTo()) {
			header.append(to).append(';');
		}
		header.append("\nFrom: ").append(msg.getFrom());
		header.append("\nSubject: ").append(msg.getSubject());
		return header.toString();
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

	public MimeMessageHelper createMimeMessageHelper() {
		logger.error("not implements the method createMimeMessageHelper().");
		return null;
	}

	public void send(MimeMessage mimeMessage) {
		logger.error("not implements the method send(MimeMessage mimeMessage).");
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.mail.MockMailServiceImpl JD-Core Version: 0.5.4
 */