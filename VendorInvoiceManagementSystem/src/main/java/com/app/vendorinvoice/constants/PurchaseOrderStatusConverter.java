package com.app.vendorinvoice.constants;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PurchaseOrderStatusConverter implements AttributeConverter<PurchaseOrderStatus, Short> {

	@Override
	public Short convertToDatabaseColumn(PurchaseOrderStatus status) {
		return status == null ? null : status.getCode();
	}

	@Override
	public PurchaseOrderStatus convertToEntityAttribute(Short code) {
		return code == null ? null : PurchaseOrderStatus.fromCode(code);
	}

}
