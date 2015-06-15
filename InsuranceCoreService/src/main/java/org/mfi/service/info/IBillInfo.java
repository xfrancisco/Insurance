package org.mfi.service.info;

import java.util.List;

import org.mfi.dto.bill.BillDto;

public interface IBillInfo {

	List<BillDto> getBills(long numcli, int numcon);

}
