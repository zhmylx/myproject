package export.tag;

import org.apache.poi.ss.usermodel.Cell;

import utils.StringUtil;
import export.bean.CellDefinition;

public class ListTag implements export.PoiTag{
	
	
	

	@Override
	public CellDefinition parse(Cell cell, String tag) {
		CellDefinition cellDefinition =null;
		if(tag.startsWith(NAME_PREFIX_START)){
			String[] values = StringUtil.paresToArray(tag, " ");
			if(values.length==4){
				cellDefinition = new CellDefinition(cell, values[3].substring(NAME_PREFIX.length(), values[3].length()-NAME_Suffix.length()), values[1]);
			}
		}
		return cellDefinition;
	}

}
