package export.tag;

import org.apache.poi.ss.usermodel.Cell;

import utils.StringUtil;
import export.PoiTag;
import export.bean.CellDefinition;


public class TitleTag implements PoiTag{

	@Override
	public CellDefinition parse(Cell cell, String tag) {
		CellDefinition cellDefinition =null;
		if(StringUtil.hasLength(tag)&&!tag.startsWith("#")&&!tag.startsWith(NAME_PREFIX)&&!tag.endsWith(NAME_Suffix)){
			cellDefinition = new CellDefinition(cell, tag);
		}
		return cellDefinition;
	}

}
