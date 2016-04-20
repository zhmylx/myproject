package base;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import utils.DateEditor;


/*import com.xmniao.xmn.core.util.DateEditor;
import com.xmniao.xmn.core.util.StringUtils;*/


/**
 * 
 * @author Homing Tsang
 *
 * 2015年11月3日
 */


public class BaseController {

	protected final Logger log = Logger.getLogger(getClass());
	
	/**
	 * 时间转换
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}


}
