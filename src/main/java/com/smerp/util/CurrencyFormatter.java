package com.smerp.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;


import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class CurrencyFormatter implements TemplateMethodModelEx {

	@Override
	public Object exec(List args) throws TemplateModelException {
		if (args.size() != 1) {
			throw new TemplateModelException("Wrong arguments");
		}
		String number = String.valueOf(args.get(0));
		if (StringUtil.isEmptyTrim(number) || number.equals("null")) {
			number = "0";
		}
		BigDecimal d = new BigDecimal(number);
		com.ibm.icu.text.NumberFormat format = com.ibm.icu.text.NumberFormat.getNumberInstance(new Locale("en", "in"));
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		return format.format(d);
	}
}
