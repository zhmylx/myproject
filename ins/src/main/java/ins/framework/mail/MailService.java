package ins.framework.mail;

import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

public abstract interface MailService {
	public abstract void send(SimpleMailMessage paramSimpleMailMessage);

	public abstract void send(String paramString1, String paramString2,
			String paramString3, String paramString4);

	public abstract void send(SimpleMailMessage paramSimpleMailMessage,
			String paramString, Map<String, Object> paramMap);

	public abstract MimeMessageHelper createMimeMessageHelper();

	public abstract void send(MimeMessage paramMimeMessage);
}
