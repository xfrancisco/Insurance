package org.insurance.service.info;

import java.util.List;

import org.insurance.dto.bill.BillDto;

public interface IBillInfo {

	List<BillDto> getBills(long numcli, int numcon);

}
