package utils.web.struts2;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CrudActionSupport<T> extends ActionSupport implements
		ModelDriven<T>, Preparable {
	private static final long serialVersionUID = -1653204626115064950L;
	public static final String RELOAD = "reload";
	protected Logger logger = LoggerFactory.getLogger(super.getClass());

	protected boolean readOnly = true;

	public String execute() throws Exception {
		return list();
	}

	public abstract String list() throws Exception;

	public abstract String input() throws Exception;

	public abstract String save() throws Exception;

	public abstract String delete() throws Exception;

	public void prepare() throws Exception {
	}

	public void prepareInput() throws Exception {
		prepareModel();
	}

	public void prepareSave() throws Exception {
		prepareModel();
	}

	public void prepareSaveAct() throws Exception {
		prepareModel();
	}

	public void prepareSupplierList() throws Exception {
		prepareModel();
	}

	protected abstract void prepareModel() throws Exception;

	public boolean isReadOnly() {
		return this.readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	
}

